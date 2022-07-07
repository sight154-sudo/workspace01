package com.huawei.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * @author king
 * @date 2022/7/6-0:41
 * @Desc
 */
public class CompletableFutureTimeout {

    public static final Logger log = LoggerFactory.getLogger(CompletableFutureTimeout.class);

    private static ExecutorService executorService = LivedataThreadFactory.init();

    private static ScheduledExecutorService scheduledExecutorService = LivedataThreadFactory.initSchedule();

    public static <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        // timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
        scheduledExecutorService.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

    /**
     * 哪个先完成 就apply哪一个结果 这是一个关键的API,exceptionally出现异常后返回默认值
     *
     * @param t
     * @param future
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> completeOnTimeout(T t, CompletableFuture<T> future, long timeout, TimeUnit unit) {
        final CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        return future.applyToEither(timeoutFuture, Function.identity()).exceptionally((throwable) -> t);
    }

    /**
     * 哪个先完成 就apply哪一个结果 这是一个关键的API，不设置默认值，超时后抛出异常
     *
     * @param t
     * @param future
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> orTimeout(T t, CompletableFuture<T> future, long timeout, TimeUnit unit) {
        final CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        return future.applyToEither(timeoutFuture, Function.identity()).exceptionally((throwable) -> t);
    }

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            log.info("async task is running...");
            try {
                Thread.sleep(3000);
                if (Thread.currentThread().isInterrupted()) {
                    log.info("Thread interrupt...");
                }
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            log.info("async task is ending...");
            return "run ending";
        }, executorService);
        /*CompletableFuture<String> within = CompletableFutureTimeout.completeOnTimeout("超时...", completableFuture, 1, TimeUnit.SECONDS);

        CompletableFuture<String> futureStr = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "正常执行";
        });*/

        CompletableFuture<String> withinStr = CompletableFutureTimeout.completeOnTimeout("异常执行", completableFuture, 1, TimeUnit.SECONDS);
        try {
            System.out.println(withinStr.get(5,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static <T> CompletableFuture<T> timeout(long timeout, TimeUnit unit, String message) {
        CompletableFuture<T> ret = new CompletableFuture<>();
        scheduledExecutorService.schedule(() -> ret.completeExceptionally(new TimeoutException(message)), timeout, unit);
        return ret;
    }
}
