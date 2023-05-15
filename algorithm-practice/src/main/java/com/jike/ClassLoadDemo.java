package com.jike;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @author king
 * @date 2023/4/23-22:47
 * @Desc
 */
public class ClassLoadDemo {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL urL : urLs) {
            System.out.println("===>" +urL.toExternalForm());
        }



    }

    public static void printClassLoader(String name, ClassLoader loader) {
        if (loader != null) {
            System.out.println(name + loader.toString());

        } else {
            System.out.println(name + "null");
        }
    }


}
