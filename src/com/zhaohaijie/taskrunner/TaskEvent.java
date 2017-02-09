package com.zhaohaijie.taskrunner;

import java.util.EventObject;

/**
 * Created by on 02/02/2017.
 */
public class TaskEvent extends EventObject {

    /**
	 * Version 1
	 */
	private static final long serialVersionUID = 1L;
	private TaskStatus taskStatus;

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    private Exception failureException;
    public Exception getFailureException() {
        return failureException;
    }

    public void setFailureException(Exception ex) {
        failureException = ex;
    }

    public TaskEvent(Object objectSource, TaskStatus taskStatus) {
        super(objectSource);
        this.taskStatus = taskStatus;
    }
}
