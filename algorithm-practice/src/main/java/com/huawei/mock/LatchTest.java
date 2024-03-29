package com.huawei.mock;
 
import cn.hutool.json.JSONUtil;
import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
 
public class LatchTest {
 
    public static void main(String[] args) throws InterruptedException {
//        Runnable taskTemp = new Runnable() {
//
//       // 注意，此处是非线程安全的，留坑
//            private int iCounter;
//
//            @Override
//            public void run() {
//                for(int i = 0; i < 10; i++) {
//                    // 发起请求
////                    HttpClientOp.doGet("https://www.baidu.com/");
//                    iCounter++;
//                    System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };

        String url = "http://localhost:8080/exec?param=aaa";
//        String url = "http://localhost:8080/testApi";
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RunnableTask taskTemp = new RunnableTask(url,null, Collections.emptyMap());
            list.add(taskTemp);
        }


        LatchTest latchTest = new LatchTest();
        latchTest.startTaskAllInOnce(10, list);
    }

    public long startTaskAllInOnce(int threadNums, List<Runnable> task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for(int i = 0; i < threadNums; i++) {
            int finalI = i;
            Thread t = new Thread() {
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.get(finalI).run();
                        } finally {
                            // 将结束门减1，减到0时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        return endTime - startTime;
    }

    public long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for(int i = 0; i < threadNums; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // 将结束门减1，减到0时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        return endTime - startTime;
    }

    static class RunnableTask implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(RunnableTask.class);

        private String url;

        private String json;

        private Map<String, String> params;

        public RunnableTask(){}

        public RunnableTask(String url, String json) {
            this.url = url;
            this.json = json;
        }

        public RunnableTask(String url, String json, Map<String, String> params) {
            this.url = url;
            this.json = json;
            this.params = params;
        }

        @SneakyThrows
        @Override
        public void run() {
            HttpResponse httpResponse = HttpUtils.doGet(url, Collections.emptyMap(), params);
            logger.info("result is {}, ", IOUtils.toString(httpResponse.getEntity().getContent()));
        }
    }
}