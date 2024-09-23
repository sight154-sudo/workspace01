package com.huawei.algorithm.leetcode.arrayPractice;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author king
 * @date 2023/6/29-23:33
 * @Desc
 */
public class CountSort {

    public static void main(String[] args) {
        long t = (int)3.9;
        t %=2;
        System.out.println(t);
    }

    @Test
    public void countSortTest() {
        int[] arr = {2, 3, 4, 1, 2, 3, 4, 3, 5, 0, 7, 0, 4, 5, 8, 3, 7, 9, 5, 9, 10};
        System.out.println(Arrays.toString(arr));
        countSort(arr);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < 100; i++) {
            validSortResult(1000, 10);
        }
    }

    /**
     * 计数排序  需要给定的参数有一定限制，比如人的年龄，高考学生的分数等等
     *
     * @param arr
     */
    public void countSort(int[] arr) {
        // 给定一个数组，数组中的元素范围在0-10之间，求排序后的结果
        int[] tmp = new int[11];
        for (int num : arr) {
            tmp[num]++;
        }
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
            while (tmp[i] > 0) {
                arr[index++] = i;
                tmp[i]--;
            }
        }
    }

    public void validSortResult(int len, int maxVal) {
        int[] arr1 = new int[len];
        int[] arr2 = new int[len];
        for (int i = 0; i < len; i++) {
            int num = (int) (Math.random() * (maxVal + 1));
            arr1[i] = num;
            arr2[i] = num;
        }
        int[] arr = new int[len];
        System.arraycopy(arr1, 0, arr, 0, len);
        countSort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr1));
                System.out.println("Error!! Fuck!!");
                return;
            }
        }
        System.out.println("Sort Right");
    }

    @Test
    public void radixSortTest() {
        int[] arr = {101, 132, 12, 341, 53, 3, 6, 74, 9};
        System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void radixSort(int[] arr) {
        int R = getArrMaxRadix(arr);
        // 有几位，遍历几次
        int[] tmp = new int[arr.length];
        for (int i = 1; i <= R; i++) {
            int[] count = new int[10];
            for (int j = 0; j < arr.length; j++) {
                int m = getDigit(arr[j], i);
                count[m]++;
            }
            // 计算前缀和
            for (int k = 1; k < count.length; k++) {
                count[k] = count[k - 1] + count[k];
            }
            // 从后往前遍历数组，计算每个数第几位该落的位置
            for (int p = arr.length - 1; p >= 0; p--) {
                int m = getDigit(arr[p], i);
                int index = --count[m];
                tmp[index] = arr[p];
            }
            System.arraycopy(tmp, 0, arr, 0, tmp.length);
        }
    }

    /**
     * 获取num第R位的值
     *
     * @param num
     * @param R
     * @return
     */
    private int getDigit(int num, int R) {
        int ans = num;
        while (R > 0) {
            ans = num % 10;
            num /= 10;
            R--;
        }
        return ans;
    }

    private int getArrMaxRadix(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int R = 0;
        while (max > 0) {
            max /= 10;
            R++;
        }
        return R;
    }



}

