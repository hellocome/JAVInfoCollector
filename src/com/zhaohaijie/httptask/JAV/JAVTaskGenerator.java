package com.zhaohaijie.httptask.JAV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import java.nio.charset.StandardCharsets;

import com.zhaohaijie.httptask.SimpleHttpDownloadTask;
import com.zhaohaijie.logging.AppLogger;
import com.zhaohaijie.taskrunner.Task;
import com.zhaohaijie.taskrunner.TaskGenerator;
import com.zhaohaijie.taskrunner.TaskResultProcessor;


public class JAVTaskGenerator implements TaskGenerator {
	
	private List<String> pageIndex = new ArrayList<String>();
	private String[] languages = {"en", "cn"};
	
	private static final String LAST_PAGE_PATTERN = "<a class=\"page last\" href=\"([\\w/]*).php\\?&mode=&page=([0-9]*)\">>>";
	
	private int taskId = 0;
	
	public Integer getTaskID(){
		return taskId;
	}
	
	public void setTaskID(Integer taskId){
		this.taskId = taskId;
	}
	
	public JAVTaskGenerator(){
		pageIndex.add("http://www.javlibrary.com/en/vl_update.php");
		pageIndex.add("http://www.javlibrary.com/en/vl_newrelease.php");
		pageIndex.add("http://www.javlibrary.com/en/vl_newentries.php");
		pageIndex.add("http://www.javlibrary.com/en/vl_mostwanted.php");
		pageIndex.add("http://www.javlibrary.com/en/vl_bestrated.php");
		pageIndex.add("http://www.javlibrary.com/en/vl_mostwanted.php");
	}
	
	public List<Task> createTasks(TaskResultProcessor resultProcessor){
		
		/*
		List<Task> tasks = new ArrayList<Task>();
		
		for(String url : pageIndex){
			tasks.addAll(buildTaskFromURL(url, resultProcessor));
		}*/
		
		List<Task> tasks = buildTaskBrutal(resultProcessor);
		
		
		return tasks;
	}
	
	private List<Task> buildTaskBrutal(TaskResultProcessor resultProcessor){
		List<Task> tasks = new ArrayList<Task>();
		
		char[] alphabetnum = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		

		for(int a=0; a<alphabetnum.length; a++){
			for(int b=0; b<alphabetnum.length; b++){
				for(int c=0; c<alphabetnum.length; c++){
					
					for(String lang: languages){
						String prefix = "" + alphabetnum[a] + alphabetnum[b] + alphabetnum[c];
						String downloadUrl = String.format("http://www.javlibrary.com/%s/?v=javlika%s", lang, prefix);
						
						AppLogger.getLogger().info("BuildTaskFromURL: " + downloadUrl);
						
						JAVHttpDownloadTask task = new JAVHttpDownloadTask(downloadUrl);
						task.addTaskEventListener(resultProcessor);
						task.setTaskGenerator(this);
						task.setLanguage(lang);
						tasks.add(task);
					}
				}
			}	
		}
		
		return tasks;
	}
	
	
	private List<Task> buildTaskFromURL(String url, TaskResultProcessor resultProcessor){
		AppLogger.getLogger().info("BuildTaskFromURL: " + url);
		List<Task> tasks = new ArrayList<Task>();
		
		int last = lastPage(url);
		
		for(int i=1; i<=last; i++){
			String downloadUrl = String.format("%s?&mode=&page=%d", url, i);
			
			SimpleHttpDownloadTask task = new SimpleHttpDownloadTask(downloadUrl);
			task.addTaskEventListener(resultProcessor);
			tasks.add(task);
		}
		
		AppLogger.getLogger().info("BuildTaskFromURL: " + tasks.size());
		
		return tasks;
	}
	
	
	
	private int lastPage(String url){
		int lastPage = -1;
		
		try{
			AppLogger.getLogger().info("Parse: " + url);
			
			byte[] content = SimpleHttpDownloadTask.downloadHtml(url);
			String str = new String(content, StandardCharsets.UTF_8);
		    Pattern p = Pattern.compile(LAST_PAGE_PATTERN);
		    Matcher m = p.matcher(str);
		    
		    
		    if(m.find()){
		    	String matchStr = m.group(2);
		    	lastPage = Integer.parseInt(matchStr);
		    	
		    	AppLogger.getLogger().info("LastPage: " + lastPage);
		    }
		    
		    return lastPage;
		}catch(Exception ex){
			AppLogger.getLogger().error("Fail to get last page", ex);
			return -1;
		}
	}
	
}
