package com.huawei.thread.volatiledemo;

/**
 * @author king
 * @date 2022/2/14-23:37
 * @Desc
 */
public class VolatileDemo {

    int x = 0;
    volatile boolean v = false;

    public void write(){
        x = 45;
        v = true;
    }

    public void read(){
        if(v){
            System.out.println(x);
        }
    }


    public static void main1(String[] args) {
        final VolatileDemo volatileDemo = new VolatileDemo();
        new Thread(()->{
            volatileDemo.write();
        }).start();
        new Thread(()->{
            volatileDemo.read();
        }).start();

    }


    public static void main(String[] args) throws InterruptedException {
        VolatileDemo volatileDemo = new VolatileDemo();
        Thread t = new Thread(() -> {
            volatileDemo.x = 20;
        });
        t.start();
        t.join();
        System.out.println(volatileDemo.x);
    }
}
