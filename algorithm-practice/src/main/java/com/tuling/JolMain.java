package com.tuling;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author king
 * @date 2023/5/24-0:39
 * @Desc
 */
public class JolMain {
    public static void main(String[] args) {
        ClassLayout classLayout = ClassLayout.parseInstance(new Object());
        System.out.println(classLayout.toPrintable());
        ClassLayout classLayout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(classLayout1.toPrintable());
        ClassLayout classLayout2 = ClassLayout.parseInstance(new A());
        System.out.println(classLayout2.toPrintable());
    }

    public static class A{
        int id;
        String name;
        byte b;
        Object obj;
    }
}
