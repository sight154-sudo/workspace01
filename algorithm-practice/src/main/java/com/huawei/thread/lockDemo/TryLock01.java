package com.huawei.thread.lockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author king
 * @date 2022/2/19-22:43
 * @Desc lock.tryLock()  尝试获取锁
 */
public class TryLock01 {

    private static Lock lock = new ReentrantLock();


    public static void main(String[] args) {

        TryLock01 tryLock01 = new TryLock01();
        Thread t1 = new Thread(() -> {
//            tryLock01.tryLock();
//            tryLock01.tryLockAndParam();
        });
        t1.start();
        Thread t2 = new Thread(() -> {
//            tryLock01.tryLock();
//            tryLock01.tryLockAndParam();
        });

        t2.start();
        t2.interrupt();
    }


    public static void tryLockAndParam(){
        try {
            if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到锁");
                    Thread.sleep(2000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放锁");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + "未获取到锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void tryLock() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                Thread.sleep(3000);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "未获取到锁");
        }
    }
}
