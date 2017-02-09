package com.zhaohaijie.logging;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4jLogger implements ILogger {
	final static Logger logger = LogManager.getLogger("AppLogFile");

	public Log4jLogger(){

	}
	
	public void error(String error){
		logger.error(error);
	}
	
	public void error(Exception ex){
		logger.error(ex.toString());
	}
	
	public void error(String errorInfo, Exception ex){
		logger.error(String.format("%s  Exception = %s", errorInfo, ex.toString()));
	}
	
	public void info(String info){
		logger.info(info);
	}
	
	public void debug(String debug){
		logger.debug(debug);
	}
	
	public void warn(String warn){
		logger.warn(warn);
	}
}
