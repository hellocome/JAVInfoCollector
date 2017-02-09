package com.zhaohaijie.httptask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import com.zhaohaijie.taskrunner.*;

/**
 * Created by Me on 03/02/2017.
 */
public class SimpleHttpDownloadTask implements Task{

	protected static final int BUFFSIZE = 1024;
	protected TaskGenerator generator;
	protected ConcurrentLinkedQueue<TaskEventListener> taskEventListenerList = new ConcurrentLinkedQueue<>();

	protected byte[] downloadContent;
    public byte[] getDownloadContent(){
        return downloadContent;
    }

    protected SimpleHttpDownloadTaskParameter taskParameter;
    
    @SuppressWarnings("unchecked")
    public TaskParameter<String> getTaskParameter(){
        return taskParameter;
    }

    public SimpleHttpDownloadTask(String url){
        taskParameter = new SimpleHttpDownloadTaskParameter(url);
    }

    protected void notifyTaskEventListener(TaskEvent e){
        for (TaskEventListener listener: taskEventListenerList) {
            listener.taskStatusChanged(e);
        }
    }

    public void addTaskEventListener(TaskEventListener e){
            taskEventListenerList.add(e);
    }

    public void removeTaskEventListener(TaskEventListener e){
        taskEventListenerList.remove(e);
    }
    
    public TaskGenerator getTaskGenerator(){
        return generator;
    }
    
    public void setTaskGenerator(TaskGenerator generator){
        this.generator =generator;
    }

    public void run(){
        TaskEvent taskEvent;

        try {
            taskEvent = new TaskEvent(this, TaskStatus.RUNNING);
            notifyTaskEventListener(taskEvent);

            downloadContent = downloadHtml(taskParameter.getParameter());

            taskEvent = new TaskEvent(this, TaskStatus.FINISH_OK);
            notifyTaskEventListener(taskEvent);

        }catch (Exception ex){
            taskEvent = new TaskEvent(this, TaskStatus.FINISH_FAIL);
            taskEvent.setFailureException(ex);

            notifyTaskEventListener(taskEvent);
        }
    }

    public static byte[] downloadHtml(final String url) throws IOException{
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;

        try {
        	URLConnection connection = new URL(url).openConnection();
        	connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        	connection.connect();
        	
            in = new BufferedInputStream(connection.getInputStream());
            out = new ByteArrayOutputStream();

            final byte buffer[] = new byte[BUFFSIZE];
            int count;

            while ((count = in.read(buffer, 0, BUFFSIZE)) != -1) {
                out.write(buffer, 0, count);
            }

            return out.toByteArray();

        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
