package com.tuling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author king
 * @date 2023/5/18-0:16
 * @Desc
 */
public class HeadTest {

    byte[] b = new byte[1024*100];

    public static void main(String[] args) {
        // -Xms128M 堆的最小值， -Xmx128M 堆的最大值  -Xmn64M 设置新生代的大小 -XX:MetaspaceSize=256M 设置元空间大小
        // -XX:MaxMetaspaceSize=256M 元空间最大值
        List<HeadTest> headTests = new ArrayList<>();
        long total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("total = " + total);
        System.out.println("maxMemory = " + maxMemory);
        for (String arg : args) {
            System.out.println(arg);
        }
        String profile = System.getenv("profile");
        System.out.println("profile = " + profile);
        String key1 = System.getProperty("key1");
        System.out.println("key1 = " + key1);
        String key2 = System.getProperty("key2");
        System.out.println("key2 = " + key2);
        String javaHome = System.getenv("java_home");
        System.out.println("javaHome = " + javaHome);
        String env = System.getenv("env");
        System.out.println("env = " + env);
        String env2 = System.getenv("env2");
        System.out.println("env2 = " + env2);
        System.out.println(System.getenv());
        while (true) {
            headTests.add(new HeadTest());
            try {
                TimeUnit.MILLISECONDS.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}
