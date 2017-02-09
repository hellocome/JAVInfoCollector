package com.zhaohaijie.logging;

public interface ILogger {
	void error(String error);
	void error(Exception ex);
	void error(String errorInfo, Exception ex);
	
	void info(String info);
	
	void warn(String debug);
	
	void debug(String debug);
}
