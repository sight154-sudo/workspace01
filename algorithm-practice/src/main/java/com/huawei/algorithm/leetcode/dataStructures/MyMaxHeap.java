package com.huawei.algorithm.leetcode.dataStructures;

import java.util.Arrays;

/**
 * 大顶堆
 * @author king
 * @date 2023/6/14-22:52
 * @Desc
 */
public class MyMaxHeap {
    int[] arr = new int[1024];
    int heapSize = 0;

    public void heapInsert(int num) {
        arr[++heapSize] = num;
        int index = heapSize;
        // 上沉元素
        while (index/2 != 0 && arr[index/2] < num) {
            swap(arr, index, index/2);
            index/=2;
        }
    }

    public int heapify() {
        if (heapSize == 0) {
            throw new RuntimeException("堆中没有元素了");
        }
        // 下沉
        int ans = arr[1];
        arr[1] = arr[heapSize--];
        int index = 1;
        int maxPos = index;
        while (index < heapSize) {
            if (index *2 < heapSize && arr[index*2] > arr[index]) maxPos = index*2;
            if (index*2+1 < heapSize && arr[index*2+1] > arr[maxPos]) maxPos = index*2+1;
            if (maxPos == index) {
                break;
            }
            swap(arr, index, maxPos);
            index = maxPos;
        }
        return ans;
    }
// https://subapi.rss-node.com/sub?target=clash&interval=129600&filename=Hitun&url=https%3A%2F%2Frss-node.com%2Flink%2FMsrzuaedA1YkeirI%3Fmu%3D1
    public int peek() {
        if (heapSize == 0) {
            throw new RuntimeException("堆中没有元素了");
        }
        return arr[1];
    }




    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        MyMaxHeap myMaxHeap = new MyMaxHeap();
        int[] arr = {5,3,2,5,6,2,1,6};
        for (int num : arr) {
            myMaxHeap.heapInsert(num);
        }
        for (int i = arr.length-1; i >= 0; i--) {
            arr[i] = myMaxHeap.heapify();
        }
        System.out.println(Arrays.toString(arr));
    }
}
