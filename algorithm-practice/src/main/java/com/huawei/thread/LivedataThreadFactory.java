package com.huawei.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author king
 * @date 2022/7/6-0:42
 * @Desc
 */
public class LivedataThreadFactory {
    private static final Logger log = LoggerFactory.getLogger(LivedataThreadFactory.class);
    private static int coreNum = Runtime.getRuntime().availableProcessors()*2;
    private static int maxNum = coreNum+1;
    private static ExecutorService executorService;

    private static ScheduledExecutorService scheduledExecutorService;

    public static ExecutorService init() {
        executorService = new ThreadPoolExecutor(
                coreNum,
                maxNum,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                new CustomThread(),
                new CustomRejected());
        return executorService;
    }

    public static ScheduledExecutorService initSchedule() {
        return new ScheduledThreadPoolExecutor(1, (ThreadFactory) r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("ScheduledTask");
            return t;
        },new ThreadPoolExecutor.AbortPolicy());
    }

    private static class CustomThread implements ThreadFactory {
        AtomicInteger count = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("livedata-exec-"+count.incrementAndGet());
            t.setUncaughtExceptionHandler((t1, e) -> {
                log.warn("This thread run exception {}",t.getName(),t1);
            });
            return t;
        }
    }

    static class CustomRejected implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                log.warn("Task is filled.",e);
            }
        }
    }
}
