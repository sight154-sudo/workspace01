package com.tuling;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author king
 * @date 2023/5/9-0:06
 * @Desc
 */
public class MyTestClassLoaderTest {


    static class MyTestClassLoader extends ClassLoader {
        private String path;

        public MyTestClassLoader(String path) {
            this.path = path;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            String className = name;
            name = name.replaceAll("\\.","/");
            byte[] data = loadByteCode(name);
            return defineClass(className, data, 0 , data.length);
        }

        private byte[] loadByteCode(String name) {
            try(FileInputStream ipt = new FileInputStream(new File(path, name+".class"));
                ByteArrayOutputStream out = new ByteArrayOutputStream();
            ) {
                byte[] data = new byte[ipt.available()];
                ipt.read(data);
                out.write(data);
                return data;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            synchronized (super.getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        if (name.startsWith("com.tuling")) {
                            c = findClass(name);
                        } else {
                            c = getParent().loadClass(name);
                        }
                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        MyTestClassLoader myTestClassLoader = new MyTestClassLoader("d:\\test\\");
        Class<?> clazz = myTestClassLoader.loadClass("com.tuling.User1");
        Method method = clazz.getMethod("sout", null);
        method.invoke(clazz.newInstance(),null);
        System.out.println(clazz.getClassLoader().getClass().getName());
        System.out.println(clazz.getClassLoader().getParent().getClass().getName());
        MyTestClassLoader myTestClassLoader2 = new MyTestClassLoader("d:\\test1\\");
        Class<?> clazz2 = myTestClassLoader2.loadClass("com.tuling.User1");
        Method method2 = clazz2.getMethod("sout", null);
        method2.invoke(clazz.newInstance(),null);
        System.out.println(clazz.getClassLoader().getClass().getName());
        System.out.println(clazz.getClassLoader().getParent().getClass().getName());
    }
}
