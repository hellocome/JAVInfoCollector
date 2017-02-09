package com.zhaohaijie.taskrunner;

/**
 * Created by zhaoh on 02/02/2017.
 */
public interface TaskEventListener {
    void taskStatusChanged(TaskEvent e);
}
