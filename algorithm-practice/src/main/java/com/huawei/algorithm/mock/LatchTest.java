package com.huawei.algorithm.mock;
 
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class LatchTest {

    private static AtomicInteger iCounter = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {

        String json = "{\n" +
                "    \"id\":\"100002\",\n" +
                "    \"apiName\":\"api-v\",\n" +
                "    \"apiMethod\":\"post\",\n" +
                "    \"createtime\":\"2022-07-22 23:42:37\",\n" +
                "    \"modifytime\":\"2022-07-22 23:42:39\",\n" +
                "    \"deleteflag\":\"0\"\n" +
                "}";

        Random random = new Random();

        // 注意，此处是非线程安全的，留坑
        Runnable taskTemp = () -> {
                 for(int i = 0; i < 1; i++) {
                     // 发起请求
                     String url = "http://localhost:8080/add";
                     JSONObject obj = JSONUtil.parseObj(json);
                     obj.set("apiName","local-api"+System.currentTimeMillis()+"-"+random.nextInt(100000));
                     obj.set("apiUUID", random.nextInt(200000)+100001);
                     String s = obj.toString();
                     HttpUtils.doPost(url,s);
 //                    HttpClientOp.doGet("https://www.baidu.com/");
                     System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter.getAndAdd(1));
                     try {
                         Thread.sleep(100);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             };
 
        LatchTest latchTest = new LatchTest();
        latchTest.startTaskAllInOnce(10, taskTemp);
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
}