package com.huawei.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author king
 * @date 2022/10/24-23:20
 * @Desc
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        m1();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        Integer join = CompletableFuture.supplyAsync(() -> {
            return 1;
        },threadPoolExecutor).handle((f,e) -> {
            int i = 1/0;
            System.out.println("------1-----------");
            return f + 2;
        }).handle((f,e) -> {
            System.out.println("------2-----------");
            return f + 3;
        }).handle((f,e) -> {
            System.out.println("------3-----------");
            return f + 4;
        }).whenComplete((f, e) -> {
            if (e == null) {
                System.out.println(f);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return 0;
        }).join();
        System.out.println(join);
        threadPoolExecutor.shutdown();
    }

    private static void m1() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });


//        System.out.println(future.get(2, TimeUnit.SECONDS));
        // 立即获取结果，不等待，并设置默认值
//        System.out.println(future.getNow(999));
//        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
        // complete 是否立即打断get()方法， 若未得到结果，返回true,打断， 若已获取结果，则打断，返回false
        System.out.println(future.complete(44));
        System.out.println(future.get());

        threadPoolExecutor.shutdown();
    }
}
