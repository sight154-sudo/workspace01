package com.jike;

/**
 * @author king
 * @date 2023/4/25-23:07
 * @Desc
 */
public class ByteCodeDemo {

    public static Integer a = 888;

    public static Hello hello = new Hello();

    public static void main(String[] args) {
        int a = 10;
        double b = 20d;
        double c = (a + b) / 2;
        for (int i = 0; i < c; i += 2) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
