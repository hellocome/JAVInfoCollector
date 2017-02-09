package com.zhaohaijie.taskrunner;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;

public class TaskRunner {

    private static final int maxThreadNum = Integer.MAX_VALUE;
    private static final int queueCapacity = Integer.MAX_VALUE;
    private static final int keepAliveSeconds = 60;
    
    private ThreadPoolExecutor threadPoolExecutor = null;

    public TaskRunner(int threadNum) {

        BlockingQueue<Runnable> queue = createQueue(queueCapacity);

        threadPoolExecutor = new ThreadPoolExecutor(threadNum, maxThreadNum,
                keepAliveSeconds,
                TimeUnit.SECONDS,
                queue);

    }

    public TaskRunner() {
        this(5);
    }


    public void execute(Runnable task) {
        ThreadPoolExecutor executor = getThreadPoolExecutor();

        try {
            executor.execute(task);
        }
        catch (RejectedExecutionException ex) {
            throw new RejectedExecutionException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }

    private BlockingQueue<Runnable> createQueue(int size) {
        return new LinkedBlockingQueue<>(size);
    }

    private ThreadPoolExecutor getThreadPoolExecutor(){
        return threadPoolExecutor;
    }
}
