package com.huawei.concurrent;


import com.huawei.mock.LatchTest2;
import com.sun.java.swing.plaf.windows.WindowsOptionPaneUI;

import java.util.concurrent.CyclicBarrier;

/**
 * @author king
 * @date 2022/8/31-23:02
 * @Desc
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        Runnable runnable = () -> doWork();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, runnable);
        for (int i = 0; i < 10; i++) {
            new Thread(new LatchTest2.CounterTask(cyclicBarrier)).start();
        }
    }

    public static void doWork() {
        System.out.println(Thread.currentThread().getName()+"准备完毕...");
    }
}
