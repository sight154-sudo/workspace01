package com.huawei.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author king
 * @date 2022/8/30-0:48
 * @Desc
 */
public class ThreadFactory {
    private static volatile ThreadPool threadPool;

    /**
     * 获取线程池对象
     * @return
     */
    public static ThreadPool getInstance() {
        if (threadPool == null) {
            synchronized (ThreadFactory.class) {
                if (threadPool == null) {
                    int cpuNum = Runtime.getRuntime().availableProcessors();
                    int threadNum = cpuNum *2 +1;
                    threadPool = new ThreadPool(threadNum-1, threadNum, Integer.MAX_VALUE );
                }
            }
        }
        return threadPool;
    }

    public static class ThreadPool {
        private ThreadPoolExecutor mExecutor;

        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         *
         * @param r
         */
        public void execute(Runnable r) {
            if (null == r) {
                return;
            }
            mExecutor = getMExecute(mExecutor, corePoolSize, maximumPoolSize, keepAliveTime);
            mExecutor.execute(r);
        }

        private ThreadPoolExecutor getMExecute(ThreadPoolExecutor mExecutor, int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            if (mExecutor == null) {
                mExecutor = new ThreadPoolExecutor(corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<Runnable>(Integer.MAX_VALUE),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy() {
                            @Override
                            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                                super.rejectedExecution(r, e);
                            }
                        });
            }
            return mExecutor;
        }

        public void cancel(Runnable r) {
            if (mExecutor != null) {
                mExecutor.getQueue().remove(r);
            }
        }
    }
}
