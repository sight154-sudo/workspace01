package com.huawei.algorithm.leetcode.arrayPractice;

import com.huawei.algorithm.leetcode.linkedPractice.BinaryTreeNode;
import com.huawei.algorithm.leetcode.linkedPractice.ListNode;
import com.huawei.algorithm.leetcode.linkedPractice.NodeUtils;
import lombok.val;
import org.antlr.v4.runtime.tree.Tree;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

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
        int[] arr = new int[len];
        System.arraycopy(arr1, 0, arr, 0, len);
        quickSort02(arr1);
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
        int[] arr = {1, 2, 3, 4, 4, 4, 4, 6};
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
                j = mid - 1;
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
                j = mid - 1;
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
        if (arr[len - 1] < arr[len - 2]) {
            return len - 1;
        }
        int i = 0;
        int j = len - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (arr[mid] > arr[mid - 1]) {
                j = mid - 1;
            } else if (arr[mid] < arr[mid + 1]) {
                i = mid + 1;
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
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void quickSortTest() {
        int[] arr = {4, 6, 5, 2, 3, 1};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
        validResult(10, 100);
    }

    public void quickSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;
        while (left < right) {
            while (left < right && arr[right] > arr[left]) {
                right--;
            }
            if (left < right)
                swap(arr, left, right);
            while (left < right && arr[left] < arr[right]) {
                left++;
            }
            if (left < right)
                swap(arr, left, right);
        }
        quickSort(arr, i, left - 1);
        quickSort(arr, left + 1, j);
    }

    @Test
    public void mergeSortTest() {
        int[] arr = {4, 6, 5, 2, 3, 1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < 100; i++) {
            validResult(10, 100);
        }
    }

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        mergePart(arr, 0, arr.length - 1);
    }

    private void mergePart(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergePart(arr, left, mid);
        mergePart(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        // 重新赋值
        for (int k = left; k <= right; k++) {
            arr[k] = temp[k - left];
        }
    }

    @Test
    public void mergeSortNoRecursionTest() {
        int[] arr = {-55, 47, 44, -29, 41, 84, -17, -19, 23, 20};
        mergeSortNoRecursion(arr);
        for (int i = 0; i < 100; i++) {
            validResult(101, 100);
        }
    }

    public void mergeSortNoRecursion(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        int step = 1;
        int len = arr.length;
        while (step < len) {
            int L = 0;
            while (L < len) {
                int mid = L + step - 1;
                if (mid >= len) {
                    break;
                }
                int R = Math.min(mid + step, len - 1);
                merge(arr, L, mid, R);
                L = R + 1;
            }
            if (step > len / 2) {
                break;
            }
            step <<= 1;
        }
    }

    @Test
    public void getMinSumTest() {
        int[] arr = {6, 3, 2, 1, 6, 7};
        System.out.println(getMinSum(arr));
        validGetMinSum(10, 100);
    }

    public int getMinSum01(int[] arr) {
        // 暴力法
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }

    public int getMinSum(int[] arr) {
        // 借助归并排序思想，求小和
        if (arr == null || arr.length == 1) {
            return 0;
        }

        return getMinSum(arr, 0, arr.length - 1);
    }

    public void validGetMinSum(int len, int maxVal) {
        int[] arr1 = new int[len];
        int[] arr2 = new int[len];
        for (int i = 0; i < len; i++) {
            int num = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * maxVal);
            arr1[i] = num;
            arr2[i] = num;
        }
        int[] arr = new int[len];
        System.arraycopy(arr1, 0, arr, 0, len);
        System.out.println(getMinSum(arr1) == getMinSum01(arr2) ? "right" : "failed");
    }


    private int getMinSum(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        return getMinSum(arr, left, mid) +
                getMinSum(arr, mid + 1, right) +
                getMinSumPart(arr, left, mid, right);

    }

    private int getMinSumPart(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int index = 0;
        int sum = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                sum += (right - j + 1) * arr[i];
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        // 重新赋值
        for (int k = left; k <= right; k++) {
            arr[k] = temp[k - left];
        }
        return sum;
    }

    @Test
    public void getReversePairsTest() {
        int[] arr = {6, 3, 2, 1, 6, 7};
        System.out.println(getReversePairs01(arr));
        System.out.println(getReversePairs(arr));
        for (int i = 0; i < 100; i++) {
            validgetReversePairs(4, 100);
        }
    }

    public int getReversePairs(int[] arr) {
        if (arr == null || arr.length == 1) {
            return 0;
        }
        return getReversePairs(arr, 0, arr.length - 1);
    }

    public void validgetReversePairs(int len, int maxVal) {
        int[] arr1 = new int[len];
        int[] arr2 = new int[len];
        for (int i = 0; i < len; i++) {
            int num = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * maxVal);
            arr1[i] = num;
            arr2[i] = num;
        }
        int[] arr = new int[len];
        System.arraycopy(arr1, 0, arr, 0, len);
        int reversePairs01 = getReversePairs(arr1);
        int reversePairs02 = getReversePairs01(arr2);
        if (reversePairs02 == reversePairs01) {
            System.out.println("right");
        } else {
            System.out.println(Arrays.toString(arr) + " reversePairs01:  " + reversePairs01 + "  reversePairs02：  " + reversePairs02);
        }

    }

    public int getReversePairs01(int[] arr) {
        int pairs = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    pairs++;
                }
            }
        }
        return pairs;
    }

    private int getReversePairs(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        return getReversePairs(arr, left, mid) +
                getReversePairs(arr, mid + 1, right) +
                mergePairs(arr, left, mid, right);
    }

    private int mergePairs(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int index = 0;
        int pairs = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else {
                pairs += (mid - i + 1);
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        // 重新赋值
        for (int k = left; k <= right; k++) {
            arr[k] = temp[k - left];
        }
        return pairs;
    }

    @Test
    public void biggerThanRightTwiceTest() {
        int[] arr = {1, 4, 5, 2, 3, 1, 1};
        int[] arr01 = {1, 4, 5, 2, 3, 1, 1};
        for (int i = 0; i < 100; i++) {
            validbiggerThanRightTwice(100, 100);
        }
    }

    public void validbiggerThanRightTwice(int len, int maxVal) {
        int[] arr1 = new int[len];
        int[] arr2 = new int[len];
        for (int i = 0; i < len; i++) {
            int num = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * maxVal);
            arr1[i] = num;
            arr2[i] = num;
        }
        int[] arr = new int[len];
        System.arraycopy(arr1, 0, arr, 0, len);
        int sum01 = biggerThanRightTwice(arr1);
        int sum02 = biggerThanRightTwice01(arr2);
        System.out.println(sum01 == sum02 ? "right" : "false");
    }

    public int biggerThanRightTwice(int[] arr) {
        if (arr == null || arr.length == 1) {
            return 0;
        }
        return biggerThanRightTwice(arr, 0, arr.length - 1);
    }

    public int biggerThanRightTwice01(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j] * 2) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private int biggerThanRightTwice(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        return biggerThanRightTwice(arr, left, mid) + biggerThanRightTwice(arr, mid + 1, right) +
                mergeBiggerThanRightTwice(arr, left, mid, right);
    }

    private int mergeBiggerThanRightTwice(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int index = 0;
        int L = i;
        int sum = 0;
        for (int k = mid + 1; k <= right; k++) {
            while (L <= mid && arr[L] <= arr[k] * 2) {
                L++;
            }
            sum += mid - L + 1;
        }
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        // 重新赋值
        for (int k = left; k <= right; k++) {
            arr[k] = temp[k - left];
        }
        return sum;
    }

    @Test
    public void countRangeNumTest() {
        int[] arr = {0, -3, -3, 1, 1, 2};
        System.out.println(countRangeSum01(arr, 3, 5));
        System.out.println(countRangeSum(arr, 3, 5));
        /*for (int i = 0; i < 100; i++) {
            validRangeNum(6, 100, 3, 20);
        }*/
    }

    public void validRangeNum(int len, int maxVal, int lower, int upper) {
        int[] arr1 = new int[len];
        int[] arr2 = new int[len];
        for (int i = 0; i < len; i++) {
            int num = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * maxVal);
            arr1[i] = num;
            arr2[i] = num;
        }
        int[] arr = new int[len];
        System.arraycopy(arr1, 0, arr, 0, len);
        int sum01 = countRangeSum(arr1, lower, upper);
        int sum02 = countRangeSum01(arr2, lower, upper);
        if (sum01 == sum02) {
            System.out.println("right");
        } else {
            System.out.println(sum01 + " " + sum02 + "   " + Arrays.toString(arr1));
        }
