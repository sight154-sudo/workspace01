package com.tuling;

/**
 * @author king
 * @date 2023/5/25-23:19
 * @Desc
 */
public class GCTest {
    public static void main(String[] args) {
        // -XX:+PrintGCDetails
        // -XX:PretenureSizeThreshold=1000000  设置大对象的大小 -XX:+UseSerialGC 这个参数只在 Serial 和ParNew两个收集器下
        byte[] allocation1,allocation2;
        byte[] allocation3,allocation4,allocation5;
        allocation1 = new byte[1024*1024*80];
        allocation2 = new byte[1024*1024*10];
        allocation3 = new byte[1024*1024];
        allocation4 = new byte[1024*1024];
        allocation5 = new byte[1024*1024];
    }
}
