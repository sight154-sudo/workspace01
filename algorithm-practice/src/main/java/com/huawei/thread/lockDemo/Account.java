package com.huawei.thread.lockDemo;

import java.util.Locale;

/**
 * @author king
 * @date 2022/2/23-0:20
 * @Desc
 */
public class Account {

    private String name;
    private Integer balance;

    public Account() {
    }

    public Account(String name,Integer balance) {
        this.name = name;
        this.balance = balance;
    }

    void transfer(Account target, int res) throws InterruptedException {
        if (this.balance > res) {
            this.balance -= res;
//            Thread.sleep(100);
            target.balance += res;
        }
        System.out.println(String.format(Locale.ROOT,"%s: %d  %s: %d",this.name,this.balance,target.name,target.balance));
    }

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account("A",2000);
        Account b = new Account("B",2000);
        Account c = new Account("C",2000);
        Thread t1 = new Thread(()->{
            try {
                for (int i = 0; i < 20; i++) {
                    a.transfer(b,100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            try {
                for (int i = 0; i < 20; i++) {
                    b.transfer(c,100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(String.format(Locale.ROOT,"A: %d  B: %d  C: %d",a.balance,b.balance,c.balance));
    }
}
