package com.huawei.algorithm.leetcode.arrayPractice;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author king
 * @date 2023/6/3-17:18
 * @Desc
 */
public class SortPractice {


    @Test
    public void chooseSortTest() {
        int[] arr = {5, 3, 1, 3, 5, 2};
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
        validResult(10, 100);
    }

    public void chooseSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int tmp = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[tmp]) {
                    tmp = j;
                }
            }
            swap(arr, i, tmp);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void validResult(int len, int maxVal) {
        int[] arr1 = new int[len];
        int[] arr2 = new int[len];
        for (int i = 0; i < len; i++) {
            int num = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * maxVal);
            arr1[i] = num;
            arr2[i] = num;
        }
        insertSort(arr1);
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

    public void bubblingSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public void insertSort(int[] arr) {
        // 将无序数组添加到有序数组中
        if (arr.length == 0 || arr.length == 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j - 1 >= 0 && arr[j - 1] > arr[j]; j--) {
                swap(arr, j - 1, j);
            }
        }
    }

    @Test
    public void binarySearchTest() {
        int[] arr = {1, 3, 5, 6, 8, 9};
        System.out.println(binarySearch(arr, 4));
    }

    public int binarySearch(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (arr[mid] > target) {
                j = mid - 1;
            } else if (arr[mid] < target) {
                i = mid + 1;
            } else {
                return mid;
            }
        }
        return arr[i] == target ? i : -1;
    }

    @Test
    public void searchGreatTargetTest() {
        int[] arr = {1,2,3,4,4,4,4,6};
        System.out.println(searchGreatTarget(arr, 5));
    }

    public int searchGreatTarget(int[] arr, int target) {
        // 在有序数组中，找到>=某个数的最左侧位置
        if (arr.length == 0) {
            return -1;
        }
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (arr[mid] > target) {
                j = mid -1 ;
            } else if (arr[mid] < target) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
    }
    public int searchLessTarget(int[] arr, int target) {
        // 在有序数组中，找到>=某个数的最左侧位置
        if (arr.length == 0) {
            return -1;
        }
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (arr[mid] > target) {
                j = mid -1 ;
            } else if (arr[mid] < target) {
                i = mid + 1;
            } else {
                i = mid;
            }
        }
        return i;
    }

    public int partMin(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return -1;
        }
        int len = arr.length;
        if (len > 2 && arr[0] < arr[1]) {
            return 0;
        }
        if (arr[len-1] < arr[len -2]) {
            return len-1;
        }
        int i = 0;
        int j = len-1;
        while (i < j) {
            int mid = i+ (j-i)/2;
            if (arr[mid] > arr[mid-1]) {
                j = mid - 1;
            } else if (arr[mid] < arr[mid+1]) {
                i = mid +1;
            } else {
                return mid;
            }
        }
        return i;
    }

    @Test
    public void swap() {
        int a = 10;
        int b = 10;
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println(a);
        System.out.println(b);
    }
}
