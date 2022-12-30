package com.huawei.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author king
 * @date 2022/12/13-23:15
 * @Desc
 */
public class DynamicProgramming {

    @Test
    public void countCoins() {
        int n = 2934;
        int[] coins = {1, 2, 3};
//        System.out.println(countCoins(n,coins));
        System.out.println(bruteForceCoins(2934, coins));
    }

    /**
     * 在一个国家仅有1分，2分，3分硬币，将钱N兑换成硬币有很多种兑法。
     *
     * @param n
     * @param coins
     * @return
     */
    public int bruteForceCoins(int n, int[] coins) {
        int count = 0;
        // 由题意可知，3分硬币的数量在[0,n/3]以i表示，则2分硬币的数量为[0,(n-3*i)/2],剩余的为1分的硬币，
        // 可以依次遍历i,当3分硬币有i枚时，求出2分硬币的个数+1，求和后为本题的解
        for (int i = 0; i <= n / 3; i++) {
            count += (n - 3 * i) / 2 + 1;
        }
        return count;
    }

    public int countCoins(int n, int[] coins) {
        int[][] dp = new int[coins.length][37000];
        // 定义边界情况
        dp[0][0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < 37000; j++) {
                if (i - coins[j] >= 0) {
//                    dp[i][j]+=dp[i-coins[j]];
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[3][n];
    }


    @Test
    public void quickSort() {
//        int[] arr = {5, 1, 3, 2, 4};
        Random rd = new Random();
        int[] arr = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            arr[i] = rd.nextInt(1000000000);
        }
//        System.out.println(Arrays.toString(arr));
        long l = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        System.out.println(System.currentTimeMillis()-l);
//        System.out.println(Arrays.toString(arr));
    }

    public void quickSort(int[] arr, int i, int j) {
        if (i >= j) {
            return;
        }
        int base = partition(arr, i, j);
        quickSort(arr, i, base-1);
        quickSort(arr, base + 1, j);
    }

    private int partition(int[] arr, int i, int j) {
        // 5, 1, 3, 2, 4
        int base = arr[j];
        int p = i;
        for (int k = i; k < j; k++) {
            if (arr[k] < base) {
                swap(arr,p,k);
                p++;
            }
        }
        swap(arr,p,j);
        /*int start = i;
        int base = arr[i];
        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, i, start);*/
        return p;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void insertSort() {
        int[] arr = {5, 1, 3, 2, 4, 24, 2365, 23, 523, 423, 4, 124, 2432};
        System.out.println(Arrays.toString(arr));
        insertSortPro(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void insertSort(int[] arr) {
        // 思想: 将无序数组中的数据插入到有序数组中
        // 5，2，4，3，1
        int r = 1;
        while (r < arr.length) {
            int right = r;
            int left = right-1;
            while (left >= 0 ) {
                if (arr[right] < arr[left]) {
                    swap(arr,left,right);
                    left--;
                    right--;
                } else {
                    break;
                }
            }
            r++;
        }
    }

    public void insertSortPro(int[] arr) {
        if (arr.length<1) return;
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int val = arr[i];
            int j = i-1;
            for (; j >= 0; j--) {
                if (arr[j] > val) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = val;
        }
    }

}
