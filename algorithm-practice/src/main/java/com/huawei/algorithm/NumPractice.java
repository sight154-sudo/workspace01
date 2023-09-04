package com.huawei.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // 使用除数与余数相比较
        if (x < 0 || x % 10 == 0) return false;
        int i = 0;
        int num = 0;
        while (num < x) {
            i = x % 10;
            num = num * 10 + i;
            x = x / 10;
        }
        return num == x ? true : (num / 10 == x ? true : false);
    }

    public int[] maxYueMinBei(int m, int n) {
        m = m > n ? m : n;
        n = m > n ? n : m;
        while (m % n != 0) {
            int r = m % n;
            m = n;
            n = r;
        }
        int bei = m * n / n;
        return new int[]{n, bei};
    }

    @Test
    public void maxYueMinBeiTest() {
        int m = 45;
        int n = 24;
        System.out.println(Arrays.toString(maxYueMinBei(m, n)));
    }

    @Test
    public void printTest() {
        print(3, 4);
    }

    public void print(int row, int col) {
        /**
         * +---+---+
         * |   |   |
         * +---+---+
         */
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("+---");
            }
            System.out.print("+");
            System.out.println();
            for (int j = 0; j < col; j++) {
                System.out.print("|   ");
            }
            System.out.print("|");
            System.out.println();
        }
        for (int i = 0; i < col; i++) {
            System.out.print("+---");
        }
        System.out.print("+");
        System.out.println();
    }

    @Test
    public void patternTest() {
        String s = "31-223=4";
        Pattern pattern1 = Pattern.compile("((\\d)+)");
        Pattern pattern = Pattern.compile("(\\+|-)");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String group = matcher.group(1);
            System.out.println(group);
        }
    }

    @Test
    public void normalPowerTest() {
        System.out.println(normalPower(2, 100));
    }

    /**
     * 求base的power次方的结果的后三位数
     *
     * @param base
     * @param power
     * @return
     */
    public int normalPower(int base, int power) {
        long result = 1;
        // 若在过程中不做处理，结果会溢出
        /*for (int i = 0; i < power; i++) {
            result = result * base;
        }*/
        // 若取模的运算规则   (a*b)%p = ((a%p) * (b%p)) % p
        for (int i = 0; i < power; i++) {
            result = result * base;
            result = result % 1000;
        }
        return (int) (result % 1000);
    }

    int mod = 1000;

    public int normalPowerPlus(int base, int power) {
        return divide(base, power);
    }

    @Test
    public void normalPowerPlusTest() {
        int base = 2;
        int power = 100;
        System.out.println(normalPowerPlus(base, power));
    }

    public int divide(int b, int p) {
        if (p == 0) {
            return 1;
        } else if ((p & 1) == 1) {
            return b % mod * divide(b, p - 1);
        } else {
            return divide((b % mod) * (b % mod), p / 2) % mod;
        }
    }

    /**
     * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
     * leetCode 372
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        return 1;
    }
}
