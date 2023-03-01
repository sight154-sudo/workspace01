package com.huawei;

import java.util.Scanner;

/**
 * @author king
 * @date 2022/6/17-21:43
 * @Desc
 */
public class DemoTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = null;
        while (sc.hasNext()) {
            s1 = sc.next();
        }
        System.out.println(s1.length());
    }
}
