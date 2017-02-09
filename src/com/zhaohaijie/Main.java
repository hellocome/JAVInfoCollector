package com.zhaohaijie;

import com.zhaohaijie.logging.AppLogger;
import com.zhaohaijie.taskrunner.TaskFactory;
import com.zhaohaijie.taskrunner.onfiguration.ConfigurationMamanger;

public class Main {

    public static void main(String[] args) {
	// write your code here
    	AppLogger.getLogger().info("Application starting...");
    	
    	ConfigurationMamanger.getConfigurationMamangerInstance().initialize();
    	TaskFactory.getTaskFactoryInstance().Start();
    	
    	AppLogger.getLogger().info("Application Exit...");
    }
}
