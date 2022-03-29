package com.huawei.algorithm;

import org.junit.Test;

/**
 * @author king
 * @date 2022/3/26-17:08
 * @Desc
 */
public class NumPractice {

    @Test
    public void testIsPalindrome() {
        boolean palindrome = isPalindrome(-121);
        System.out.println("palindrome = " + palindrome);
    }

    /**
     * 判断是否是回文数
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // 使用除数与余数相比较
        if(x<0 || x%10 == 0)return false;
        int i = 0;
        int num = 0;
        while(num < x){
            i = x%10;
            num = num*10 + i;
            x = x/10;
        }
        return num==x?true:(num/10==x?true:false);
    }

}
