package com.huawei.algorithm.leetcode.dataStructures;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author king
 * @date 2023/6/23-17:37
 * @Desc
 */
public class HeapPractice {

    @Test
    public void maxCoverTest() {
        int[][] arr = {{4, 5}, {1, 4}, {1, 7}, {3,4}, {1, 3}, {2, 4}, {3, 5}};
        System.out.println(maxCover(arr));
        for (int i = 0; i < 100; i++) {

            validMaxCover(10, 20);
        }
    }

    /**
     * 题目描述
     * 给定很多线段，每条线段都有两个数组 [start, end]，表示线段的开始位置和结束位置，左右都是闭区间。规定：
     * 线段开始和结束位置一定都是整数值；
     * 线段重合区域的长度必须 >=1 （比如(1,3) 和 (3,5) 不算重合）
     * 返回线段最多重合区域中，包含了几条线段。
     * 类似的题目，最多需要准备多少间教室
     *
     * @param arr
     * @return
     */
    public int maxCover01(int[][] arr) {
        // 暴力解法
        int min = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i][0]);
            max = Math.max(max, arr[i][1]);
        }
        int ans = 0;
        for (double i = min + 0.5; i < max; i++) {
            int num = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][0] < i && arr[j][1] > i) {
                    num++;
                }
            }
            ans = Math.max(ans, num);
        }
        return ans;
    }

    public int maxCover(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek() <= arr[i][0]) {
                priorityQueue.poll();
            }
            priorityQueue.offer(arr[i][1]);
            ans = Math.max(ans, priorityQueue.size());
        }
        return ans;
    }


    public void validMaxCover(int len, int maxVal) {
        int[][] arr1 = new int[len][];
        int[][] arr2 = new int[len][];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 2; j++) {
                int[] tmp = new int[2];
                int m = (int) (Math.random() * (maxVal));
                int n = (int) ((maxVal - m + 1) * Math.random()) + m;
                while (n <= m) {
                    n = (int) ((maxVal - m + 1) * Math.random()) + m;
                }
                tmp[0] = m;
                tmp[1] = n;
                arr1[i] = tmp;
                arr2[i] = tmp;
            }
        }
        int max1 = maxCover01(arr1);
        int max2 = maxCover(arr2);
        if (max1 == max2) {
            System.out.println("Good code");
        } else {
            System.out.println("Fk all");
        }
    }
}
