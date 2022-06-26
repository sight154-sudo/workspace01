package com.huawei.algorithm;/**
 * @author king
 * @date 2022/2/12-0:56
 * @Desc
 */

import org.junit.Test;

import java.util.Scanner;

/**
 * @program: springboot-demo
 *
 * @author: Mr.King
 *
 * @create: 2022-02-12 00:56
 **/

public class StringPractice {

    @Test
    public void testStrstr(){
        String src = "";
        String target = "";
        int index = strstr(src, target);
        System.out.println("index = " + index);
    }

    /**
     * 实现strstr函数，找出target位于src的位置
     * @param src
     * @param target
     * @return
     */
    public int strstr(String src,String target) {
        if(src.length() < target.length()) {
            return -1;
        }
        int len = target.length();
        for (int i = 0; i < src.length()-len+1; i++) {
            boolean flag = true;
            for (int j = 0; j < len; j++) {
                if(src.charAt(i+j) != target.charAt(j)) {
                   flag = false;
                   break;
                }
            }
            if(flag){
                return i;
            }
        }
        return -1;
    }
    Scanner sc = new Scanner(System.in);

    @Test
    public void testScanner() {
        while(sc.hasNext()) {
            System.out.println(sc.next());
        }
    }
}
