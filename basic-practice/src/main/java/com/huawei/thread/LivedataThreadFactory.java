package com.huawei.thread;

import java.util.concurrent.*;

/**
 * @author king
 * @date 2022/3/4-0:00
 * @Desc
 */
public class LivedataThreadFactory {



    public static class ThreadPool {
        private ThreadPoolExecutor executor;

        private int corePoolSize;
        private int maxPoolSize;
        private long keepAliveTine;

        public ThreadPool(int corePoolSize, int maxPoolSize, long keepAliveTine) {
            this.corePoolSize = corePoolSize;
            this.maxPoolSize = maxPoolSize;
            this.keepAliveTine = keepAliveTine;
        }


        public void execute(Runnable r){

        }

        public <T> Future<T> exector(Callable callable){
            return null;
        }

        private static ThreadPoolExecutor getExecutor(ThreadPoolExecutor executor,int corePoolSize, int maxPoolSize, long keepAliveTine){
            if(executor == null) {
                executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTine, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<Runnable>(Integer.MAX_VALUE),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy(){
                            @Override
                            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                                super.rejectedExecution(r, e);
                            }
                        });
            }
            return executor;
        }
    }
}