//        System.out.println(sum01 == sum02 ? "right" : "false");
    }

    /**
     * 给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper]
     * （包含lower和upper）之内的 区间和的个数 。
     * <p>
     * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        // 问题可以转换为某个位置x的子数组的终点，以0-x的前缀和为s1, 求0-0,0-1,0-x-1之前的所有前缀和在(s1.upper, s1-lower)中的
        // 个数， 然后可以将所有的前缀和数组转换为归并排序的问题， 求左数组中，在右数组中i的位置中 左数组的值在之间
        long[] bak = new long[nums.length];
        bak[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            bak[i] = bak[i - 1] + nums[i];
        }
        System.out.println("Arrays.toString(bak) = " + Arrays.toString(bak));
        return count(bak, 0, bak.length - 1, lower, upper);
    }

    private int count(long[] bak, int left, int right, int lower, int upper) {
        if (left == right) {
            if (bak[left] >= lower && bak[right] <= upper) {
                return 1;
            }
            return 0;
        }
        int mid = left + (right - left) / 2;
        return count(bak, left, mid, lower, upper) +
                count(bak, mid + 1, right, lower, upper) +
                mergeCount(bak, left, mid, right, lower, upper);
    }

    private int mergeCount(long[] arr, int left, int mid, int right, int lower, int upper) {
        int sum = 0;
        int L = left;
        int R = left;
        for (int i = mid + 1; i <= right; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (R <= mid && arr[R] <= max) {
                R++;
            }
            while (L <= mid && arr[L] < min) {
                L++;
            }

            sum += R - L;
        }

        long[] temp = new long[right - left + 1];
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        // 重新赋值
        for (int k = left; k <= right; k++) {
            arr[k] = temp[k - left];
        }
        return sum;
    }


    public int countRangeSum01(int[] nums, int lower, int upper) {
        long[] bak = new long[nums.length];
        bak[0] = nums[0];
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            bak[i] = bak[i - 1] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                long sum = getSum(i, j, bak);
                if (sum >= lower && sum <= upper) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private long getSum(int i, int j, long[] bak) {
        return i == 0 ? bak[j] : bak[j] - bak[i - 1];
    }

    @Test
    public void partitionTest() {
        int[] arr = {4, 2, 6, 8, 1, 3, 1};
        int index = partition(arr, 1, 4, 2);
        System.out.println("index = " + index);
    }

    /**
     * 给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1）,时间复杂度 O(N）
     *
     * @param arr
     * @return
     */
    public int partition(int[] arr, int left, int right, int num) {
        int L = left;
        // 左边界
        int p = left - 1;
        // 如果arr[L] > num L++  若arr[L] <=num  则与左边界的下一个位置交换位置 L++ 边界位置++
        while (L <= right) {
            if (arr[L] > num) {
                L++;
            } else {
                swap(arr, L++, ++p);
            }
        }
        return p;
    }

    @Test
    public void partitionThirdTest() {
        int[] arr = {4, 2, 6, 8, 1, 2, 1};
        System.out.println(Arrays.toString(partitionThird(arr, 0, 4, 2)));
    }

    /**
     * 给定一个数组arr,和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     *
     * @param arr
     * @param num
     * @return
     */
    public int[] partitionThird(int[] arr, int left, int right, int num) {
        int L = left;
        int boundaryL = left - 1;
        int boundaryR = right + 1;
        int R = right;
        // 如果arr[L] > num 与arr[R]交换位置 R-- 右边界--
        // 如果arr[L] == num L++,
        // 如果arr[L] < num 则与左边界下一个位置交换 L++ , 左边界++
        while (L <= R) {
            if (arr[L] > num) {
                swap(arr, L, R--);
                boundaryR--;
            } else if (arr[L] == num) {
                L++;
            } else {
                swap(arr, L++, ++boundaryL);
            }
        }
        return new int[]{boundaryL, boundaryR};
    }

    @Test
    public void quickSort01Test() {
        int[] arr = {4, 1, 3, 5, 6, 7, 2, 4, 1};
        quickSort02(arr);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < 100; i++) {
            validResult(100, 100);
        }
    }

    public void quickSort01(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        quickSort01(arr, 0, arr.length - 1, arr.length - 1);
    }

    private void quickSort01(int[] arr, int left, int right, int idx) {
        if (left >= right) {
            return;
        }
        int index = partition(arr, left, right, arr[idx]);
        quickSort01(arr, left, index - 1, index - 1);
        quickSort01(arr, index + 1, right, right);
    }

    public void quickSort02(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        quickSort02(arr, 0, arr.length - 1, arr.length - 1);
    }

    private void quickSort02(int[] arr, int left, int right, int idx) {
        if (left >= right) {
            return;
        }
        int[] index = partitionThird(arr, left, right, arr[idx]);
        quickSort02(arr, left, index[0], index[0]);
        quickSort02(arr, index[1], right, right);
    }

    @Test
    public void binaryTreeNodePrintTest() {
        BinaryTreeNode head = NodeUtils.constructBinaryTreeNode(new int[]{-1, 1, 2, 3, 4, 5, -1, 7, -1, -1, 6, 7});
        inPrintNoRecursion(head);
        midPrintNoRecursion(head);
        midPrintNoRecursion1(head);
        orderPrintNoRecursion(head);
        orderPrintNoRecursion1(head);
    }

    public void inPrintNoRecursion(BinaryTreeNode<Integer> node) {
        // 二叉树先序遍历
        if (node == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(node);
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            BinaryTreeNode<Integer> cur = stack.pop();
            list.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println(list);
    }

    public void midPrintNoRecursion(BinaryTreeNode<Integer> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            head = stack.pop();
            list.add(head.val);
            if (head.right == null) {
                head = null;
            } else {
                head = head.right;
            }
        }
        System.out.println(list);
    }

    public void midPrintNoRecursion1(BinaryTreeNode<Integer> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            if (!stack.isEmpty()) {
                head = stack.pop();
                list.add(head.val);
                head = head.right;
            }
        }
        System.out.println(list);
    }


    public void orderPrintNoRecursion(BinaryTreeNode<Integer> node) {
        // 二叉树后序遍历
        if (node == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(node);
        Stack<BinaryTreeNode> ans = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            BinaryTreeNode<Integer> cur = stack.pop();
            ans.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!ans.isEmpty()) {
            BinaryTreeNode<Integer> pop = ans.pop();
            list.add(pop.val);
        }
        System.out.println(list);
    }

    public void orderPrintNoRecursion1(BinaryTreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        BinaryTreeNode<Integer> pre = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            if (node.right == null || node.right == pre) {
                list.add(node.val);
                stack.pop();
                pre = node;
                node = null;
            } else {
                node = node.right;
            }
        }
        System.out.println(list);
    }

    @Test
    public void preNodeSerializeTest() {
        BinaryTreeNode head = NodeUtils.constructBinaryTreeNode(new int[]{-1, 1, 2, 3, 4, 5, -1, 7, -1, -1, 6, 7});
        NodeUtils.preOrderNoRecursion(head);
        Queue queue = preNodeSerial(head);
        System.out.println(queue);
        BinaryTreeNode head2 = preDeSerialize(queue);
        NodeUtils.preOrderNoRecursion(head2);
        System.out.println("-----------------------------------");
        NodeUtils.postOrderBinaryTreeNodeByRecursion(head);
        Deque<String> postQueue = postNodeSerial(head);
        System.out.println(postQueue);
        BinaryTreeNode head3 = postDeSerialize(postQueue);
        NodeUtils.postOrderBinaryTreeNodeByRecursion(head3);
        System.out.println("============================");
        NodeUtils.levelPrint(head);
        Queue<String> levelNode = levelNodeSerial(head);
        System.out.println(levelNode);
        BinaryTreeNode head4 = levelDeSerialize(levelNode);
        NodeUtils.levelPrint(head4);
    }

    public Queue<String> preNodeSerial(BinaryTreeNode<T> head) {
        if (head == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        preNodeSerial(head, queue);
        return queue;
    }

    private void preNodeSerial(BinaryTreeNode<T> head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
            return;
        }
        queue.add(String.valueOf(head.val));
        preNodeSerial(head.left, queue);
        preNodeSerial(head.right, queue);
    }

    public BinaryTreeNode<Integer> preDeSerialize(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        String peek = queue.peek();
        if (null == peek) {
            queue.poll();
            return null;
        }
        queue.poll();
        BinaryTreeNode<Integer> head = new BinaryTreeNode<>(Integer.valueOf(peek));
        head.left = preDeSerialize(queue);
        head.right = preDeSerialize(queue);
        return head;
    }

    public Deque<String> postNodeSerial(BinaryTreeNode head) {
        if (head == null) {
            return null;
        }
        Deque<String> queue = new LinkedList<>();
        postNodeSerial(head, queue);
        return queue;
    }

    private void postNodeSerial(BinaryTreeNode head, Deque<String> queue) {
        if (head == null) {
            queue.add(null);
            return;
        }
        postNodeSerial(head.left, queue);
        postNodeSerial(head.right, queue);
        queue.add(String.valueOf(head.val));
    }

    public BinaryTreeNode postDeSerialize(Deque<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        String peek = queue.pollLast();
        ;
        if (null == peek) {
            return null;
        }
        BinaryTreeNode<Integer> head = new BinaryTreeNode(Integer.valueOf(peek));
        head.right = postDeSerialize(queue);
        head.left = postDeSerialize(queue);
        return head;
    }

    public Queue<String> levelNodeSerial(BinaryTreeNode head) {
        if (head == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        levelNodeSerial(head, queue);
        return queue;
    }

    private void levelNodeSerial(BinaryTreeNode head, Queue<String> queue) {
        Queue<BinaryTreeNode> tmp = new LinkedList<>();
        tmp.add(head);
        if (head != null) {
            queue.add(String.valueOf(head.val));
        }
        while (!tmp.isEmpty()) {
            BinaryTreeNode cur = tmp.poll();
            if (cur.left != null) {
                queue.add(String.valueOf(cur.left.val));
                tmp.add(cur.left);
            } else {
                queue.add(null);
            }
            if (cur.right != null) {
                queue.add(String.valueOf(cur.right.val));
                tmp.add(cur.right);
            } else {
                queue.add(null);
            }
        }
    }

    public BinaryTreeNode levelDeSerialize(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        String poll = queue.poll();
        BinaryTreeNode<Integer> head = new BinaryTreeNode(Integer.valueOf(poll));
        Queue<BinaryTreeNode> node = new LinkedList<>();
        node.add(head);
        BinaryTreeNode<Integer> cur = null;
        while (!queue.isEmpty()) {
            cur = node.poll();
            cur.left = generateNode(queue.poll());
            cur.right = generateNode(queue.poll());
            if (cur.left != null) {
                node.add(cur.left);
            }
            if (cur.right != null) {
                node.add(cur.right);
            }
        }
        return head;
    }

    private BinaryTreeNode generateNode(String poll) {
        if (poll == null) {
            return null;
        }
        return new BinaryTreeNode(Integer.valueOf(poll));
    }

    @Test
    public void maxTreeNodeWeightTest() {
        BinaryTreeNode head = NodeUtils.constructBinaryTreeNode(new int[]{-1, 1, 2, 3, 4, 5, 9, 7, -1, -1, 6, 7});
        System.out.println(maxTreeNodeWeight(head));
    }

    public int maxTreeNodeWeight(BinaryTreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        BinaryTreeNode curEnd = head;
        BinaryTreeNode nextEnd = null;
        int max = 0;
        int num = 0;
        queue.add(head);
        while (!queue.isEmpty()) {
            BinaryTreeNode cur = queue.poll();
            num++;
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            if (cur == curEnd) {
                max = Math.max(max, num);
                num = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public int maxTreeNodeWeight1(BinaryTreeNode head) {
        if (head == null) {
            return 0;
        }
        int ans = 0;
        int curLevel = 1;
        int curLevelNodes = 0;
        Map<BinaryTreeNode, Integer> map = new HashMap<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(head);
        map.put(head, 1);
        while (!queue.isEmpty()) {
            BinaryTreeNode cur = queue.poll();
            int curLevelNode = map.get(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                map.put(cur.left, map.get(cur) + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                map.put(cur.right, map.get(cur) + 1);
            }
            if (curLevel == curLevelNode) {
                curLevelNodes++;
            } else {
                ans = Math.max(ans, curLevelNodes);
                curLevel++;
                curLevelNodes = 0;
            }
        }
        return Math.max(ans, curLevelNodes);
    }

    static class Node {
        // 定义一棵有父节点的二叉树
        int val;
        Node left;
        Node right;
        Node parent;

        public Node(int val) {
            this.val = val;
        }
    }

    @Test
    public void getPostNodeTest() {
        Node head = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(4);
        Node n4 = new Node(5);
        Node n5 = new Node(6);
        Node n6 = new Node(7);
        Node n7 = new Node(8);
        head.left = n1;
        head.right = n2;
        n1.left = n3;
        n1.right = n4;
        n1.parent = head;
        n2.right = n7;
        n2.parent = head;
        n3.parent = n1;
        n4.parent = n1;
        n4.left = n5;
        n4.right = n6;
        n5.parent = n4;
        n6.parent = n4;
        n7.parent = n2;
        Node postNode1 = getPostNode1(head, n6);
        System.out.println(postNode1.val);
        Node postNode2 = getPostNode(head, n6);
        System.out.println(postNode2.val);
    }

    /**
     * 给定一棵二叉树和一个目标节点，求这个节点的后继节点
     *
     * @param head
     * @return
     */
    public Node getPostNode1(Node head, Node node) {
        Stack<Node> stack = new Stack<>();
        List<Node> list = new ArrayList<>();
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            if (!stack.isEmpty()) {
                head = stack.pop();
                list.add(head);
                head = head.right;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == node) {
                if (i + 1 == list.size()) {
                    return null;
                }
                return list.get(i + 1);
            }
        }
        return null;
    }

    public Node getPostNode(Node head, Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            Node cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        } else {
            Node parent = node.parent;
            Node cur = node;
            while (parent != null && parent.right == cur) {
                cur = parent;
                parent = cur.parent;
            }
            return parent;
        }
    }

    public void processFold(int n) {
        process(1, n, true);
    }

    /**
     * @param i    当前层数
     * @param n    总共有n层
     * @param down 是否是凹折痕
     */
    private void process(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        process(i + 1, n, true);
        System.out.print(down ? "凹 " : "凸 ");
        process(i + 1, n, false);
    }

    @Test
    public void processFoldTest() {
        processFold(3);
    }

    public boolean isCompleteTree(BinaryTreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(head);
        BinaryTreeNode left = null;
        BinaryTreeNode right = null;
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            BinaryTreeNode cur = queue.poll();
            left = cur.left;
            right = cur.right;
            if ((isLeaf && (left != null || right != null)) || (left == null && right != null)) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (left == null || right == null) {
                isLeaf = true;
            }
        }
        return true;
    }

    static class Info {
        boolean isBalanced;
        int height;

        public Info(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。  110
     *
     * @param head
     * @return boolean
     */
    public boolean isBalanced(BinaryTreeNode head) {
        return processBalance(head).isBalanced;
    }

    private Info processBalance(BinaryTreeNode head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info leftInfo = processBalance(head.left);
        Info rightInfo = processBalance(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced) {
            isBalanced = false;
        }
        if (!rightInfo.isBalanced) {
            isBalanced = false;
        }
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        return new Info(isBalanced, height);
    }

    public boolean isBalanced1(BinaryTreeNode head) {
        if (head == null) {
            return true;
        }
        return Math.abs(height(head.left) - height(head.right)) < 2 && isBalanced1(head.left) && isBalanced1(head.right);
    }

    public int height(BinaryTreeNode head) {
        if (head == null) {
            return 0;
        }
        return Math.max(height(head.left), height(head.right)) + 1;
    }

    public boolean isBalanced2(BinaryTreeNode head) {
        return balanced(head) != -1;
    }

    public int balanced(BinaryTreeNode head) {
        if (head == null) {
            return 0;
        }
        int leftHeight = balanced(head.left);
        int rightHeight = balanced(head.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    static class BsfInfo {
        boolean isBsf;
        int max;
        int min;

        public BsfInfo(boolean i, int ma, int mi) {
            isBsf = i;
            max = ma;
            min = mi;
        }
    }

    public boolean isBSF(BinaryTreeNode head) {
        if (head == null) {
            return true;
        }
        return isBSFProcess(head).isBsf;
    }

    public BsfInfo isBSFProcess(BinaryTreeNode<Integer> head) {
        if (head == null) {
            return null;
        }
        BsfInfo leftInfo = isBSFProcess(head.left);
        BsfInfo rightInfo = isBSFProcess(head.right);
        boolean isBSF = true;
        int max = head.val;
        int min = head.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        if (leftInfo != null && !leftInfo.isBsf) {
            isBSF = false;
        }
        if (rightInfo != null && !rightInfo.isBsf) {
            isBSF = false;
        }
        if (leftInfo != null && head.val <= leftInfo.max) {
            isBSF = false;
        }
        if (rightInfo != null && head.val >= rightInfo.min) {
            isBSF = false;
        }
        return new BsfInfo(isBSF, max, min);
    }

    @Test
    public void isBsfNode1Test() {
        BinaryTreeNode<Integer> head = NodeUtils.constructBinaryTreeNode(new int[]{-1, 2, 1, 3});
        System.out.println(isBsfNode3(head));
    }

    public boolean isBsfNode1(BinaryTreeNode<Integer> head) {
        return isBsfNode1(head, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isBsfNode1(BinaryTreeNode<Integer> head, long lower, long upper) {
        if (head == null) {
            return true;
        }
        long val = head.val.longValue();
        if (val <= lower || val >= upper) {
            return false;
        }
        return isBsfNode1(head.left, lower, val) && isBsfNode1(head.right, val, upper);
    }

    long pre = Long.MIN_VALUE;

    public boolean isBsfNode2(BinaryTreeNode<Integer> head) {
        if (head == null) {
            return true;
        }
        // 使用中序遍历 ，二叉搜索树为递增序列
        Deque<BinaryTreeNode> queue = new LinkedList<>();
        Long pre1 = Long.MIN_VALUE;
        while (head != null || !queue.isEmpty()) {
            while (head != null) {
                queue.addLast(head);
                head = head.left;
            }
            if (!queue.isEmpty()) {
                BinaryTreeNode<Integer> cur = queue.removeLast();
                long val = cur.val.longValue();
                // 若比上一个节点小，则不是搜索树
                if (val <= pre1) {
                    return false;
                }
                pre1 = val;
                head = cur.right;
            }
        }
        return true;
    }

    public boolean isBsfNode3(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        if (!isBsfNode3(root.left)) {
            return false;
        }
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        return isBsfNode3(root.right);
    }


    static class DistanceInfo {
        // maxDis代表当前节点下左树或者右树的最大距离
        int maxDis;
        // maxDeep代表当前节点下左树或者右树的最大深度
        int maxDeep;

        public DistanceInfo(int i, int j) {
            maxDis = i;
            maxDeep = j;
        }
    }

    /**
     * 给定一颗二叉树，任何两个节点之间都存在距离，返回整颗树的最大距离
     * @param head
     * @return
     */
    public int getMaxDistance(BinaryTreeNode head) {
        return getMaxDisProcess(head).maxDis;
    }

    public DistanceInfo getMaxDisProcess(BinaryTreeNode head) {
        if (head == null) {
            return new DistanceInfo(0, 0);
        }
        DistanceInfo leftInfo = getMaxDisProcess(head.left);
        DistanceInfo rightInfo = getMaxDisProcess(head.right);
        // 当前节点的最大深度为 max(left, right) + 1;
        int maxDeep = Math.max(leftInfo.maxDeep, rightInfo.maxDeep) + 1;
        // 当前节点的最大距离为 max(max(left, right), maxDeep)
        int p1 = leftInfo.maxDis;
        int p2 = rightInfo.maxDis;
        int p3 = leftInfo.maxDeep + rightInfo.maxDeep + 1;
        int maxDis = Math.max(Math.max(p1, p2), p3);
        return new DistanceInfo(maxDis, maxDeep);
    }

    @Test
    public void binaryTreePathTest() {
        BinaryTreeNode<Integer> head = NodeUtils.constructBinaryTreeNode(new int[]{-1, 2, 1, 3, 4, 6, 7, 8});
        System.out.println(binaryTreePaths2(head));
    }

    /**
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。 257
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        binaryTreePaths(root, "", list);
        return list;
    }

    private void binaryTreePaths(BinaryTreeNode root, String s, List<String> list) {
        if (root == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(s);
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            list.add(sb.toString());
        } else {
            sb.append("->");
            binaryTreePaths(root.left, sb.toString(), list);
            binaryTreePaths(root.right, sb.toString(), list);
        }
    }

    public List<String> binaryTreePaths2(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        // 使用先序遍历深度搜索，打印所有路径
        List<String> res = new ArrayList<>();
        Stack stack = new Stack();
        stack.add(root);
        // 保存节点的同时，保存对应的路径子串
        stack.add(root.val + "");
        while (!stack.isEmpty()) {
            // 先取路径
            String str = (String) stack.pop();
            // 再取节点
            BinaryTreeNode cur = (BinaryTreeNode) stack.pop();
            // 当前节点为叶子节点时，添加到集合中
            if (cur.left == null && cur.right == null) {
                res.add(str);
            }
            if (cur.right != null) {
                stack.add(cur.right);
                stack.add(str + "->" + cur.right.val);
            }
            if (cur.left != null) {
                stack.add(cur.left);
                stack.add(str + "->" + cur.left.val);
            }
        }
        return res;
    }

    static class SubInfo {
        /**
         * 1.子树为二叉搜索树时头节点为不在x时, 最大节点数为 max(leftMax, rightMax)
         * 2. 子树为二叉搜索树时头节点为在x时， 需要判断，子树是否是二叉搜索树， 且子树的最大节点数
         * ans = max(max(leftMax,rightMax), max(leftNodes,rightNodes)+1))
         */
        int maxSubTreeSize;
        int allSize;
        long max;
        long min;

        public SubInfo(int isBSF, int nodes, long max, long min) {
            this.maxSubTreeSize = isBSF;
            this.allSize = nodes;
            this.max = max;
            this.min = min;
        }
    }

    /**
     * 求二叉树中最大树为搜索二叉树的节点数目
     */
    @Test
    public void getSubBsfMaxNodesTest() {
        BinaryTreeNode head = NodeUtils.constructBinaryTreeNode(new int[]{-1, 8, 6, 11, 4, 5, -1, 12});
        System.out.println(getSubBSFMaxNodes(head));
    }

    public int getSubBSFMaxNodes(BinaryTreeNode head) {
        return getSubBSFMaxNodesProcess(head).maxSubTreeSize;
    }

    public SubInfo getSubBSFMaxNodesProcess(BinaryTreeNode<Integer> head) {
        if (head == null) {
            return new SubInfo(0, 0, Long.MIN_VALUE, Long.MAX_VALUE);
        }
        SubInfo leftInfo = getSubBSFMaxNodesProcess(head.left);
        SubInfo rightInfo = getSubBSFMaxNodesProcess(head.right);
        long val = head.val.longValue();
        long max = Math.max(val, Math.max(leftInfo.max, rightInfo.max));
        long min = Math.min(val, Math.min(leftInfo.min, rightInfo.min));
        int allSize = leftInfo.allSize + rightInfo.allSize + 1;
        int p1 = leftInfo.maxSubTreeSize;
        int p2 = rightInfo.maxSubTreeSize;
        int p3 = -1;
        if (leftInfo.maxSubTreeSize == leftInfo.allSize && rightInfo.maxSubTreeSize == rightInfo.allSize &&
                leftInfo.max < val && rightInfo.min > val) {
            p3 = leftInfo.allSize + rightInfo.allSize + 1;
        }
        int maxSubTreeSize = Math.max(p1, Math.max(p2, p3));
        return new SubInfo(maxSubTreeSize, allSize, max, min);
    }

    static class CompleteInfo {
        // 当前树的高度
        int height;
        // 当前树的节点数
        int nodes;

        public CompleteInfo(int i, int j) {
            height = i;
            nodes = j;
        }
    }

    /**
     * 是否是完全二叉树，
     * @param head
     * @return
     */
    public boolean isCompleteNode(BinaryTreeNode head) {
        CompleteInfo info = isCompleteNodeProcess(head);
        return (1 << info.height) - 1 == info.nodes;
    }

    private CompleteInfo isCompleteNodeProcess(BinaryTreeNode head) {
        if (head == null) {
            return new CompleteInfo(0, 0);
        }
        CompleteInfo leftInfo = isCompleteNodeProcess(head.left);
        CompleteInfo rightInfo = isCompleteNodeProcess(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new CompleteInfo(height, nodes);
    }

    @Test
    public void isCompleteNodeTest() {
        BinaryTreeNode head = NodeUtils.constructBinaryTreeNode(new int[]{-1, 5, 3, 7, 2, 4, 6, 8, 5, 6, 1, 6, 2, 4, 7, 8});
        NodeUtils.levelPrint(head);
        System.out.println(isCompleteNode(head));
    }

    static class FullInfo {
        boolean isFull;
        boolean isCsf;
        int height;

        public FullInfo(boolean f, boolean c, int h) {
            isFull = f;
            isCsf = c;
            height = h;
        }
    }

    public boolean isFullTreeNode(BinaryTreeNode root) {
        return isFullProcess(root).isCsf;
    }

    public FullInfo isFullProcess(BinaryTreeNode root) {
        if (root == null) {
            return new FullInfo(true, true, 0);
        }
        FullInfo leftInfo = isFullProcess(root.left);
        FullInfo rightInfo = isFullProcess(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCsf = false;
        if (leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height || leftInfo.height == rightInfo.height + 1)) {
            isCsf = true;
        } else if (leftInfo.isFull && rightInfo.isCsf && leftInfo.height == rightInfo.height) {
            isCsf = true;
        } else if (leftInfo.isCsf && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCsf = true;
        }
        return new FullInfo(isFull, isCsf, height);
    }


    static class ManyNodeInfo {
        int cMax;
        int nMax;

        public ManyNodeInfo(int c, int n) {
            cMax = c;
            nMax = n;
        }
    }

    static class TreeNodes {
        int val;
        List<TreeNodes> list;

        public TreeNodes(int val) {
            this.val = val;
        }

        public TreeNodes(int val, List<TreeNodes> list) {
            this.val = val;
            this.list = list;
        }
    }

    /**
     * 给定一颗多叉树， 保证每个子节点都只有一个父节点，
     * 条件1： 每一节点都有对应的happy值  对应节点的val
     * 条件2： 若选中了一个节点，则与其相关的父节点，也子节点不能被选中
     * 求： 在这颗多叉树上选择节点的happy值和的最大值
     *
     * @param root
     * @return
     */
    public int treeNodesMax(TreeNodes root) {
        ManyNodeInfo manyNodeInfo = treeNodesProcess(root);
        return Math.max(manyNodeInfo.cMax, manyNodeInfo.nMax);
    }

    public ManyNodeInfo treeNodesProcess(TreeNodes root) {
        if (root == null) {
            return new ManyNodeInfo(0, 0);
        }
        List<TreeNodes> nodes = root.list;
        int cMax = root.val;
        int nMax = 0;
        for (TreeNodes node : nodes) {
            ManyNodeInfo info = treeNodesProcess(node);
            cMax += info.nMax;
            nMax += Math.max(info.cMax, info.nMax);
        }
        return new ManyNodeInfo(cMax, nMax);
    }

    static class CommonInfo {
        boolean findA;
        boolean findB;
        BinaryTreeNode ans;

        public CommonInfo(boolean a, boolean b, BinaryTreeNode ans) {
            findA = a;
            findB = b;
            this.ans = ans;
        }
    }

    public BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        return commonAncestorProcess(root, p, q).ans;
    }

    public CommonInfo commonAncestorProcess(BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        if (root == null) {
            return new CommonInfo(false, false, null);
        }
        CommonInfo leftInfo = commonAncestorProcess(root.left, p, q);
        CommonInfo rightInfo = commonAncestorProcess(root.right, p, q);
        boolean findA = (root == p || leftInfo.findA || rightInfo.findA);
        boolean findB = (root == q || leftInfo.findB || rightInfo.findB);
        BinaryTreeNode ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findA && findB) {
                ans = root;
            }
        }
        return new CommonInfo(findA, findB, ans);
    }

    /**
     * 给定一个二叉树的头节点，返回这颗二叉树中最大搜索二叉树的头节点
     * @param root
     * @return
     */
    public BinaryTreeNode getMaxBsfHeadNode(BinaryTreeNode root) {
        return null;
    }
}
