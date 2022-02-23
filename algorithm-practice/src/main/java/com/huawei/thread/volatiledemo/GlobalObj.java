package com.huawei.thread.volatiledemo;

/**
 * @author king
 * @date 2022/2/15-0:14
 * @Desc
 */
public class GlobalObj {
    int x = 10;
    GlobalObj obj;
    public GlobalObj(){
        obj = this;
    }

    public static void main(String[] args) {
        System.out.println(new GlobalObj().obj.x);
    }
}
