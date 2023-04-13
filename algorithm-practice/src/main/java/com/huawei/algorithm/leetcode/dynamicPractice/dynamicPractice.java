package com.huawei.algorithm.leetcode.dynamicPractice;

import org.junit.Test;

/**
 * @author king
 * @date 2023/4/9-15:58
 * @Desc
 */
public class dynamicPractice {

    @Test
    public void climbStairsTest() {
        System.out.println(climbStairs2(4));
    }
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n-1) + climbStairs(n-2);
    }

    int[] arr = new int[50];

    public int climbStairs2(int n) {
        // 递归加枝减 减少重复计算
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (arr[n] != 0) {
            return arr[n];
        }
        arr[n] = climbStairs2(n-1) + climbStairs2(n-2);
        return arr[n];
    }
}
