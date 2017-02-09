package com.zhaohaijie.taskrunner;

public interface TaskResultProcessor extends TaskEventListener {
	void taskStatusChanged(TaskEvent e);
	
	Integer getTaskID();
	
	void setTaskID(Integer taskId);
}
