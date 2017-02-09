package com.zhaohaijie.httptask.JAV;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zhaohaijie.httptask.SimpleHttpDownloadTask;
import com.zhaohaijie.httptask.JAV.Objects.JAVRecord;
import com.zhaohaijie.logging.AppLogger;
import com.zhaohaijie.taskrunner.TaskEvent;
import com.zhaohaijie.taskrunner.TaskEventListener;
import com.zhaohaijie.taskrunner.TaskResultProcessor;
import com.zhaohaijie.taskrunner.TaskStatus;

/**
 * Created by on 03/02/2017.
 */
public class JAVHttpTaskResultProcessor implements TaskResultProcessor {
	
	// private static final String ITEM_PATTERN = "<div[^>]* class=\"video\" id=\"vid_[\\S]*\">[\\s]*<a[^>]* href=\"([^\"]*)\"[^>]*>[\\s]*<div[^>]* class=\"id\">([^>]*)</div>[\\s]*<img[^>]* src=\"([^\"]*.jpg)\"[^>]*/>[\\s]*<div[^>]* class=\"title\"[^>]*>([^>]*)</div>";
	
	private static final String ItemPattern = "(?s)<div[\\s]* id=\"%s\".*?<td class=\"text\">[\\w-]*</td>";
	private static final String JACKET_IMG_PATTERN = "<img[^>]+id=\"video_jacket_img\"[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
	private static final String TITLE_PATTERN = "<title>([^>]*)- JAVLibrary</title>";
	private static final String PREVIEW_IMG_PATTERN = "(?s)<div[\\s]* class=\"previewthumbs\".*?</div>";
	private static final String IMG_PATTERN = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
	private static final String DateTimeFormat = null;
	private int taskId = 0;
	public Integer getTaskID(){
		return taskId;
	}
	
	public void setTaskID(Integer taskId){
		this.taskId = taskId;
	}	
	
    public void taskStatusChanged(TaskEvent e){
    	
    	TaskStatus statuc = e.getTaskStatus();
    	JAVHttpDownloadTask task;
    	
    	try{
    		task = (JAVHttpDownloadTask)e.getSource();
    	}catch(Exception ex){
    		AppLogger.getLogger().error("Fail cast type: ", ex);
    		statuc = TaskStatus.FINISH_FAIL;
    	}
    	
        if(statuc == TaskStatus.FINISH_OK) {

        }else if(statuc == TaskStatus.FINISH_FAIL) {

        }else if(statuc == TaskStatus.RUNNING) {

        }
    }
    
    private String getItem(String fromStr, String itemName){
    	String pattern = String.format(ItemPattern, itemName);
	    Pattern p = Pattern.compile(pattern);
	    Matcher m = p.matcher(fromStr);
	    
	    if(m.find()){
	    	return m.group();
	    }
	    else{
	    	return "";
	    }
    }
    
    private List<String> getItemList(String fromStr, String itemName, int group){
    	List<String> items = new ArrayList<String>();
    	String pattern = String.format(ItemPattern, itemName);
	    Pattern p = Pattern.compile(pattern);
	    Matcher m = p.matcher(fromStr);
	    
	    while(m.find()){
	    	items.add(m.group(group));
	    }
    }
    
    private List<String> getItemList(String fromStr, String itemName){
    	return getItemList(fromStr, itemName, 0);
    }
    
    private String getPatternItemImage(String fromStr, String pattern){
	    Pattern p = Pattern.compile(pattern);
	    Matcher m = p.matcher(fromStr);
	    
	    if(m.find()){
	    	return m.group(1);
	    }
	    else{
	    	return "";
	    }
    }
    
    private List<String> getPreviewImage(String fromStr){
	    String imgDiv = getItem(fromStr, PREVIEW_IMG_PATTERN);
	    
	    if(!stringIsNullOrEmpty(imgDiv)){
	    	return getItemList(imgDiv, IMG_PATTERN);
	    }
	    
	    return null;
    }
    
    private boolean stringIsNullOrEmpty(String str){
    	return str == null || str.isEmpty();
    }
    
	private void generateRecord(JAVHttpDownloadTask task){
		try{
			String url = task.getTaskParameter().getParameter();
			AppLogger.getLogger().info("Parse: " + url);
			
			byte[] content = SimpleHttpDownloadTask.downloadHtml(url);
			String str = new String(content, StandardCharsets.UTF_8);

			String video_id = getItem(str, "video_id");
			String video_date = getItem(str, "video_date");
			String video_length = getItem(str, "video_length");
			String video_director = getItem(str, "video_director");
			String video_maker = getItem(str, "video_maker");
			String video_label = getItem(str, "video_label");
			String video_genres = getItem(str, "video_genres");
			String video_cast = getItem(str, "video_cast");
			
			String video_jack_img = getPatternItemImage(str, JACKET_IMG_PATTERN);
			String video_title = getPatternItemImage(str, TITLE_PATTERN);
			
			List<String> video_pre_img = getPreviewImage(str);
			
			if(!stringIsNullOrEmpty(video_id)){
				JAVRecord record = new JAVRecord(task.getLanguage(), video_id, video_title);
				
				try{
					SimpleDateFormat sdfmt= new SimpleDateFormat("yyyy-MM-dd");
					Date releaseDate = sdfmt.parse( video_date );
					
					record.setReleaseDate(releaseDate);
				}catch(Exception dt){
					AppLogger.getLogger().error("Fail parse date", dt);
				}
				
				try{
					int length = (int)video_length;
					
					record.setReleaseDate(releaseDate);
				}catch(Exception dt){
					AppLogger.getLogger().error("Fail parse date", dt);
				}
			}
			
		    		    
		    return lastPage;
		}catch(Exception ex){
			AppLogger.getLogger().error("Fail to get last page", ex);
			return -1;
		}
	}
}
