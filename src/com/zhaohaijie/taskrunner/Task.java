package com.zhaohaijie.taskrunner;

/**
 * Created by on 02/02/2017.
 */
public interface Task extends Runnable {

    <T> TaskParameter<T> getTaskParameter();
    
    TaskGenerator getTaskGenerator();
    void setTaskGenerator(TaskGenerator generator);

    void addTaskEventListener(TaskEventListener e);

    void removeTaskEventListener(TaskEventListener e);
}
