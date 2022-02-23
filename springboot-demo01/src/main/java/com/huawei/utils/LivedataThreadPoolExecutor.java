package com.huawei.utils;

import org.apache.tomcat.jni.Pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author king
 * @date 2022/2/19-17:09
 * @Desc
 */
public class LivedataThreadPoolExecutor {

    private static ThreadPoolExecutor threadPoolExecutor;


    public static ThreadPool getInstance(){
        threadPoolExecutor = new ThreadPoolExecutor(
                10,
                20,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy());
        ThreadPool threadPool = new ThreadPool();
        return threadPool;
    }

    public static ThreadPoolExecutor getPool(){
        return threadPoolExecutor;
    }

    static class ThreadPool extends Thread {
        private Integer corePoolSize;
        private Integer maximumPoolSize;
        private Integer keepAliveTime;



        public ThreadPool(){

        }
    }
}

