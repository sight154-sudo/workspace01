package com.tuling;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author king
 * @date 2023/5/5-23:46
 * @Desc
 */
public class TestDynamicClass {

    static {
        System.out.println("==========static load TestDynamicClass==========");
    }

    public TestDynamicClass() {
        System.out.println("============constructor TestDynamicClass===============");
    }

    public static void main(String[] args) {
        new  A();
        System.out.println("====run main===");
        B b = null;
//        System.out.println(System.getProperty("java.class.path"));
    }

}


class A{
    static {
        System.out.println("==========static load A==========");
    }

    public A() {
        System.out.println("============constructor A===============");
    }
}
class B{
    static {
        System.out.println("==========static load B==========");
    }

    public B() {
        System.out.println("============constructor B===============");
    }
}
