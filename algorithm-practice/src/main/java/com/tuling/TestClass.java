package com.tuling;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author king
 * @date 2023/5/17-23:22
 * @Desc
 */
public class TestClass {
    public static void testSys(String msgs) {
        System.out.println("java 反射机制调用方法执行,msg:" + msgs);
    }

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        System.out.println("-------------获取当前类-----------------------");
        String msg = "hello";
        Class<? extends TestClass> aClass1 = getClass();
        Method[] methods = aClass1.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("testSys")) {
                System.out.println("匹配成功，开始执行反射方法，methodName:" + method.getName());
                System.out.println("aClass1:" + aClass1);
                System.out.println("aClass1Name:" + aClass1.getName());
                System.out.println("aClass1loaderName" + aClass1.getClassLoader().getClass().getName());
                method.invoke(aClass1.newInstance(), msg);
                System.out.println("执行完成.....");
            }
        }
    }

    public void testSys2(String msgs) {
        System.out.println("java 反射机制调用方法执行,msg:" + msgs);
    }

    @Test
    public void test2() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.println("-------------获取当前类-----------------------");
        String msg = "hello";
        Class<? extends Test> aClass1 = (Class<? extends Test>) getClass();
        Method[] methods = aClass1.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("testSys2")) {
                System.out.println("匹配成功，开始执行反射方法，methodName:" + method.getName());
                System.out.println("aClass1:" + aClass1);
                System.out.println("aClass1Name:" + aClass1.getName());
                method.invoke(aClass1.newInstance(), msg);
                System.out.println("执行完成.....");
            }
        }
    }
}
