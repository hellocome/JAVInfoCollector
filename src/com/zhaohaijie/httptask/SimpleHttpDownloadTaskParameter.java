package com.zhaohaijie.httptask;

import com.zhaohaijie.taskrunner.TaskParameter;

/**
 * Created by on 03/02/2017.
 */
public class SimpleHttpDownloadTaskParameter implements TaskParameter<String>{

    private String url;

    public String getParameter(){
        return url;
    }

    public void setParameter(String parameter){
        url = parameter;
    }

    public SimpleHttpDownloadTaskParameter(String url){
        setParameter(url);
    }
}
