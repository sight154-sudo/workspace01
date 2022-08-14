package com.huawei.algorithm.sort;

import java.util.concurrent.locks.LockSupport;

/**
 * @author king
 * @date 2022/8/9-23:04
 * @Desc
 */
public class SortDemo {
    /**
     * 爬楼梯 70
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <=n ; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
