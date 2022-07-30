package com.huawei.algorithm.sort;

import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.huawei.algorithm.Node;
import org.junit.Test;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * @author king
 * @date 2022/6/22-19:38
 * @Desc
 */
public class SortAlgorithm {

    @Test
    public void bufferSort() {
        int len = 10;
        int[] arr = constructorSort(len);
        System.out.println(Arrays.toString(arr));
        bufferSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private int[] constructorSort(int len) {
        Random rd = new Random();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = rd.nextInt(20);
        }
        return arr;
    }

    public void bufferSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    @Test
    public void chooseSort() {
        int len = 10;
        int[] arr = constructorSort(len);
        System.out.println(Arrays.toString(arr));
        chooseSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void chooseSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            // 一次循环找到最小值所在的索引
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            // 最小值与i 交换位置
            swap(arr, i, min);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void quickSort() {
        int len = 5;
//        int[] arr = constructorSort(len);
        int[] arr = {8, 8, 15, 17, 6};
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public void quickSort(int[] arr) {
//        quickSortItem(arr, 0, arr.length - 1);
        quickSort2(arr, 0, arr.length - 1);
    }

    private void quickSort2(int[] arr, int i, int j) {
        if (i >= j) return;
        int pivot = partitionRev(arr, i, j);
        quickSort2(arr, i, pivot - 1);
        quickSort2(arr, pivot + 1, j);
    }

    private int partition(int[] arr, int i, int j) {
        int base = arr[i];
        int pivot = j;
        int index = i;
        for (; i < j; j--) {
            if (arr[j] > base) {
                swap(arr, pivot, j);
                pivot--;
            }
        }
        // 找到基准数位置, 交换
        swap(arr, index, pivot);
        return pivot;
    }


    private void quickSortItem(int[] arr, int i, int j) {
        // 递归终止条件  i>j时 结束递归
        if (i >= j) {
            return;
        }
        int left = i;
        int right = j;
        int base = arr[i];
        // 一次递归 找到基准数的本身位置
        while (i < j) {
            while (arr[j] >= base && i < j) {
                j--;
            }
            if (i < j) arr[i] = arr[j];
            while (arr[i] <= base && i < j) {
                i++;
            }
            if (i < j) arr[j] = arr[i];
        }
        arr[i] = base;
        quickSortItem(arr, left, i - 1);
        quickSortItem(arr, i + 1, right);
    }

    @Test
    public void insertSort() {
        int len = 10;
        int[] arr = constructorSort(len);
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void insertSort(int[] arr) {
        // 插入排序 将无序数列插入到有序数列中
        int cur = 1;
        int pre = 0;
        while (cur < arr.length) {
            insertSortItem(arr, pre);
            cur++;
            pre++;
        }
    }

    private void insertSortItem(int[] arr, int pre) {
        int value = arr[pre + 1];
        while (pre >= 0) {
            if (value < arr[pre]) {
                arr[pre + 1] = arr[pre];
                pre--;
            } else {
                break;
            }
        }
        arr[pre + 1] = value;
    }

    public int binarySearch(int[] arr, int dest) {
        int left = 0;
        int right = arr.length;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > dest) {
                left = mid + 1;
            } else if (arr[mid] < dest) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    public void bufferSortNode(Node head) {

    }

    @Test
    public void getLen() {
        int[] arr = constructorSort(20);
        Arrays.sort(arr);// 5
        System.out.println(Arrays.toString(arr));
        int len = deleteItem(arr);
        System.out.println(len);
        for (int i = 0; i <= len; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void mergeSort() {
        int len = 10;
        int[] arr = constructorSort(len);
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序
     */
    public void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSortDemo(arr, 0, arr.length - 1, tmp);
    }

    public void mergeSortDemo(int[] arr, int i, int j, int[] tmp) {
        if (i == j) return;
        int mid = (i + j) / 2;
        mergeSortDemo(arr, i, mid, tmp);
        mergeSortDemo(arr, mid + 1, j, tmp);
        // 合并有序数组
        // 标识结束位置
        merge2(arr, i, mid, j, tmp);
    }

    private void merge2(int[] arr, int i, int mid, int j, int[] tmp) {
        int index = 0;
        int m = i, n = mid + 1, r = j;
        while (m <= mid && n <= j) {
            if (arr[m] < arr[n]) {
                tmp[index++] = arr[m++];
            } else {
                tmp[index++] = arr[n++];
            }
        }

        // 补充未比较的数据
        int start = m, end = mid;
        if (start > end) {
            start = n;
            end = r;
        }
        while (start <= end) {
            tmp[index++] = arr[start++];
        }

        // 转移数据
        for (int k = 0; k < index; k++) {
            arr[i + k] = tmp[k];
        }
    }

    private void merge(int[] arr, int i, int mid, int j, int[] tmp) {
        // 合并 i-mid  与 (mid+1)-j两个数组
        int i_start = i;
        int i_end = mid;
        int j_start = mid + 1;
        int j_end = j;
        int index = 0;
        while (i_start <= i_end && j_start <= j_end) {
            if (arr[i_start] < arr[j_start]) {
                tmp[index++] = arr[i_start++];
            } else {
                tmp[index++] = arr[j_start++];
            }
        }
        // 判断左右两个数组是否都比较完成
        while (j_start <= j_end) {
            tmp[index++] = arr[j_start++];
        }
        while (i_start <= i_end) {
            tmp[index++] = arr[i_start++];
        }

        for (int k = 0; k < index; k++) {
            arr[k + i] = tmp[k];
        }
    }

    public int deleteItem(int[] arr) {
        int i = 0;
        int j = i + 1;
        while (j < arr.length) {
            if (arr[j] != arr[i]) {
                arr[i + 1] = arr[j];
                i++;
            }
            j++;
        }
        return i;
    }

    @Test
    public void testAdd() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] tmp = new int[arr.length * 2];
        int i = 0;
        int j = arr.length;
        while (i < arr.length) {
            tmp[j++] = arr[i++];
        }
        System.out.println(Arrays.toString(tmp));
    }

    @Test
    public void splitStr() {
        String str = "aabavavavavava";
        String substring = str.substring(0, 8);
        System.out.println("substring = " + substring);
        String[] split = str.split("\\w[8]");
        System.out.println(Arrays.toString(split));
    }


    @Test
    public void findKthLargest() {
        int[] arr = {23, 12, 43, 213, 2342, 12, 32};
        System.out.println(Arrays.toString(arr));
        System.out.println(findKthLargestByHeap(arr, 8));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 找出数组中第k大的元素 根据快排  分区 分治的过程
     *
     * @param arr
     * @return
     */
    public int findKthLargest(int[] arr, int k) {
        return partitionFind(arr, 0, arr.length - 1, k);
    }

    public int findKthLargestByHeap(int[] arr, int k) {
        // 小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
            // 如果堆中的元素超过了k个,则剔除
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }


    private int partitionFind(int[] arr, int i, int j, int k) {
        if (i >= j) return arr[i];
        int pivot = partitionRev(arr, i, j);
        if (pivot + 1 == k) {
            // 找到该数
            return arr[pivot];
        } else if (pivot + 1 > k) {
            // 目标数在左边
            return partitionFind(arr, i, pivot - 1, k);
        } else {
            return partitionFind(arr, pivot + 1, j, k);
        }
    }

    private int partitionRev(int[] arr, int i, int j) {
        int base = arr[j];
        int pivot = i;
        for (; i < j; i++) {
            if (arr[i] > base) {
                swap(arr, pivot, i);
                pivot++;
            }
        }
        swap(arr, pivot, j);
        return pivot;
    }

    @Test
    public void removeKdigits() {
        String num = "1432219";
        String str = removeKdigits(num, 3);
        System.out.println("str = " + str);
    }

    /**
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        // 使用栈
        if (k >= num.length()) return "0";
        Stack<Character> stack = new Stack<>();
        int base = 0;
        stack.add(num.charAt(base));
        int low = base + 1;
        // 将入栈元素与栈顶元素比较，大于等于栈顶元素，则入栈
        // 如果小于顶栈元素，弹出栈顶元素，直到入栈元素小于栈顶元素，k--,若k不为0，重复上述操作
        // 若k为0,还有元素，则将元素入栈， 栈中的元素即为结果
        // 若元素都添加进去后，k > 0 ，则一直弹出栈顶元素 k--  直到k为0
        Deque<Character> deque = new LinkedList<>();
        if (deque.peekLast() > 'c') {

        }
        num:
        while (low < num.length()) {
            if (stack.peek() <= num.charAt(low)) {
                stack.push(num.charAt(low));
            } else {
                while (stack.size() > 0) {
                    if (stack.peek() > num.charAt(low)) {
                        stack.pop();
                        k--;
                    } else {
                        stack.push(num.charAt(low));
                        break;
                    }
                    if (k == 0) {
                        // 当k为0 时，还有元素，则将剩余元素入栈
                        while (low < num.length()) {
                            stack.push(num.charAt(low));
                            low++;
                        }
                        break num;
                    }
                }
                if (stack.size() == 0)
                    stack.push(num.charAt(low));
            }
            low++;
        }
        // k!= 0时,说明元素都入栈了,则需要移除栈顶元素
        while (k > 0) {
            stack.pop();
            k--;
        }
        // 栈中的元素即为结果
        StringBuilder sb = new StringBuilder();
        while (stack.size() > 0) {
            sb.append(stack.pop());
        }
        sb = sb.reverse();
        int i = 0;
        while (i < sb.length() && sb.charAt(i) == '0') {
            i++;
        }
        LinkedList<String> strings = new LinkedList<>();
        return i >= sb.length() ? "0" : sb.substring(i);
    }


    /**
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
     * 请你以字符串形式返回这个最小的数字。
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits1(String num, int k) {
        // 暴力解法
        if (k >= num.length()) return "0";
        for (; k > 0; k--) {
            int base = 0;
            int low = base + 1;
            boolean flag = true;
            while (low < num.length()) {
                if (num.charAt(base) > num.charAt(low)) {
                    num = removeIndexChar(num, base);
                    flag = false;
                    break;
                }
                base++;
                low++;
            }
            if (flag) {
                num = removeIndexChar(num, low);
            }
            System.out.println("num = " + num);
        }
        int i = 0;
        while (i < num.length() && '0' == num.charAt(i)) {
            i++;
        }
        return i >= num.length() ? "0" : num.substring(i);
    }

    public String removeIndexChar(String num, int index) {
        if (index == num.length()) {
            return num.substring(0, index - 1);
        }
        return num.substring(0, index) + num.substring(index + 1);
    }


    // 统计逆序对个数
    int count = 0;

    @Test
    public void reversePairs() {
        int[] nums = {1, 3, 2, 3, 1};
        count = reversePairs(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(count);
    }

    /**
     * 数组中的逆序对\
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        mergeSortPairs(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSortPairs(int[] nums, int i, int j) {
        if (i >= j) {
            return;
        }
        int mid = (i + j) / 2;
        mergeSortPairs(nums, i, mid);
        mergeSortPairs(nums, mid + 1, j);
        mergePairs(nums, i, mid, j);
    }

    private void mergePairs(int[] nums, int i, int mid, int j) {
        int l_start = i;
        int base = i;
        int r_start = mid + 1;
        int index = 0;
        int[] tmp = new int[j - i + 1];
        while (l_start <= mid && r_start <= j) {
            if (nums[l_start] <= nums[r_start]) {
                tmp[index++] = nums[l_start++];
            } else {
                // 左边数大于右边  为逆序对
                count += (mid - l_start + 1);
                tmp[index++] = nums[r_start++];
            }
        }
        // 补充剩余元素
        while (l_start <= mid) {
            tmp[index++] = nums[l_start++];
        }
        while (r_start <= j) {
            tmp[index++] = nums[r_start++];
        }
        // 替换元素
        for (int k = 0; k < tmp.length; k++) {
            nums[base + k] = tmp[k];
        }
    }

    // 用来存放棋子的位置
    int[] result = new int[8];

    @Test
    public void testEightQueen() {
        eightQueen(0);
    }

    /**
     * 八皇后问题
     *
     * @param row 处理第row行的子问题
     */
    public void eightQueen(int row) {
        if (row == 8) {
            // 当棋子
            printQueen();
            return;
        }
        for (int column = 0; column < 8; column++) {
            if (isOk(row, column)) {
                result[row] = column;
                eightQueen(row + 1);
            }
        }
    }

    public boolean isOk(int row, int column) {
        int leftUp = column - 1, rightUp = column + 1;
        for (row -= 1; row >= 0; row--) {
            if (result[row] == column) return false;
            if (leftUp >= 0) {
                if (result[row] == leftUp) return false;
            }
            if (rightUp < 8) {
                if (result[row] == rightUp) return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    private void printQueen() {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (result[i] == j) {
                    System.out.print("Q");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    int maxWeight = Integer.MIN_VALUE;

    @Test
    public void zero_onePackage() {
//        int[] item = {5,2,4,7};
        int[] item = {7, 2, 4};
        f(0, 0, item, 6, 3);
        System.out.println(maxWeight);
    }

    /**
     * 0-1背包问题
     *
     * @param i
     * @param cw
     * @param item
     * @param W
     * @param n
     */
    public void f(int i, int cw, int[] item, int W, int n) {
        if (cw == W || i == n) {
            if (cw > maxWeight) maxWeight = cw;
            return;
        }
        f(i + 1, cw, item, W, n);
        if (cw + item[i] <= W) {
            f(i + 1, cw + item[i], item, W, n);
        }
    }

    @Test
    public void coinChange() {
        int[] coins = {2, 5, 7};
        int k = coinChange(coins, 27);
        System.out.println("k = " + k);
    }

    /**
     * coin[2,5,7] amount 27    最小硬币数 {}
     *
     * @param coins  每枚硬币的面值
     * @param amount 使用硬币拼奏出目标数
     * @return 组成amount的最小硬币数
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] res = new int[amount + 1];
        res[0] = 0;
        for (int i = 1; i <= amount; i++) {
            res[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && res[i - coins[j]] != Integer.MAX_VALUE) {
                    res[i] = Math.min(res[i], res[i - coins[j]] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(res));
        if (res[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return res[amount];
    }

    @Test
    public void knapsack() {
        int[] items = constructorSort(10);
        System.out.println(Arrays.toString(items));
        /*long start_time = System.currentTimeMillis();
        int knapsack = knapsack(items, 0);
        System.out.println("knapsack cost : " + (System.currentTimeMillis() - start_time));*/
        long start_time2 = System.currentTimeMillis();
        int knapsack2 = knapsack2(items, 9);
        System.out.println("knapsack2 cost : " + (System.currentTimeMillis() - start_time2));

//        System.out.println("knapsack = " + knapsack);
        System.out.println("knapsack2 = " + knapsack2);
    }

    /**
     * 使用动态规化求解0-1背包问题
     *
     * @param items 物品重量
     * @param w     背包承受的最大重量
     * @return 放入物品的最大重量
     */
    public int knapsack(int[] items, int w) {
        // 使用二维数组 i 表示放入的第i个物品  j 表示该物品是背包的物品总重量
        boolean[][] bools = new boolean[items.length][w + 1];
        bools[0][0] = true; // 第一行第一列需要特殊处理
        // 若第一个物品大于总承受重量，则不放
        if (items[0] <= w) {
            bools[0][items[0]] = true;
        }
        // 依次遍历物品
        for (int i = 1; i < items.length; i++) {
            for (int j = 0; j <= w; j++) {
                // 第i个物品不放入背包
                if (bools[i - 1][j]) bools[i][j] = true;
            }
            for (int j = 0; j <= w - items[i]; j++) {
                // 第i个物品放入背包
                if (bools[i - 1][j]) bools[i][j + items[i]] = true;
            }
        }
        System.out.println(Arrays.toString(bools[items.length - 1]));
        for (int i = w; i >= 0; i--) {
            if (bools[items.length - 1][i]) return i;
        }
        return 0;
    }

    public int knapsack2(int[] items, int w) {
        // 初始化数组
        boolean[] bools = new boolean[w + 1];
        //
        bools[0] = true;
        for (int i = 0; i < items.length; i++) {
            for (int j = w - items[i]; j >= 0; j--) { // 放入第i个物品
                if (bools[j]) bools[j + items[i]] = true;
            }
        }
        System.out.println(Arrays.toString(bools));
        for (int i = bools.length - 1; i >= 0; i--) {
            if (bools[i]) return i;
        }
        return 0;
    }


    public int knapsack3(int[] item, int w) {
        int res = 0;
        int i = 0, j = 0;
        for (; i < item.length; i++) {
            if (item[i] <= w) {
                res = Math.max(res, item[i]);
            } else {
                continue;
            }
            for (; j < item.length; j++) {
                if (i == j) {
                    continue;
                }
                if (res + item[j] <= w) {
                    res = Math.max(res, item[j] + res);
                }
            }
        }
        return res;
    }

    int[] items = constructorSort(10);
    int[] itemsValue = {4, 2, 5, 2, 1, 7, 4, 1, 3, 8};
    int w = 9;
    int max = 0;
    int coinValue = 0;

    @Test
    public void knapsackMaxValue() {
        System.out.println(Arrays.toString(items));
        System.out.println(Arrays.toString(itemsValue));
        knapsackMaxValue(0, 0, 0);
        System.out.println(max);
        System.out.println(coinValue);
    }

    public void knapsackMaxValue(int cw, int i, int value) {

        if (cw == w || i == items.length) {
            if (cw > max) {
                max = cw;
            }
            if (value > coinValue) {
                coinValue = value;
            }
            return;
        }
        knapsackMaxValue(cw, i + 1, value);
        if (cw + items[i] <= w) {
            knapsackMaxValue(cw + items[i], i + 1, itemsValue[i] + value);
        }
    }

    @Test
    public void knapsackMaxValue2() {
        int[] items = constructorSort(10);
        int[] values = constructorSort(10);
        System.out.println(Arrays.toString(items));
        System.out.println(Arrays.toString(values));
        int max = knapsackMaxValue2(12, items, values);
        System.out.println(max);
    }

    public int knapsackMaxValue2(int w, int[] items, int[] values) {
        int coinMaxValue = 0;
        boolean[] bools = new boolean[w + 1];
        bools[0] = true;
        int value = 0;
        if (items[0] <= w) {
            bools[items[0]] = true;
            value += values[0];
        }
        for (int i = 1; i < items.length; i++) {
            for (int j = w - items[i]; j >= 0; j--) {
                // 放第i个物品
                if (bools[j]) bools[items[i]] = true;
                value += values[i];
            }
            coinMaxValue = Math.max(value, coinMaxValue);
        }
        return coinMaxValue;
    }

    @Test
    public void knapsackeMaxValue() {
        int[] items = {0, 2, 3, 4, 5};
        int[] itemsValue = {0, 3, 4, 5, 8};
        int max = knapsackMaxValue3(8, items, itemsValue);
        System.out.println("max = " + max);
    }

    public int knapsackMaxValue3(int w, int[] items, int[] itemsValue) {
        // f(n,w) = f(n-1,w)  或  f(n,w) = max(f(n-1,w-items[n]),itemsValue[n])
        int[][] states = new int[items.length][w + 1];
        states[0][0] = 0;
        if (items[0] <= w) {
            states[0][items[0]] = itemsValue[0];
        }
        for (int i = 1; i < items.length; i++) {
            for (int j = 1; j <= w; j++) {
                // 承重大于w,不放入的情况
                if (items[i] > j) {
                    states[i][j] = states[i - 1][j];
                } else {
                    // 放入第i个元素  不放入第i个元素的价值与放入i个价值
                    // f(n,w) = max(f(n-1,w-items[n])+itemsValue[i],f(n-1,j))
                    states[i][j] = Math.max(states[i - 1][j], states[i - 1][j - items[i]] + itemsValue[i]);
                }
            }
        }
        for (int i = 0; i < items.length; i++) {
            System.out.println(Arrays.toString(states[i]));
        }
        return states[items.length - 1][w];
    }

    @Test
    public void knapsackMaxValue4() {
        int[] items = constructorSort(4);
        System.out.println(Arrays.toString(items));
        int[] itemsValue = constructorSort(4);
        System.out.println(Arrays.toString(itemsValue));
        int max = knapsackMaxValue(8, items, itemsValue);
        System.out.println("max = " + max);
    }

    public int knapsackMaxValue(int w, int[] items, int[] itemsValue) {
        int[][] states = new int[items.length][w + 1];
        states[0][0] = 0;
        if (items[0] <= w) {
            states[0][items[0]] = itemsValue[0];
        }
        for (int i = 1; i < items.length; i++) {
            for (int j = 0; j <= w; j++) {
                if (items[i] > j) {
                    states[i][j] = states[i - 1][j];
                } else {
                    states[i][j] = Math.max(states[i - 1][j], states[i - 1][j - items[i]] + itemsValue[i]);
                }
            }
        }
        for (int i = 0; i < items.length; i++) {
            System.out.println(Arrays.toString(states[i]));
        }
        return 1;
    }


    int minPath = Integer.MAX_VALUE;

    @Test
    public void minPath() {
        int[][] items = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(items.length);
        System.out.println(items[0].length);
        minPath(0, 0, items, 1);
        System.out.println(minPath);
    }

    /**
     * @param i     行
     * @param j     列
     * @param items
     * @param path
     */
    public void minPath(int i, int j, int[][] items, int path) {
        if (i == items.length - 1 && j == items[0].length - 1) {
            if (path < minPath) {
                minPath = path;
            }
            return;
        }
        if (i + 1 < items.length) {
            minPath(i + 1, j, items, path + items[i + 1][j]);
        }
        if (j + 1 < items[0].length) {
            minPath(i, j + 1, items, path + items[i][j + 1]);
        }
    }

    @Test
    public void uniquePaths() {
        int i = uniquePaths(3, 3);
        System.out.println(i);
    }

    /**
     * m*n的格子中，机器人从左上走到右下的路径数
     *
     * @param m column
     * @param n row
     * @return
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        int[][] path = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    path[i][j] = 1;
                    continue;
                }
                path[i][j] = path[i - 1][j] + path[i][j - 1];
            }
        }
        return path[n - 1][m - 1];
    }

    @Test
    public void minPath2() {
        int[][] items = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
        int res = minPath(items);
        System.out.println(res);
    }


    /**
     * 二维数组中从左上到右下最小路径问题
     *
     * @param grid
     * @return
     */
    public int minPath(int[][] grid) {
        // 转移方程  d[i][j] = Min(d[i-1][j],d[i][j-1]) + d[i][j]
        int r = grid.length; // 行
        int c = grid[0].length; // 列
        int[][] path = new int[r][c];
        // 初始化第1个路径
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0 && j == 0) {
                    path[i][j] = grid[i][j];
                    continue;
                }
                if (i == 0 && j > 0) {
                    path[i][j] = path[i][j - 1] + grid[i][j];
                    continue;
                }
                if (j == 0 && i > 0) {
                    path[i][j] = path[i - 1][j] + grid[i][j];
                    continue;
                }
                path[i][j] = Math.min(path[i - 1][j], path[i][j - 1]) + grid[i][j];
            }
        }
        return path[r - 1][c - 1];
    }

    @Test
    public void minPath3() {
//        int[][] grid = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        ;
        int[][] mem = new int[grid.length][grid[0].length];
        int minPath = minPath3(grid, grid.length - 1, grid[0].length - 1, mem);
        System.out.println("minPath = " + minPath);
    }

    /**
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public int minPath3(int[][] grid, int i, int j, int[][] mem) {
        // 递归终止条件
        if (i == 0 && j == 0) return grid[0][0];
        // 枝剪重复路径
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        // 左边的最小路径
        if (i - 1 >= 0) {
            minLeft = minPath3(grid, i - 1, j, mem);
        }
        int minUp = Integer.MAX_VALUE;
        // 上边的最小路径
        if (j - 1 >= 0) {
            minUp = minPath3(grid, i, j - 1, mem);
        }
        // 计算 左边与上边的最小路径，并求出当前的最小路径
        int cur = grid[i][j] + Math.min(minLeft, minUp);
        return cur;
    }


    @Test
    public void testList() {
        List<Integer> list1 = Lists.newArrayList(1);
        List<Integer> list2 = Lists.newArrayList(2, 3);
        List<Integer> list3 = Lists.newArrayList(4, 5, 6);
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        System.out.println(list);
    }

    @Test
    public void minPath5() {
        List<Integer> list1 = Lists.newArrayList(5);
        List<Integer> list2 = Lists.newArrayList(7, 8);
        List<Integer> list3 = Lists.newArrayList(2, 3, 4);
        List<Integer> list4 = Lists.newArrayList(4, 9, 6, 1);
        List<Integer> list5 = Lists.newArrayList(2, 7, 9, 4, 5);
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);
        list.add(list5);
    }

    @Test
    public void minPath4() {
        List<Integer> list1 = Lists.newArrayList(1);
        List<Integer> list2 = Lists.newArrayList(5, 3);
        List<Integer> list3 = Lists.newArrayList(4, 5, 6);
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        minPath4(list, 0, 0, 0, 2);
        System.out.println(minPath);
    }

    /**
     * 杨辉三角求最小路径
     *
     * @param list
     * @return
     */
    public void minPath4(List<List<Integer>> list, int i, int j, int path, int n) {
        // 保存到达子节点的最小路径
//        int[] res = new int[list.size()];
        if (i == list.size()) {
            if (path < minPath) {
                minPath = path;
            }
            n--;
            return;
        }
        if (i < list.size()) {
            minPath4(list, i + 1, j, path + list.get(i).get(j), n);
        }
        if (n > 0 && j + 1 <= i) {
            minPath4(list, i + 1, j + 1, path + list.get(i).get(j + 1), n);
        }
        //        return 0
    }

    @Test
    public void minPath6() {
        List<Integer> list1 = Lists.newArrayList(1);
        List<Integer> list2 = Lists.newArrayList(5, 3);
        List<Integer> list3 = Lists.newArrayList(4, 5, 6);
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        int min = minPath6(list);
        System.out.println("min = " + min);
    }

    public int minPath6(List<List<Integer>> list) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        tmp.add(list.get(0).get(0));
        res.add(tmp);
        for (int i = 1; i < list.size(); i++) {
            tmp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    tmp.add(res.get(i - 1).get(j) + list.get(i).get(j));
                    continue;
                }
                if (j == i) {
                    tmp.add(res.get(i - 1).get(j - 1) + list.get(i).get(j));
                    continue;
                }
                int max = Math.max(res.get(i - 1).get(j - 1), res.get(i - 1).get(j)) + list.get(i).get(j);
                tmp.add(max);
            }
            res.add(tmp);
        }
        System.out.println(res.get(list.size() - 1));
        Integer minPath = res.get(list.size() - 1).stream().min(Comparator.comparing(Integer::intValue)).get();
        return minPath;
    }

    @Test
    public void testMax() {
        List<Integer> list = Arrays.asList(23, 41, 32, 53, 13);
        Integer a = list.stream().max(Comparator.comparing(Integer::intValue)).get();
        Integer b = list.stream().max(Integer::compareTo).get();
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    @Test
    public void canJump() {
//        int[] arr = {2,3,1,1,4};
        int[] arr = {3, 2, 1, 0, 4};
        boolean res = canJump(arr);
        System.out.println(res);
    }

    /**
     * 跳跃游戏
     *
     * @param arr
     */
    public boolean canJump(int[] arr) {
        // f[j] = OR (0<=x<j ) [f[i] AND i+a(i) >= j]
        boolean[] res = new boolean[arr.length];
        res[0] = true;
        for (int i = 1; i < res.length; i++) {
            for (int j = 0; j < i; j++) {
                res[i] = res[j] && arr[j] + j >= i;
                if (res[i]) {
                    break;
                }
            }
        }
        return res[arr.length - 1];
    }

    @Test
    public void canJump2() {
//        int[] arr = {2, 3, 1, 1, 4};
        int[] arr = {3, 2, 1, 0, 4};
        boolean res = canJump4(arr);
        System.out.println(res);
    }


    /**
     * 贪心
     *
     * @param arr
     * @return
     */
    public boolean canJump2(int[] arr) {
        int maxStep = 0;
        for (int i = 0, len = arr.length - 1; i <= len; i++) {
            if (i <= maxStep) {
                maxStep = Math.max(maxStep, i + arr[i]);
                if (maxStep >= len) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从后向前判断当前元素是否可以到lastPoint
     *
     * @param arr
     * @return
     */
    public boolean canJump3(int[] arr) {
        int right = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i++) {
            if (i + arr[i] >= right) {
                right = i;
            }
        }
        return right == 0;
    }

    public boolean canJump4(int[] arr) {
        boolean[] res = new boolean[arr.length];
        res[0] = true;
        for (int i = 1; i < arr.length; i++) {
            res[i] = false;
            for (int j = 0; j < i; j++) {
                if (res[j] && j + arr[j] >= i) {
                    res[i] = true;
                    break;
                }
            }
        }
        return res[arr.length - 1];
    }

    @Test
    public void jump() {
//        int[] arr = {2, 3, 0, 1, 4};
        int[] arr = {1, 1, 1, 1, 4};
        int res = jump(arr);
        System.out.println(res);
    }

    public int jump(int[] arr) {
        int right = arr.length - 1;
        int count = 0;
        while (right > 0) {
            for (int i = 0; i < right; i++) {
                if (i + arr[i] >= right) {
                    right = i;
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    @Test
    public void jump2() {
//        int[] arr = {2, 3, 0, 1, 4};
//        int[] arr = {1, 1, 1, 1, 4};
//        int[] arr = {1,3,2};
        int[] arr = {1, 2, 4};
        int res = jump2(arr);
        System.out.println(res);
    }

    /**
     * 优化跳跃游戏
     *
     * @param arr
     * @return
     */
    public int jump2(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        }
        // 记录当前可跳跃的最远位置
        int right = arr[0];
        // 当前跳跃到最远位置的索引
        int index = 0;
        int count = 0;
        while (right < arr.length - 1) {
            int step = index + arr[index];
            for (int i = index + 1; i <= step && i < arr.length; i++) {
                if (i + arr[i] > right) {
                    right = i + arr[i];
                    index = i;
                }
            }
            count++;
        }
        return ++count;
    }

    @Test
    public void jump3() {
//        int[] arr = {2, 3, 0, 1, 4};
//        int[] arr = {1, 1, 1, 1, 4};
//        int[] arr = {1,3,2};
        int[] arr = {1, 2, 4};
        int res = jump2(arr);
        System.out.println(res);
    }

    public int jump3(int[] arr) {
        int len = arr.length;
        int maxPosition = 0; //
        int end = 0; // 当前元素可以跳跃到的最大距离
        int count = 0;
        for (int i = 0; i < len - 1; i++) {
            maxPosition = Math.max(maxPosition, i + arr[i]);
            if (i == end) {
                // 已经到达前面元素可以到达的最大值
                end = maxPosition;
                // 跳跃次数+1
                count++;
            }
        }
        return count;
    }

    char[] a = "mw".toCharArray();
    char[] b = "ymots".toCharArray();
    int m = a.length;
    int n = b.length;
    int initDis = Integer.MAX_VALUE;

    @Test
    public void lwstDis() {
        lwstDis(0, 0, 0);
        System.out.println("initDis = " + initDis);
    }

    /**
     * 莱文斯坦距离  最小
     *
     * @param i
     * @param j
     * @param minDis
     */
    public void lwstDis(int i, int j, int minDis) {
        // 递归终止条件
        if (i == m || j == n) {
            if (i < m) {
                minDis += (m - i);
            }
            if (j < n) {
                minDis += (n - j);
            }
            if (minDis < initDis) {
                initDis = minDis;
            }
            return;
        }
        if (a[i] == b[j]) {
            lwstDis(i + 1, j + 1, minDis);
        } else {
            lwstDis(i + 1, j, minDis + 1); // 删除a[i] 或 b[j]前添加一个字符
            lwstDis(i, j + 1, minDis + 1); // 删除b[j] 或 a[i]前添加一个字符
            lwstDis(i + 1, j + 1, minDis + 1); // 替换字符
        }
    }

    @Test
    public void lwstDis1() {
        String s1 = "mw";
        String s2 = "ymotsi";
        int minDis = lwstDis1(s1, s2);
        System.out.println(minDis);
    }

    public int lwstDis1(String s1, String s2) {
        //  若 arr[i] == arr[j]  d[i][j] = min(d[i-1][j],d[i-1][j-1],d[i][j-1])
        // 若 arr[i] == arr[j]   d[i][j] = min(d[i-1][j],d[i-1][j-1],d[i][j-1]) +1;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int m = c1.length;
        int n = c2.length;
        int[][] res = new int[n][m];
        if (c1[0] == c2[0]) {
            res[0][0] = 0;
        } else {
            res[0][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            if (c1[i] == c2[0]) {
                res[0][i] = res[0][i - 1];
            } else {
                res[0][i] = res[0][i - 1] + 1;
            }
        }
        for (int i = 1; i < n; i++) {
            if (c2[i] == c1[0]) {
                res[i][0] = res[i - 1][0];
            } else {
                res[i][0] = res[i - 1][0] + 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (c2[i] == c1[j]) {
                    res[i][j] = Math.min(Math.min(res[i - 1][j], res[i][j - 1]), res[i - 1][j - 1]);
                } else {
                    res[i][j] = Math.min(Math.min(res[i - 1][j], res[i][j - 1]), res[i - 1][j - 1]) + 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
        return res[n - 1][m - 1];
    }

    char[] c1 = "awfef".toCharArray();
    char[] c2 = "wfaf".toCharArray();
    int p = c1.length;
    int q = c2.length;
    int maxDis = 0;

    @Test
    public void lcs() {
        lcs(0, 0, 0);
        System.out.println("maxDis = " + maxDis);
    }

    /**
     * 最长公共子串长度   awfe   wfaf  >>  wf 2
     *
     * @param i
     * @param j
     * @param dest
     */
    public void lcs(int i, int j, int dest) {
        if (i == p || j == q) {
            if (dest > maxDis) {
                maxDis = dest;
            }
            return;
        }
        if (c1[i] == c2[j]) {
            lcs(i + 1, j + 1, dest + 1);
        } else {
            lcs(i + 1, j, dest);
            lcs(i, j + 1, dest);
        }
    }

    @Test
    public void lcs1() {
        String s1 = "awfea";
        String s2 = "wfa";
        int maxDis = lcs1(s1, s2);
        System.out.println(maxDis);
    }

    /**
     * 最长公共子串
     *
     * @param s1
     * @param s2
     * @return
     */
    public int lcs1(String s1, String s2) {
        // c1[i] == c2[j]  res[i][j] = max(res[i-1][j],res[i][j-1],res[i-1][j-1]) +1;
        // c1[i] != c2[j]  res[i][j] = max(res[i-1][j],res[i][j-1],res[i-1][j-1]);
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int m = c1.length;
        int n = c2.length;
        int[][] res = new int[m][n];
        res[0][0] = c1[0] == c2[0] ? 1 : 0;
        for (int i = 1; i < n; i++) {
            res[0][i] = c2[i] == c1[0] ? res[0][i - 1] + 1 : res[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            res[i][0] = c2[0] == c1[i] ? res[i - 1][0] + 1 : res[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int min = Math.max(Math.max(res[i - 1][j], res[i][j - 1]), res[i - 1][j - 1]);
                if (c1[i] == c2[j]) {
                    res[i][j] = min + 1;
                } else {
                    res[i][j] = min;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
        return res[m - 1][n - 1];
    }

    @Test
    public void lastStoneWeight() {
        int[] stones = {1, 4, 1, 8, 1};
        int lastStone = lastStoneWeightII(stones);
        System.out.println("lastStone = " + lastStone);
    }

    /**
     * 最后一块石头的重量
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        int len = stones.length;
        int[] res = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                res[j] = Math.max(res[j], res[j - stones[i]] + stones[i]);
            }
        }
        System.out.println(Arrays.toString(res));
        return sum - 2 * res[target];
    }

    int target = 3;
    int count1 = 0;

    @Test
    public void targetAdd() {
        int[] arr = {1, 1, 1, 1, 1};
        targetAdd(arr, 0, 0);
        System.out.println(count1);
    }

    public void targetAdd(int[] arr, int i, int res) {
        if (i == arr.length) {
            if (res == target) {
                count1++;
            }
            return;
        }
        // 当前数前为+号
        targetAdd(arr, i + 1, res + arr[i]);
        targetAdd(arr, i + 1, res - arr[i]);
    }

    @Test
    public void findTargetSubways() {
        int[] arr = {1, 3, 2, 4, 5};
        int target = 3;
        int way = targetAdd1(arr, target);
        System.out.println("way = " + way);
    }

    /**
     * 目标和
     *
     * @param arr
     * @param target
     * @return
     */
    public int targetAdd1(int[] arr, int target) {
        // 假设带-号的元素和为neg, 带+号的元素的和则为sum-neg  若目标为target ,则  target = (sum-neg) - neg;
        // 转换公式为neg = (sum-target)/2  又 sum-target >0 且，(sum-target)/2 可以整除
        int sum = Arrays.stream(arr).reduce(0, Integer::sum);
        int neg = (sum - target) / 2;
        if ((sum - target) < 0 || (sum - target) % 2 != 0) {
            return 0; // 不可能求出目标和
        }
        int len = arr.length;
        // d(i)(j)  i表示当前第i个元素 j表示背包的最大承重， 值为组合为target的总方式
        int[][] res = new int[len + 1][neg + 1];
        // 初始化数据  若i==0 , j==0 ,表示取第0个元素，重量为0的方式，即一个不取，方式为1
        // 若 j>=1 则值为0
        res[0][0] = 1;
        for (int i = 1; i <= neg; i++) {
            res[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= neg; j++) {
                if (arr[i - 1] > j) {
                    res[i][j] = res[i - 1][j];
                } else {
                    res[i][j] = res[i - 1][j] + res[i - 1][j - arr[i - 1]];
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
        return res[n][neg];
    }

    @Test
    public void findTargetSubways2() {
        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 1};
//        int[] arr = {1,1,1,1,1};
        int target = 1;
        int way = findTargetSubways(arr, target);
        System.out.println("way = " + way);
    }

    public int findTargetSubways(int[] arr, int target) {
        int len = arr.length;
        int sum = Arrays.stream(arr).sum();
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = (sum - target) / 2;
        int[] res = new int[neg + 1];
        res[0] = 1;

        for (int i = 0; i < len; i++) {
            for (int j = neg; j >= arr[i]; j--) {
                res[j] = res[j] + res[j - arr[i]];
            }
            System.out.println(Arrays.toString(res));
        }
        return res[neg];
    }

    int subway = 0;
    int zero_m = 5;
    int one_n = 3;

    @Test
    public void findMaxForm() {
        String[] str = {"10", "0001", "111001", "1", "0"};
//        String[] str = {"10", "0", "1"};
        findMaxForm(str, 0, 0, 0, 0);
        System.out.println(subway);
    }

    public void findMaxForm(String[] str, int i, int m, int n, int maxSubway) {
        if (i == str.length) {
            if (maxSubway > subway) {
                subway = maxSubway;
            }
            return;
        }
        int[] arr = funZeroandOne(str[i], m, n);
        findMaxForm(str, i + 1, m, n, maxSubway);
        if (arr[0] <= zero_m && arr[1] <= one_n) {
            findMaxForm(str, i + 1, arr[0], arr[1], maxSubway + 1);
        }
    }

    @Test
    public void findMaxForm1() {
        String[] str = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        int maxForm1 = findMaxForm1(str, m, n);
        System.out.println("maxForm1 = " + maxForm1);
    }


    public int findMaxForm1(String[] str, int m, int n) {
        // 可以转换为一个三维数组  01背包问题
        // d[i][j][k] i表示当前第i个字符串(物品) j 表示 可以存放最大为j个'0'字符 k 表示 可以存放最大为k个'1'字符
        /**
         *  初始化 由于不入任何元素时， j,k都为0，即d[0][0][0] = 0;
         *  zero_m : 当前元素的'0'的个数  one_n: 表示'1'的个数
         *  若 j < zero_m || k < one_n ,则不放入该元素 d[i][j][k] = d[i-1][j][k]
         *  j >= zero_m && k >= one_n  若则 d[i][j][k] = max(d[i-1][j][k],d[i][j-zero_m][k-one_n]+1)
         *  最后结果为 d[arr.length-1][j][m]
         */
        int[][][] dp = new int[str.length + 1][m + 1][n + 1];
        for (int i = 1; i <= str.length; i++) {
            int[] arr = findStr(str[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= arr[0] && k >= arr[1]) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - arr[0]][k - arr[1]] + 1);
                    }
                }
            }
        }
        for (int j = 0; j <= m; j++) {
            System.out.println(Arrays.toString(dp[str.length][j]));
        }
        return dp[str.length][m][n];
    }


    public int[] findStr(String str) {
        int[] arr = new int[2];
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i) - '0']++;
        }
        return arr;
    }


    @Test
    public void findMaxForm2() {
        String[] str = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        int maxForm1 = findMaxForm2(str, m, n);
        System.out.println("maxForm1 = " + maxForm1);
    }

    public int findMaxForm2(String[] str, int m, int n) {
        // 使用滚动数组实现 动态规化元素赋值
        // dp[i][j] i 表示 '0'的最大个数 j表示'1'的最大个数

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < str.length; i++) {
            int[] arr = findStr(str[i]);
            for (int j = m; j >= arr[0]; j--) {
                for (int k = n; k >= arr[1]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - arr[0]][k - arr[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }


    private int[] funZeroandOne(String s, int m, int n) {
        int[] arr = new int[2];
        int zero = 0;
        int one = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zero++;
            } else {
                one++;
            }
        }
        arr[0] = zero + m;
        arr[1] = one + n;
        return arr;
    }

    List<List<Integer>> res = new ArrayList<>();

    @Test
    public void subsets() {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }

    /**
     * 子集  找出该数据所有的子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> subs = new ArrayList<>();
//        findSubset(nums,0,subs);
        findSubsetNoExtraSpace(nums, 0, subs);
        res.addAll(new ArrayList());
        return res;
    }

    @Test
    public void subsets2() {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }

    public void findSubset(int[] nums, int i, List<Integer> subs) {
        if (i == nums.length) {
            res.add(subs);
            return;
        }
        List<Integer> list1 = new ArrayList<>();
        list1.addAll(subs);
        findSubset(nums, i + 1, list1);
        List<Integer> list2 = new ArrayList<>();
        list2.addAll(subs);
        list2.add(nums[i]);
        findSubset(nums, i + 1, list2);
    }

    public void findSubsetNoExtraSpace(int[] nums, int i, List<Integer> subs) {
        if (i == nums.length) {
            res.add(subs);
            return;
        }
        // 添加该数
        subs.add(nums[i]);
        findSubset(nums, i + 1, subs);
        // 不添加该数
        subs.remove(i);
        findSubset(nums, i + 1, subs);
    }

    @Test
    public void subset2() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = subsets2(nums);
        System.out.println(lists);
    }

    /**
     * 使用动态规化求子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        // 当前元素的子集为 上个元素的子集+ 当前元素是否加入元素的情况
        List<List<Integer>> subs = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        // 初始化空集合
        subs.add(list);
        for (int i = 0; i < nums.length; i++) {
            int times = subs.size();
            for (int j = 0; j < times; j++) {
                List<Integer> tmp = new ArrayList<>(subs.get(j));
                tmp.add(nums[i]);
                subs.add(tmp);
            }
        }
        return subs;
    }

    @Test
    public void lengthOfLIS() {
//        int[] nums = {10,9,2,5,3,7,101,18};
//        int[] nums = {2, 9, 3, 6, 5, 1, 7};
        int[] nums = {0, 1, 0, 3, 2, 3};
        int i = lengthOfLIS(nums);
        System.out.println("i = " + i);
    }

    /**
     * 最长递增子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        //d[i] 表示当前元素的最长递增子序列
        // d[i] = max(d[j])+1 且nums[i] > nums[j]时，
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = 1;
            for (int j = 0; j < i; j++) {
                // 依次遍历之前每个元素的最长递增子序列 ，若nums[i] > nums[j] 则d[j]+1
                if (nums[i] > nums[j]) {
                    res[i] = Math.max(res[i], res[j] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(res));
        return Arrays.stream(res).max().getAsInt();
    }

    @Test
    public void lengthOfLIS1() {
//        int[] nums = {10,9,2,5,3,7,101,18};
//        int[] nums = {2, 9, 3, 6, 5, 1, 7};
        int[] nums = {0, 1, 0, 3, 2, 3};
        int i = lengthOfLIS1(nums);
        System.out.println("i = " + i);
    }

    public int lengthOfLIS1(int[] nums) {
        // dp[i] 当前第i个元素 最长递增子序列的长度
        /*
           若nums[i] > nums[j] dp[i] = dp[j]+1
           若小于,则为dp[i] = Math.max(dp[j],dp[i])
         */
        int[] res = new int[nums.length];
        // 最小序列为1  数组初始化为1
        for (int i = 0; i < nums.length; i++) {
            res[i] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    res[i] = res[j] + 1;
                } else {
                    res[i] = Math.max(res[i], res[j]);
                }
            }
        }
        return Arrays.stream(res).max().getAsInt();
    }

    @Test
    public void maxProduct() {
//        int[] nums = {2,3,-2,4};
        int[] nums = {-1, 2, 4, 1};
        int i = maxProduct(nums);
        System.out.println("i = " + i);
    }

    public int maxProduct(int[] nums) {
        // dp[i] 表示经过第i个元素的乘积最大的值
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = nums[i];
            int max = res[i];
            for (int j = i - 1; j >= 0; j--) {
                res[i] = res[i] * nums[j];
                max = Math.max(res[i], max);
            }
            res[i] = max;
        }
        System.out.println(Arrays.toString(res));
        return Arrays.stream(res).max().getAsInt();
    }

    @Test
    public void maxSubArray() {
//        int[] nums = {2,3,-2,4};
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int i = maxSubArray(nums);
        System.out.println("i = " + i);
    }

    public int maxSubArray(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = nums[i];
            int max = res[i];
            for (int j = i - 1; j >= 0; j--) {
                res[i] = res[i] + nums[j];
                max = Math.max(res[i], max);
            }
            res[i] = max;
        }
        System.out.println(Arrays.toString(res));
        return Arrays.stream(res).max().getAsInt();
    }

    @Test
    public void maxSubArray1() {
        int[] nums = {2, 3, -2, 4};
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int i = maxSubArray1(nums);
        System.out.println("i = " + i);
    }

    public int maxSubArray1(int[] nums) {
        // dp[i] 表示第i个元素前子数组最大和为dp[i]
        // pre表示 i元素前子数组最大和
        // max 表示 最大子数组和
        int pre = 0, max = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
    }

    @Test
    public void maxProduct1() {
        int[] nums = {2, 3, -2, 4};
        int max = maxProduct1(nums);
        System.out.println("max = " + max);
    }

    public int maxProduct1(int[] nums) {
        // 根据最大子数组和的转换方程  max[i] = f(max(0<i<i-1)) + nums[i];
        // 但上述方程并不能求出最大乘积子数组，原因 当i 为负数是， 子序列也为负数时， 相乘为最大值， 所以需要求出f(max) 与 f(min)
        // max(i) = max(max(i-1)*a[i],min[i-1]*a[i],a[i])
        // min(i) = min(min(i-1)*a[i],min(i-1)*a[i],a[i])

        int[] min = new int[nums.length];
        int[] max = new int[nums.length];
        int len = nums.length;
        System.arraycopy(nums, 0, min, 0, len);
        System.arraycopy(nums, 0, max, 0, len);
        for (int i = 1; i < nums.length; i++) {
            max[i] = Math.max(max[i], Math.max(max[i - 1] * nums[i], min[i - 1] * nums[i]));
            min[i] = Math.min(min[i], Math.max(max[i - 1] * nums[i], min[i - 1] * nums[i]));
        }
        return Arrays.stream(max).max().getAsInt();
    }

    @Test
    public void maxProduct2() {
//        int[] nums = {2, 3, -2, 4, -5};
//        int[] nums = {-4,-3,-2};
        int[] nums = {2, -5, -2, -4, 3};
        int max = maxProduct2(nums);
        System.out.println("max = " + max);
    }

    public int maxProduct2(int[] nums) {
        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmp = max;
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(tmp * nums[i], min * nums[i]));
            res = Math.max(max, res);
        }
        return res;
    }

    @Test
    public void addBinary() {
        String a = "1110";
        String b = "0001010";
        String re = addBinary(a, b);
        System.out.println("re = " + re);
    }


    /**
     * 二进制中的加法
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int yu = 0;
        int i = a.length() - 1, j = b.length() - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            int res = yu + a.charAt(i) + b.charAt(j) - 96;
            if (res / 2 > 0) {
                yu = 1;
            } else {
                yu = 0;
            }
            sb.append(res % 2);
        }
        while (i >= 0) {
            int res = yu + a.charAt(i) - 48;
            if (res / 2 > 0) {
                yu = 1;
            } else {
                yu = 0;
            }
            sb.append(res % 2);
            i--;
        }
        while (j >= 0) {
            int res = yu + b.charAt(j) - 48;
            if (res / 2 > 0) {
                yu = 1;
            } else {
                yu = 0;
            }
            sb.append(res % 2);
            j--;
        }
        if (yu != 0) {
            sb.append(yu);
        }
        Maps.newHashMap();
        return sb.reverse().toString();
    }

    @Test
    public void addBinary1() {
        /*String a = "1011";
        int i = Integer.parseInt(a, 2);
        System.out.println("i = " + i);
        String s = Integer.toHexString(i);
        System.out.println("s = " + s);
        int i1 = Integer.highestOneBit(i);
        System.out.println("i1 = " + i1);*/
        String a = "1010";
        String b = "1101";
        System.out.println(addBinary1(a, b));
    }

    /**
     * 二进制求和
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary1(String a, String b) {
        // 先将数据转换为10进制计算后，再转换为10进制
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }

    @Test
    public void addBinary2() {
        String a = "1010";
        String b = "1101";
        System.out.println(addBinary2(a, b));
    }

    /**
     * 二进制求和
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary2(String a, String b) {
        // 模拟二进制数相加，逢二进一
        // 高位不足的用0补齐
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int len = Math.max(a.length(), b.length());
        int i = 0;
        for (; i < len; i++) {
            carry += i < a.length() ? a.charAt(a.length() - 1 - i) - 48 : 0;
            carry += i < b.length() ? b.charAt(b.length() - 1 - i) - 48 : 0;
            // 相加后的值
            sb.append(carry % 2);
            // 向前进位的值 0或1
            carry /= 2;
        }
        // 或最高位依然进位，则最加
        if (carry > 0) sb.append(carry);
        return sb.reverse().toString();
    }

    @Test
    public void addBinary3() {
        String a = "11";
        String b = "01";
        System.out.println(addBinary3(a, b));
    }

    /**
     * 二进制求和
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary3(String a, String b) {
        // 使用位运算模拟计算机加法运算
        /* 第一位数求和，不考虑进位     第二位数求和 不考虑进位
            0+0 = 0^0 = 0
            1+0 = 1^0 = 1
            0+1 = 0^1 = 1
            1+1 = 1^1 = 0

         */
        int i = Integer.parseInt(a, 2);
        int j = Integer.parseInt(b, 2);
        while (j != 0) {
            int carry = i ^ j;
            j = (i & j) << 1;
            i = carry;
        }
        System.out.println(i);
        return Integer.toBinaryString(i);
    }

    @Test
    public void hammingWeight() {
        int i = -3;
        int count = hammingWeight1(i);
        System.out.println("count = " + count);
    }

    /**
     * 求二进制中1的个数
     *
     * @param i
     * @return
     */
    public int hammingWeight(int i) {
        int count = 0;
//        i = Integer.parseInt(i+"",2);
        for (int j = 0; j < 32; j++) {
            count += i & 1;
            i >>= 1;
        }
        return count;
    }

    public int hammingWeight1(int i) {
        // 由n&(n-1) 得知 每一次位运算，都将低位的1去除，重复计算，求出1的个数
        int count = 0;
        while (i != 0) {
            i &= (i - 1);
            count++;
        }
        return count;
    }


    @Test
    public void carry() {
        String s = "aaaa";
        int i = Integer.parseInt(s, 16);
        System.out.println("i = " + i);
    }

    @Test
    public void consecutiveNumbersSum() {
        int i = consecutiveNumbersSum(9);
        System.out.println("i = " + i);
    }

    /**
     * 连续整数求和
     *
     * @return
     */
    public int consecutiveNumbersSum(int n) {
        // 暴力解法
        int sum = 1;
        int len = n / 2;
        System.out.println(n);
        for (int i = n - 1; i > 0; i--) {
            int num = i;
            for (int j = i - 1; j > 0; j--) {
                num += j;
                if (num == n) {
                    System.out.println(i + "+" + j);
                    sum++;
                    break;
                }
                if (num > n) {
                    break;
                }

            }
        }

        return sum;
    }

    @Test
    public void consecutiveNumbersSum1() {
        int n = 15;
        int res = consecutiveNumbersSum1(n);
        System.out.println("res = " + res);
    }

    public int consecutiveNumbersSum1(int n) {
        // 9 = 5+4 = 2+3+4
        /* 根据等差数列的特性   从a(i)项到a(j)开始的项和为  (i+j)(j-i+1) = 2*n ==> i,j为正整数 j-i+i 表示连续数列长度x
            且i+j > j-i+1 (i从1开始)  ==> x*x < 2*n  ==> x < sqrt(2n) 则子数列在1~x 之间
            循环找出i  ==>  (i+i+x-1)*x = 2n ==> i = (2n/x-x+1)/2
         */
        int sum = 0;
        for (int i = 1; i < Math.sqrt(2 * n); i++) {
            int m = ((2 * n / i) - i + 1) / 2;
            if ((m + m + i - 1) * i == 2 * n) {
                sum++;
            }
        }
        return sum;
    }

    @Test
    public void consecutiveNumbersSum2() {
        int n = 15;
        System.out.println(consecutiveNumbersSum2(n));
        System.out.println(Math.abs(Integer.MIN_VALUE));
    }

    public int consecutiveNumbersSum2(int n) {
        // 根据等差数列的特性   从a项开始到k项和为  (i+i+k-1)*k = 2*n ==> i*k + k*(k-1)/2 = n
        // 又k*(k-1)/2 为1到k-1的和  sum  所以必须有  n - k*(k-1)/2 = i*k   ==? (n-sum) mod k == 0;
        // 且 n > sum
        int sum = 0, count = 0;
        for (int i = 1; sum < n; i++) {
            if (((n - sum) % i) == 0) {
                int tmp = (n - sum) / i;
                System.out.println(tmp);
                count++;
            }
            sum += i;
        }
        return count;
    }

    /**
     * 有序数组中绝对值差的和
     *
     * @param nums
     * @return
     */
    public int[] getSumAbsoluteDifferences(int[] nums) {
        // 暴力解法
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                sum += Math.abs(nums[i] - nums[j]);
            }
            res[i] = sum;
        }
        return res;
    }

    @Test
    public void getSumAbsoluteDifferences1() {
//        int[] nums = {1,4,6,8,10};
        int[] nums = {-3, -1, 5, 7, 11, 15};
        int[] res = getSumAbsoluteDifferences1(nums);
    }


    public int[] getSumAbsoluteDifferences1(int[] nums) {
        // 根据前缀和 A(n)数列  a[i] = sum[n] - sum[i-1];
        // 又该数组是有序数组  求a[i]的绝对值和,分为左前缀 与 右前缀
        // leftPrefixSum(i) = a[i]-a[1]+a[i]-a[2]+..+a[i]-a[i-1] = a[i]*(i-1)-sum(i-1)
        // rightPrefixSum(i) = a[n]-a[i] + a[n-1]-a[i] +...+a[i+1]-a[i] = sum(n) - sum(i-1) - a[i] *(n-i+1)
        // sum[a[i]] = rightPrefixSum(i) + leftPrefixSum(i) = sum(n) - 2*sum(i-1) - a[i]*(n-i+1-i)
        // 定义结果数组 和 前缀和数组
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int[] res = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        System.out.println(Arrays.toString(dp));
        int n = nums.length;
        res[0] = dp[n - 1] - nums[0] * n;
        res[n - 1] = nums[n - 1] * n - dp[n - 1];
        for (int i = 1; i < nums.length - 1; i++) {
            // res[i] = 左边个数*nums[i] - 左边和 + 右边和 - nums[i]*右边个数
//            res[i] = i*nums[i] - dp[i-1] + dp[n-1]-dp[i-1] - (n-i)*nums[i];
            res[i] = dp[n - 1] - 2 * dp[i - 1] - nums[i] * (n - 2 * i);
        }
        System.out.println(Arrays.toString(res));
        return res;
    }


    @Test
    public void divide() {
        int a = 7;
        int b = -3;
        System.out.println(divide(a, b));
    }

    public int divide(int a, int b) {
        int m = a, n = b;
        a = a < 0 ? a * -1 : a;
        b = b < 0 ? b * -1 : b;
        int count = 0;
        while (a > b) {
            a = a - b;
            count++;
        }
        if ((m > 0 && n < 0) || (m < 0 && n > 0)) {
            return count * -1;
        }
        return count;
    }

    @Test
    public void number() {
        int k = 3;
        int n = 2;
        int[] number = number(k, n);
        System.out.println(Arrays.toString(number));
    }

    /**
     * 求一个正整数m 是否存在n个连续的数和为m
     *
     * @param k
     * @param n
     * @return
     */
    public int[] number(int k, int n) {
        int base = n * (n - 1) / 2;
        if ((k - base) % n != 0) {
            return new int[]{-1};
        }
        int[] arr = new int[n];
        int m = (k - base) / n;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = m + i;
        }
        return arr;
    }

