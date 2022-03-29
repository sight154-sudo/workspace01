package com.huawei.thread;

import java.util.concurrent.*;

/**
 * @author king
 * @date 2022/3/3-23:43
 * @Desc
 */
public class CustomThreadPoolExecutor {

    private ThreadPoolExecutor pool = null;

    public void init() {
        pool = new ThreadPoolExecutor(
                10,
                20,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public ExecutorService getExecutorService(){
        return this.pool;
    }


    public void release(){
        if(!this.pool.isShutdown()) {
            this.pool.shutdown();
        }
    }

    public static class CustomThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            return null;
        }
    }

    static class MyRejectedHandler implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }


    public static void main(String[] args) throws Exception{
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor();
        customThreadPoolExecutor.init();
        ExecutorService executorService = customThreadPoolExecutor.getExecutorService();

        Future<Integer> odd = executorService.submit(new Task("odd"));
        Future<Integer> even = executorService.submit(new Task("even"));
        System.out.println("odd = " + odd.get());
        System.out.println("even = " + even.get());
        executorService.shutdown();
//        Thread.sleep(1000);
    }

    private static class Task implements Callable<Integer>{
        private String flag;
        public Task(String flag) {
            this.flag = flag;
        }
        @Override
        public Integer call() throws Exception {
            if(flag.equals("odd")) {
                return oddAvg();
            }
            return evenAvg();
        }

        public Integer oddAvg(){
            int i = 1;
            int count = 0;
            while(i < 100){
                if((i&1) == 1) {
                    count +=i;
                }
                i++;
            }
            return count;
        }

        public Integer evenAvg() {
            int i = 1;
            int count = 0;
            while(i < 100){
                if((i&1) == 0) {
                    count +=i;
                }
                i++;
            }
            return count;
        }
    }

}
