package com.zhaohaijie.httptask.JAV;

import com.zhaohaijie.httptask.SimpleHttpDownloadTask;

public class JAVHttpDownloadTask extends SimpleHttpDownloadTask {
	
	private String language;
	public String getLanguage(){
		return language;
	}
	
	public void setLanguage(String lang){
		language = lang;
	}
	
    public JAVHttpDownloadTask(String url){
    	super(url);
    }
}