    @Test
    public void eatHuoguo() {
        int[] x = {1, 2, 3};
        int[] y = {2, 1, 1};
        int m = 2;
        System.out.println(eatHuoguo(x, y, m));
    }

    /**
     * 导师吃火锅
     *
     * @param list 第i个菜与该菜所需时间
     * @param m    手速
     * @return
     */
    public int eatHuoguo(int[] x, int[] y, int m) {
        // 计算第个菜的适合时间
        int n = x.length;
        int[] itemTime = new int[n];
        for (int i = 0; i < n; i++) {
            itemTime[i] = x[i] + y[i];
        }
        Arrays.sort(itemTime);
        // 根据手速，计算出最多能吃菜的数量
        int pre = 0;
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (itemTime[i] >= itemTime[pre] + m) {
                count++;
                pre = 1;
            }
        }
        return count;
    }


    @Test
    public void shangshe() {
//        int[] n = {2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 14, 15, 17};
        int[] n = {2, 20, 98, 78, 99, 67, 27, 88, 67, 16};
        int count = shangse(n);
        System.out.println("count = " + count);
    }

    /**
     * 上色
     *
     * @return
     */
    public int shangse(int[] n) {
        for (int i = 0; i < n.length; i++) {
            for (int j = i + 1; j < n.length; j++) {
                if (n[j] < n[i]) {
                    continue;
                }
                if (n[j] % n[i] == 0) {
                    n[j] = n[i];
                }
            }
        }
        int count = 0;
        System.out.println(Arrays.toString(n));
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n.length; i++) {
            if (set.add(n[i])) {
                count++;
            }
        }
        return count;
    }

    public int shangse1(int[] arr) {
        int[] flag = new int[arr.length];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (flag[i] == 1) {
                continue;
            } else {
                for (int j = 1; j < arr.length; j++) {
                    if (arr[j] % arr[i] == 0) {
                        flag[j] = 1;
                    }
                }
            }
            count++;
        }
        return count;
    }

    @Test
    public void huadong() {
        int[] arr = {10, 20, 30, 15, 23, 12};
        int i = huadongSumMax(arr, 3);
        System.out.println(i);
    }

    /**
     * 有一个N个整数的数组，和一个长度为M的窗口，窗口从数组内的第一个数开始滑动直到窗口不能滑动为止
     * 每次窗口滑动产生一个窗口和（窗口内所有数和和），求窗口滑动产生的所有窗口和的最大值。
     *
     * @param arr
     * @return
     */
    public int huadongSumMax(int[] arr, int m) {
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += arr[i];
        }
        int max = sum;
        for (int i = m; i < arr.length; i++) {
            sum = sum + arr[i] - arr[i - m];
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    @Test
    public void xiaoxiaole() {
        String str = "abbaA";
        System.out.println(xiaoxiaole(str));
    }

    /**
     * 字符串消消乐
     *
     * @param src
     * @return
     */
    public String xiaoxiaole(String src) {
        // s = "abbace"  ==>  aace   ==> ce
        String tmp = src;
        int i = 0;
        while (i < tmp.length()) {
            if (i + 1 < tmp.length() && tmp.charAt(i) != tmp.charAt(i + 1)) {
                i++;
                continue;
            }
            if (i == tmp.length() - 1) {
                break;
            }
            int start = i;
            while (i + 1 < tmp.length() && tmp.charAt(i) == tmp.charAt(i + 1)) {
                i++;
            }
            tmp = tmp.substring(0, start) + tmp.substring(i + 1);
            i = 0;
        }
        return tmp;
    }

    @Test
    public void prime() {
        System.out.println(prime(100));
    }

    public List<Integer> prime(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 判断该数是否为素数（质数） 从2开始，只能被1或它本身整除
     *
     * @param i
     * @return
     */
    private boolean isPrime(int num) {
        int i = 2;
        while (i < Math.sqrt(num) + 1) {
            if (num % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    @Test
    public void isPrime1() {
        int num = 6;
        System.out.println(isPrime(num));
    }

    /*public boolean isPrime1(int num) {
        // 由偶数的性质得知 一个偶数必能分解为2与（其他奇数和偶数的积）
        // 若一个数能被2整除，则必然不为质数
        // 所有只需要在奇数中进行判断
        if (num <= 3) {
            return num > 1;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrt; i += 2) {
            if (num % 2 == 0 || num % i == 0) {
                return false;
            }
        }
        return true;
    }*/

    @Test
    public void maxGongyueshu() {
        int m = 150;
        int n = 210;
        System.out.println(maxGongyueshu(m, n));
    }

    /**
     * 两个正整数的最大公约数
     *
     * @param m
     * @param n
     * @return
     */
    public int maxGongyueshu(int m, int n) {
        int p = m > n ? m : n;
        int q = m < n ? m : n;
        while (p % q != 0) {
            int r = p % q;
            p = q;
            q = r;
        }
        return q;
    }

    @Test
    public void countSubstrings() {
        String s = "abcbb";
        System.out.println(countSubstrings(s));
    }

    /**
     * 回文子串的数量
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        // 使用暴力解法
        int sum = s.length();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                String tmp = s.substring(i, j + 1);
                if (isPalindrome(tmp)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    @Test
    public void countSubstrings1() {
        String s = "abcbb";
        System.out.println(countSubstrings1(s));
    }

    /**
     * 回文子串
     *
     * @param s
     * @return
     */
    public int countSubstrings1(String s) {
        // 若子串为回文串，需要尽可能的找到回文点，当回文子串为奇数长度时，回文点为一个字符，当长度为偶数时，回文串为两个，
        // 这两种情况包含的所有情况 再使用双指针依次遍历
        int sum = s.length();
        for (int i = 1; i < s.length(); i++) {
            // 当回文点为一个数时
            sum += palindromeByone(s, i - 1, i + 1);
            if (s.charAt(i) == s.charAt(i - 1)) {
                sum++;
                sum += palindromeByone(s, i - 2, i + 1);
            }
        }
        return sum;
    }


    private int palindromeByone(String s, int start, int end) {
        int num = 0;
        while (start >= 0 && end < s.length()) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
            start--;
            end++;
            num++;
        }
        return num;
    }

    /**
     * 删除字符串中所有相邻的重复项
     * 字符串消消乐
     *
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        String tmp = s;
        int i = 0;
        while (i != tmp.length()) {
            int j = i;
            while (j + 1 < tmp.length() && tmp.charAt(j) != tmp.charAt(j + 1)) {
                j++;
            }
            if (j + 1 == tmp.length()) {
                break;
            }
            int pre = j;
            tmp = sb.append(tmp, i, pre).append(tmp.substring(j + 2)).toString();
            sb.delete(0, tmp.length());
            i = 0;
        }
        return tmp;
    }

    @Test
    public void removeDuplicates1() {
        /*String s = "tmp";
        String tmp = s;
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(s, 0, 1).toString());*/
//        String s = "abbaca";
        String s = "aaaaaaaa";
        String s1 = removeDuplicates1(s);
        System.out.println(s1);
    }

    public String removeDuplicates1(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else if (stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character character : stack) {
            sb.append(character);
        }
        return sb.toString();
    }

    @Test
    public void removeDuplicates2() {
        /*String s = "tmp";
        String tmp = s;
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(s, 0, 1).toString());*/
//        String s = "abbaca";
        String s = "aaaaaaaaa";
        String s1 = removeDuplicates2(s);
        System.out.println(s1);
    }

    public String removeDuplicates2(String s) {
        // 使用stringbuilder模拟栈
        StringBuilder sb = new StringBuilder();
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            if (top >= 0 && sb.charAt(top) == s.charAt(i)) {
                sb.deleteCharAt(top);
                top--;
            } else {
                sb.append(s.charAt(i));
                top++;
            }
        }
        return sb.toString();
    }

    @Test
    public void removeDuplicates3() {
        /*String s = "tmp";
        String tmp = s;
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(s, 0, 1).toString());*/
//        String s = "abbaca";
        String s = "aaaaaaaa";
        String s1 = removeDuplicates3(s);
        System.out.println(s1);
    }

    public String removeDuplicates3(String s) {
        char[] chs = s.toCharArray();
        int top = -1;
        char[] tmp = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (top >= 0 && tmp[top] == s.charAt(i)) {
                top--;
            } else {
                tmp[++top] = s.charAt(i);
            }
        }
        return new String(tmp, 0, top + 1);
    }

    @Test
    public void findDuplicates() {
        int[] nums = {4, 3, 2, 1};
        System.out.println(findDuplicates(nums));
    }

    /**
     * 找出数组中的重复数据
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        // 找到数组中出现2次的数据 数组中有n个元素 且元素取值在1-n
        // 4,3,2,7,8,2,3,1
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        System.out.println(Arrays.toString(nums));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                list.add(nums[i]);
            }
        }
        return list;
    }

    @Test
    public void findDuplicate1() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDuplicate1(nums));
    }

    public List<Integer> findDuplicate1(int[] nums) {
        // 由于数组中的元素只会出现一次或两次，且元素取值在1-n
        // 可以使用负数来标识判断元素是否出现过 对于元素i 当nums[nums[i]-1] 为正数，表示该元素未出现过，
        // 将nums[nums[i]-1] = -nums[nums[i]-1] 当nums[nums[i]-1]为负数时，表示元素已经出现过一次，将abs(nums[i])添加到结果
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            } else {
                list.add(index + 1);
            }
        }
        return list;
    }

    @Test
    public void removeDuplicates4() {
        int[] nums = {1, 1, 2, 2, 2, 3};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] == nums[right]) {
                right++;
            } else if (nums[right] > nums[left]) {
                nums[++left] = nums[right++];
            }
        }
        return left + 1;
    }

    @Test
    public void removeDuplicates() {
        /*String s = "tmp";
        String tmp = s;
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(s, 0, 1).toString());*/
//        String s = "abbaca";
        String s = "aaaaaaaa";
        String s1 = removeDuplicates(s);
        System.out.println(s1);
//        long[] ch = new long[10];
//        System.out.println(ch[0]);

    }

    @Test
    public void longestCommonSubsequence() {
        //"bsbininm"
        //"jmjkbkjkv"
        String s1 = "bsbininm";
        String s2 = "jmjkbkjkv";
        System.out.println(longestCommonSubsequence(s1, s2));
    }

    /**
     * 最长公共子序列
     *
     * @param s1
     * @param s2
     * @return
     */
    public int longestCommonSubsequence(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[m][n];
    }

    int maxSub = Integer.MIN_VALUE;

    @Test
    public void longestCommonSubsequence2() {
        //"pmjghexybyrgzczy"
        //"hafcdqbgncrcbihkd"
        String s1 = "pmjghexybyrgzczy";
        String s2 = "hafcdqbgncrcbihkd";
        longestCommonSubsequence2(0, 0, 0, s1, s2);
        System.out.println(maxSub);
    }

    @Test
    public void lwstdis() {
        String s1 = "mitcmu";
        String s2 = "mtacnu";
        lwstdis(0, 0, 0, s1, s2);
        System.out.println(minSub);
    }

    int minSub = Integer.MAX_VALUE;

    public void lwstdis(int i, int j, int max, String s1, String s2) {
        if (i == s1.length() || j == s2.length()) {
            if (i < s1.length()) {
                max += (s1.length() - i);
            }
            if (j < s2.length()) {
                max += (s2.length() - j);
            }
            if (max < minSub) {
                minSub = max;
            }
            return;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            lwstdis(i + 1, j + 1, max, s1, s2);
        } else {
            lwstdis(i + 1, j + 1, max + 1, s1, s2);
            lwstdis(i + 1, j, max + 1, s1, s2);
            lwstdis(i, j + 1, max + 1, s1, s2);
        }
    }

    @Test
    public void lwstdis1() {
        String s1 = "mitcmu";
        String s2 = "mtacmu";
        System.out.println(lwstdis1(s1, s2));
    }

    public int lwstdis1(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        // ch1与ch2的dis距离可以通过删除，增加，或替换
        // dp[i][j]  表示 ch1[0,i]与ch2[0,j]的最短莱文斯坦距离(dis)  所以当ch1[i] != ch2[j]时 则从dp[i-1][j-1]变换到dp[i][j
        // ]需要加一,同理 dp[i-1][j],dp[i][j-1]也需要加1 所以dp[i][j] = min(dp[i-1][j-1]+1,dp[i-1][j]+1,dp[i][j-1]+1)
        // 当ch1[i] == ch2[j]时，dp[i-1][j-1]变换到i,j不需要加1，因为相等， 但dp[i-1][j],dp[i][j-1] 变换到i,j时也需要加1
        // 需要添加，或删除一个字符 相当于 ab
        int m = ch1.length;
        int n = ch2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j] + 1, dp[i][j - 1]));
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[m][n];
    }

    public void longestCommonSubsequence2(int i, int j, int max, String s1, String s2) {
        if (i == s1.length() || j == s2.length()) {
            if (max > maxSub) {
                maxSub = max;
            }
            return;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            longestCommonSubsequence2(i + 1, j + 1, max + 1, s1, s2);
        } else {
            longestCommonSubsequence2(i, j + 1, max, s1, s2);
            longestCommonSubsequence2(i + 1, j, max, s1, s2);
        }
    }

    @Test
    public void longestPalindromeSubseq() {
        String str = "bbbab";
        System.out.println(longestPalindromeSubseq(str));
    }

    /**
     * 最长回文字子序列
     *
     * @param str
     * @return
     */
    public int longestPalindromeSubseq(String str) {
        // 定义dp[i][j]为 str[i][j]的最长回文子序列, i < j 且0<= i < j < n但，子序列长度为1时，dp[i][i] = 1;
        // 当str[i] == str[j] 表示在i+1,j-1子回文串上加2  所以dp[i][j] = dp[i+1][j-1]+2
        // 当str[i] != str[j] 时，则这两个子序列不能同时出现在首尾，所以dp[i][j] = max(dp[i+1][j],dp[i][j-1])
        // 初始化条件 dp[i][i]都为1，其他则初始化为0
        // 遍历顺序 由于是将子字符串由小到大遍历，我们可以倒序遍历 其结果为dp[0][n-1]
        int n = str.length();
        char[] ch = str.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            // 初始化 dp[i][i] = 1
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (ch[i] == ch[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][n - 1];
    }

    @Test
    public void longestPalindromeSubseq1() {
        String str = "bbbahreab";
        System.out.println(longestPalindromeSubseq1(str));
    }

    public int longestPalindromeSubseq1(String str) {
        int n = str.length();
        char[] ch = str.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            // 初始化 dp[i][i] = 1
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (ch[i] == ch[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        char[] tmp = new char[n];
        int i = 0, j = n - 1;
        while (i < j) {
            if (ch[i] == ch[j]) {
                tmp[i] = ch[i];
                tmp[j] = ch[j];
                i++;
                j--;
                continue;
            }
            if (dp[i][j] == dp[i + 1][j]) {
                tmp[i] = '\0';
                i++;
            }
            if (dp[i][j] == dp[i][j - 1]) {
                tmp[j - 1] = '\0';
                j--;
            }
        }
        for (int k = 0; k < n; k++) {
            if (tmp[k] == '\0') {
                continue;
            }
            sb.append(tmp[k]);
        }
        System.out.println(sb);
        for (i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][n - 1];
    }

    @Test
    public void mysqrt() {
        System.out.println(mysqrt(8));
    }

    public int mysqrt(double x) {
        // 使用牛顿迭代法
        // y = x2 - C // k = 2x 经过点(x,x2-c)的方程为
        double C = x, x0 = x;
        while (true) {
            x = (x0 + C / x0) / 2;
            if (Math.abs(x - x0) < Math.pow(10, -7)) {
                break;
            }
            x0 = x;
        }
        return (int) x;
    }

    @Test
    public void mysqrt1() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            int t = mysqrt1(i);
            System.out.println(t);
        }
        System.out.println(mysqrt1(2147395599));
//        System.out.println(mysqrt1(8));
    }

    public int mysqrt1(int x) {
        // 使用二分法求x的平方根
        int l = 0, r = x, res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
            /*if ((long) mid * mid > x) {
                r = mid - 1;
            } else {
                res = mid;
                l = mid + 1;
            }*/
        }
        return res;
    }

    @Test
    public void avg() {
        int a = 2147483647;
        int b = 2147483647;
        System.out.println((a & b) + ((a ^ b) >> 1));
        System.out.println((a >> 1) + (b >> 1));
    }

    /**
     * 剑指 Offer II 061. 和最小的 k 个数对
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        return null;
    }

    @Test
    public void kthSmallest() {
//        int[][] matrix =  {{1,5,9}, {10,11,13},{12,13,15}};
        int[][] matrix = {{-5}};
        int i = kthSmallest(matrix, 1);
        System.out.println("i = " + i);
    }

    /**
     * 378. 有序矩阵中第 K 小的元素
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue(Comparator.reverseOrder());
        int len = matrix[0].length;
        int m = 0;
        int n = 0;
        for (int i = 0; i < k; i++) {
            queue.add(matrix[m][n]);
            n++;
            if ((i + 1) % len == 0) {
                m++;
                n = 0;
            }
        }
        for (; m < len; m++) {
            for (; n < len; n++) {
                if (!queue.isEmpty() && queue.peek() > matrix[m][n]) {
                    queue.poll();
                    queue.add(matrix[m][n]);
                }
            }
            n = 0;
        }
        return queue.peek();
    }

    @Test
    public void kthSmallest1() {
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
//        int[][] matrix = {{-5}};
        int i = kthSmallest1(matrix, 8);
        System.out.println("i = " + i);
    }

    public int kthSmallest1(int[][] matrix, int k) {
        // 使用归并排序 合并n个数组  使用优先级队列小顶堆实现n路归并
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int len = matrix[0].length;
        for (int i = 0; i < len; i++) {
            // 存放元素  依次为i行的首元素，第i行， 第0列的；
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k-1; i++) {
            int[] cur = pq.poll();
            if (cur[2] < len - 1) {
                pq.offer(new int[]{matrix[cur[1]][cur[2] + 1], cur[1], cur[2] + 1});
            }
        }
        return pq.peek()[0];
    }


    @Test
    public void findKthitem() {
        // 求N个元素中选出最小的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int[] arr = {10, 14, 32, 62, 13, 35, 23, 15, 743, 2, 46, 6};
        int k = 5;
        // 先将k个元素存放到优化队列中
        for (int i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }
        // 再将剩余的元素存放到队列中，若元素小于堆顶元素，则存放到队列中
        for (int i = k; i < arr.length; i++) {
            if (!pq.isEmpty() && pq.peek() > arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.remove();
        }
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void longestPalindromeSubseq2() {
        String str = "bbbahreab";
        System.out.println(longestPalindromeSubseq2(str));
    }

    public int longestPalindromeSubseq2(String str) {
        int n = str.length();
        char[] ch = str.toCharArray();
        String[][] dp = new String[n][n];
        for (int i = n - 1; i >= 0; i--) {
            // 初始化 dp[i][i] = 1
            dp[i][i] = ch[i] + "";
            for (int j = i + 1; j < n; j++) {
                if (ch[i] == ch[j]) {
                    dp[i][j] = ch[i] + dp[i + 1][j - 1] + ch[j];
                } else {
                    if (dp[i + 1][j].length() > dp[i][j - 1].length()) {
                        dp[i][j] = dp[i + 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }
        System.out.println(dp[0][n - 1]);
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][n - 1].length();
    }


    @Test
    public void longestPalindromeSubstr() {
        String str = "bb";
        System.out.println(longestPalindromeSubstr(str));
    }

    /**
     * 最长回文子串
     *
     * @param str
     * @return
     */
    public String longestPalindromeSubstr(String str) {
        int n = str.length();
        char[] ch = str.toCharArray();
        // dp[i][j] 表示str[i][j]的子串是否为回文串 当str[i]= str[j]时， j-i <3 时dp[i][j]必为true  aba  aa a
        // 否则 dp[i][j] = dp[i+1][j-1]  若不相等则dp[i][j] = false
        boolean[][] dp = new boolean[n][n];
        // 由于子串长度为1时，都是回文串 所以 dp[i][i] = true;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int maxLen = 1;
        int begin = 0;
        // 再依次遍历子串长度为2，3,4,..n到的情况
        for (int L = 2; L < n; L++) {

            for (int i = 0; i <= n; i++) {
                int j = L - 1 + i;
                if (j >= n) {
                    break;
                }
                if (ch[i] != ch[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && L > maxLen) {
                    maxLen = L;
                    begin = i;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return str.substring(begin, begin + maxLen);
    }

    @Test
    public void test01() {
        byte[] bytes = Base64.getEncoder().encode("str".getBytes(StandardCharsets.UTF_8));
        System.out.println(Arrays.toString(bytes));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        byte[] temp = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(temp, 0, temp.length)) != -1) {
            String str = new String(temp, 0, len);
            System.out.println(str);
        }
    }

    /**
     * 正整数分解质因子
     *
     * @param n
     * @return
     */
    public int[] zhengzheshuPrime(int n) {

        return null;
    }

    public static String ten2two(int src) {
        StringBuilder sb = new StringBuilder();
        int count = 4;
        while (src / 2 > 0 || count > 0) {
            if (src == 0) {
                sb.append("0");
            } else {
                sb.append(src % 2);
                src = src / 2;
            }
            count--;
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String[] splits = in.nextLine().split(" ");
            handle(splits[0], splits[1]);
        }
    }

    public static char two2ten(String src) {
        int res = 0;
        for (int i = 0, j = 3; i < src.length(); i++, j--) {
            res += ((src.charAt(i) - 48) * Math.pow(2, j));
        }
        char c = (char) (res > 9 ? res + 55 : res + 48);
        return c;
    }

    public static void handle(String s1, String s2) {
        String src = s1 + s2;
        char[] chs = src.toCharArray();
        for (int i = 0, j = 1; i < src.length() && j < src.length(); i += 2, j += 2) {
            int mini = i;
            int minj = j;
            for (int m = i + 2, n = j + 2; m < src.length() && n < src.length(); m += 2, n += 2) {
                if (chs[m] < chs[mini]) {
                    mini = m;
                }
                if (chs[n] < chs[minj]) {
                    minj = n;
                }
            }
            swap(chs, i, mini);
            swap(chs, j, minj);
        }
        System.out.println(chs);
        for (int i = 0; i < chs.length; i++) {
            int res = chs[i] >= 65 ? Character.toUpperCase(chs[i]) - 55 : chs[i] - 48;
            String t = ten2two(res);
            chs[i] = two2ten(t);
        }
        System.out.println(chs);
        System.out.println(new String(chs));
    }


    public static void swap(char[] src, int i, int j) {
        char t = src[i];
        src[i] = src[j];
        src[j] = t;
    }

    @Test
    public void pow() {
        System.out.println(Math.pow(2, 3));
        System.out.println(Integer.valueOf('0'));
    }
}
