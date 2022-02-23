package com.huawei.thread;

/**
 * @author king
 * @date 2022/2/22-0:26
 * @Desc
 */
public class ListThread {

    public static void main(String[] args) throws InterruptedException{

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "run ....");
        }, "线程1");
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "run ....");
        }, "线程2");
        Thread t3 = new Thread(() -> {
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "run ....");
        }, "线程3");


        t1.start();
        t2.start();
        t3.start();



        System.out.println(Thread.currentThread().getName()+"run ...");
    }
}
