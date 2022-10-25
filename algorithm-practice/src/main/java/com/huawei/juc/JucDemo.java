package com.huawei.juc;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author king
 * @date 2022/9/8-23:11
 * @Desc
 */
public class JucDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("pdd"),
            new NetMall("taobao")
    );

    public static List<String> getMallStep(String productName) {
        return list.stream()
                .map(mall -> String.format("%s in %s price is %.3f", productName, mall.mallName, mall.computePrice(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getMallAsync(String productName) {
        return list.stream()
                .map(mall -> CompletableFuture.supplyAsync(() ->
                        String.format("%s in %s price is %.3f", productName, mall.mallName, mall.computePrice(productName)))).collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) throws Exception {
//        m1();
//        m2();
//        m3();
//        m4();
//        m5();
//        System.out.println(ThreadLocalRandom.current().nextDouble()*2+'a');


        long start1 = System.currentTimeMillis();
        List<String> list1 = getMallStep("mysql");
        for (String s : list1) {
            System.out.println(s);
        }
        long end1 = System.currentTimeMillis();
        System.out.println(String.format("programmer cost %s ms", end1 - start1));

        System.out.println();

        long start2 = System.currentTimeMillis();
        List<String> list2 = getMallAsync("mysql");
        for (String s : list2) {
            System.out.println(s);
        }
        long end2 = System.currentTimeMillis();
        System.out.println(String.format("programmer cost %s ms", end2 - start2));
    }

    static class NetMall{
        private String mallName;

        public NetMall(String mallName) {
            this.mallName = mallName;
        }

        public String getMallName() {
            return mallName;
        }

        public void setMallName(String mallName) {
            this.mallName = mallName;
        }

        public double computePrice(String productName) {
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            return ThreadLocalRandom.current().nextDouble()*2 + productName.charAt(0);
        }
    }

    private static void m5() throws InterruptedException, java.util.concurrent.ExecutionException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 1;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println(v);
            }
        }).exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });


        System.out.println("--- Main run --- ");

        future.get();

        executor.shutdown();
    }

    private static void m4() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture.supplyAsync(() -> {
            System.out.println(" 首先开始烧水");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, executor).thenApply(integer -> String.valueOf(integer))
                .whenComplete((v, e) -> {
                    if (e != null) {
                        System.out.println(" programming is error");
                    } else {
                        System.out.println(v);
                    }
                }).exceptionally(e -> {
            System.out.println(e);
            return null;
        });

        System.out.println("main running----");
        executor.shutdown();
    }


    @Test
    public void testSupplier() {
        // 测试生产者函数式编程
        int i = getInteger(() -> 1 + 2);

        String s = getString(() -> 1 + "  ");
        System.out.println(i);
        System.out.println(s);

        int[] arr = {123, 124, 42, 123, 412};

        int max = getMax(() -> {
            int m = arr[0];
            for (int j = 1; j < arr.length; j++) {
                m = Math.max(m, arr[j]);
            }
            return m;
        });
        System.out.println(max);
    }

    public int getMax(Supplier<Integer> supplier) {
        return supplier.get();
    }

    public int getInteger(Supplier<Integer> supplier) {
        return supplier.get();
    }

    public String getString(Supplier<String> supplier) {
        return supplier.get();
    }

    @Test
    public void testConsumer() {
        // 测试消费者函数式编程
//        operatorString("lisi", s-> System.out.println(s));
//        operatorString("zhangsan", StringUtils::reverse, System.out::println);
        String[] arr = {"林青霞,30", "张曼玉,35", "王祖贤,33"};
        printConsumer(arr, s -> System.out.printf("姓名 : %5s", s.split(",")[0]),
                s -> System.out.printf("\t 年龄 : %s \n", s.split(",")[1]));
    }

    public void printConsumer(String[] arr, Consumer<String> con1, Consumer<String> con2) {
        for (String s : arr) {
            con1.andThen(con2).accept(s);
        }
    }

    public void operatorString(String name, Consumer<String> con1) {
        con1.accept(name);
    }

    public void operatorString(String name, Consumer<String> con1, Consumer<String> con2) {
        con1.andThen(con2).accept(name);
    }

    @Test
    public void testPredicate() {
        // 判断参数是否满足条件 boolean
        boolean b1 = checkString("hello", s -> s.length() > 8);
        System.out.println("b1 = " + b1);

        boolean b2 = checkString("hello world", s -> s.length() < 8, s -> s.startsWith("he"));
        System.out.println("b2 = " + b2);
        String[] arr = {"林青霞,30", "张曼玉,35", "王祖贤,33"};
        List<String> list = practicePredicate(arr,
                s -> s.split(",")[0].length() > 2,
                s -> Integer.valueOf(s.split(",")[1]) > 33);
        list.forEach(System.out::println);
    }

    public List<String> practicePredicate(String[] arr, Predicate<String> pre1, Predicate<String> pre2) {
        List<String> list = new ArrayList<>();
        for (String s : arr) {
            if (pre1.and(pre2).test(s)) {
                list.add(s);
            }
        }
        return list;
    }

    public boolean checkString(String str, Predicate<String> pre) {
//        return pre.test(str);
        return pre.negate().test(str);
    }

    public boolean checkString(String str, Predicate<String> pre1, Predicate<String> pre2) {
        return pre1.and(pre2).test(str);
    }

    @Test
    public void testFunction() {
        // 类型转换 r->t
        convert("12", Integer::valueOf);
        convert(13, i-> String.valueOf(i+335));

        convert("12", Integer::valueOf, i-> String.valueOf(i+111));

        int i = convertPratice("林青霞,30", s -> {
            return Integer.valueOf(s.split(",")[1]).intValue() + 70;
        });
        System.out.println("i = " + i);
    }

    public int convertPratice(String s, Function<String, Integer> func) {
        Integer i = func.apply(s);
        return i;
    }

    public void convert(String s, Function<String, Integer> func) {
        Integer i = func.apply(s);
        System.out.println("i = " + i);
    }

    public void convert(Integer i, Function<Integer, String> func) {
        String s = func.apply(i);
        System.out.println("s = " + s);
    }

    public void convert(String s, Function<String, Integer> func, Function<Integer, String> func2 ) {
        String res = func.andThen(func2).apply(s);
        System.out.println("res = " + res);
    }


    private static void n3() throws InterruptedException, java.util.concurrent.ExecutionException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                10,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " completableFuture running ...");
        });

        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " completableFuture running ...");
        }, executor);


        System.out.println(f1.get());

        System.out.println(f2.get());

        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> {

            System.out.println(Thread.currentThread().getName() + " completableFuture supplyAsync running ...");
            return 11;
        });
        CompletableFuture<Integer> f4 = CompletableFuture.supplyAsync(() -> {

            System.out.println(Thread.currentThread().getName() + " completableFuture supplyAsync running ...");
            return 12;
        }, executor);

        System.out.println(f3.get());
        System.out.println(f4.get());


        executor.shutdown();
    }

    private static void m2() throws InterruptedException, java.util.concurrent.ExecutionException {
        Runnable r = () -> {
        };
        FutureTask<Integer> futureTask = new FutureTask(() -> {
            System.out.println(" runnable is running ---");
            TimeUnit.SECONDS.sleep(1);
            return 1024;
        });
        Thread t = new Thread(futureTask);
        t.start();

//        System.out.println(futureTask.get()); // 工作中尽量不要使用此方法，会一直阻塞主线程
//        System.out.println(futureTask.get(1,TimeUnit.SECONDS));  // 设置等待时间、

        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                System.out.println("waitting ....");
            }
        }
    }

    private static void m1() throws InterruptedException {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in : \t "
                    + (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true) {

            }
        }, "");
        a.setDaemon(true);
        a.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + " \t" + "task is over");
    }
}
