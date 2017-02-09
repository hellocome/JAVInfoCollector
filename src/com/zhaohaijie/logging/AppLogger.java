package com.zhaohaijie.logging;

public class AppLogger {
	
	private static final ILogger logger = new Log4jLogger();
	
	public static ILogger getLogger(){
		return logger;
	}
}
