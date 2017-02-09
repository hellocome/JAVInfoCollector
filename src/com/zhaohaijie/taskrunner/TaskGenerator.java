package com.zhaohaijie.taskrunner;
import java.util.List;

public interface TaskGenerator {
	List<Task> createTasks(TaskResultProcessor resultProcessor);
	
	Integer getTaskID();
	
	void setTaskID(Integer taskId);
}
