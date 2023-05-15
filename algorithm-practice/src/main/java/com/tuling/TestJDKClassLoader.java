package com.tuling;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @author king
 * @date 2023/5/5-23:52
 * @Desc
 */
public class TestJDKClassLoader {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESedeKeyFactory.class.getClassLoader().getClass().getName());
        System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());
        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader sysClassLoader = extClassLoader.getParent();
        System.out.println("appClassLoader = " + appClassLoader);
        System.out.println("extClassLoader = " + extClassLoader);
        System.out.println("sysClassLoader = " + sysClassLoader);
        System.out.println();

        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));
    }
}
