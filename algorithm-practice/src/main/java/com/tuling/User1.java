package com.tuling;

import lombok.ToString;

/**
 * @author king
 * @date 2023/5/11-22:36
 * @Desc
 */
@ToString
public class User1 {

    int a;
    String s;

    public User1(int a, String s) {
        this.a = a;
        this.s = s;
    }

    public User1() {
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void sout(){
        System.out.println("另一个版本的User1 --- run user1");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("该对象被回收...."+ this.toString());
        super.finalize();
    }
}
