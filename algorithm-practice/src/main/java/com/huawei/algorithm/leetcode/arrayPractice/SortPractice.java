package com.huawei.algorithm.leetcode.arrayPractice;

import com.huawei.algorithm.leetcode.linkedPractice.ListNode;
import com.huawei.algorithm.leetcode.linkedPractice.NodeUtils;
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
        int p = left-1;
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
        System.out.println(Arrays.toString(partitionThird(arr, 0, 4,2)));
    }

    /**
     * 给定一个数组arr,和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     * @param arr
     * @param num
     * @return
     */
    public int[] partitionThird(int[] arr, int left, int right, int num) {
        int L = left;
        int boundaryL = left-1;
        int boundaryR = right+1;
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



}
