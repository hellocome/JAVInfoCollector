package com.zhaohaijie.taskrunner;

/**
 * Created by zhaoh on 02/02/2017.
 */
public interface TaskParameter<T> {

    T getParameter();

    void setParameter(T parameter);
}
