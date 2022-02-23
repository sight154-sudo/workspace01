package com.huawei.thread.volatiledemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author king
 * @date 2022/2/19-22:30
 * @Desc
 */
public class X implements Runnable {

    private Lock lock = new ReentrantLock();

    int value;

    @Override
    public void run() {
        try{
            lock.lock();
            value =  1 + get();
        }finally {
            lock.unlock();
        }
    }

    public int get(){
        try{
            lock.lock();
            return 2;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        X x = new X();
        Thread t1 = new Thread(x);

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(x.value);
    }
}
