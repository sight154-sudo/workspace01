package com.huawei.algorithm.sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组，求出这个数组中的逆序对的总数。
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
        int[] arr = {2, 3, 1, 1, 4};
//        int[] arr = {3, 2, 1, 0, 4};
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
        int[] arr = {2, 3, 0, 1, 4};
//        int[] arr = {1, 1, 1, 1, 4};
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

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），
     * 并返回该子数组所对应的乘积。
     *
     * @param nums
     * @return
     */
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

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 子数组 是数组中的一个连续部分。
     *
     * @param nums
     * @return
     */
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
    public List<List<Integer>> kSmallestPairs(int[] num1, int[] num2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return num1[o1[0]] + num2[o1[1]] - num1[o2[0]] - num2[o2[1]];
            }
        });
        List<List<Integer>> res = new ArrayList<>();
        int m = num1.length;
        int n = num1.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.add(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] poll = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(num1[poll[0]]);
            list.add(num2[poll[1]]);
            res.add(list);
            if (poll[1] + 1 < n) {
                pq.add(new int[]{poll[0], poll[1] + 1});
            }
        }
        return res;

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
        for (int i = 0; i < k - 1; i++) {
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

    @Test
    public void validSquare() {
        int[] p1 = {0, 0};
        int[] p2 = {0, 1};
        int[] p3 = {1, 1};
        int[] p4 = {1, 0};
        System.out.println(validSquare(p1, p2, p3, p4));
    }

    /**
     * 有效的正方形 593
     *
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (p1[0] == p2[0] && p1[1] == p2[1]) {
            return false;
        }
        int len1 = isEqual(p1, p2);
        int len2 = isEqual(p1, p3);
        int len3 = isEqual(p1, p4);
        int len4 = isEqual(p2, p3);
        int len5 = isEqual(p2, p4);
        int len6 = isEqual(p3, p4);
        int[] res = {len1, len2, len3, len4, len5, len6};
        Arrays.sort(res);
        return res[0] == res[3] && res[4] == res[5];
//        int[] p1p2 = new int[]{p1[0]-p2[0], p1[1]-p2[1]};
//        int[] p1p4 = new int[]{p1[0]-p4[0], p1[1]-p4[1]};
//        if (len1 == len2 && len1 == len3 && len1 == len4 && iscos(p1p2,p1p4)) {
//            return true;
//        }
//        return false;
    }

    public int isEqual(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    public boolean iscos(int[] p1, int[] p2) {
        // 两向量积为0，则垂直
        return p1[0] * p2[0] + p1[1] * p2[1] == 0;
    }

    @Test
    public void lastRemaining() {
        int n = 5;
        int m = 3;
        System.out.println(lastRemaining(n, m));
    }

    /**
     * 约瑟夫环问题 圆圈中最后剩下的数
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        Node head = new Node(0);
        Node cur = head;
        for (int i = 1; i < n; i++) {
            Node node = new Node(i);
            cur.next = node;
            cur = node;
        }
        Node pre = cur;
        cur.next = head;
        cur = head;
        int count = 1;
        while (true) {
            if (cur.val == cur.next.val) {
                break;
            }
            if (count == m) {
                pre.next = cur.next;
                cur = cur.next;
                count = 1;
            } else {
                count++;
                pre = cur;
                cur = cur.next;
            }
        }
        return cur.val;
    }

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    @Test
    public void lastRemaining1() {
        int n = 5;
        int m = 3;
        System.out.println(lastRemaining1(n, m));
    }

    /**
     * 约瑟夫环问题 循环数组
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining1(int n, int m) {
        int alive = n;
        int num = 0;
        int index = 0;
        int[] arr = new int[n];
        while (alive > 0) {
            num += 1 - arr[index];
            if (num == m) {
                arr[index] = 1;
                System.out.print(index);
                alive--;
                num = 0;
            }
            index = (index + 1) % n;
        }
        System.out.println(Arrays.toString(arr));
        return 1;
    }

    @Test
    public void lastRemaining2() {
        int n = 5;
        int m = 3;
        System.out.println(lastRemaining2(n, m));
    }

    public int lastRemaining2(int n, int m) {
        int f = 0;
        for (int i = 2; i <= n; i++) {
            f = (m + f) % i;
        }
        return f;
    }

    @Test
    public void findDiagonalOrder() {
//        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] mat = {{2, 3}};
        int[] diagonalOrder = findDiagonalOrder(mat);
        System.out.println(Arrays.toString(diagonalOrder));
    }

    /**
     * 对角线遍历  498
     *
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat[0].length; // 长
        int m = mat.length;
        int total = m + n - 1;
        int[] res = new int[m * n];
        int index = 0;
        int count = 0;
        while (count < total) {
            // 第1，3，5。。。趟
            int x1 = count < m ? count : m - 1;
            int y1 = count - x1;
            while (x1 >= 0 && y1 < n) {
                res[index++] = mat[x1][y1];
                y1++;
                x1--;
            }
            count++;
            // 第2，4，6趟
            int y2 = count < n ? count : n - 1;
            int x2 = count - y2;
            while (y2 >= 0 && x2 < m) {
                res[index++] = mat[x2][y2];
                y2--;
                x2++;
            }
            count++;
        }
        return res;
    }

    @Test
    public void findDiagonalOrder1() {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] mat = {{2, 3}};
        int[] diagonalOrder = findDiagonalOrder1(mat);
        System.out.println(Arrays.toString(diagonalOrder));
    }

    public int[] findDiagonalOrder1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m * n];
        int index = 0;
        // 使用flag标识 x,y的上限
        boolean flag = true;
        for (int i = 0; i < m + n - 1; i++) {
            int pm = flag ? m : n;
            int pn = flag ? n : m;

            int x = i < pm ? i : pm - 1;
            int y = i - x;
            while (x >= 0 && y < pn) {
                res[index++] = flag ? mat[x][y] : mat[y][x];
                x--;
                y++;
            }
            flag = !flag;
        }
        return res;
    }

    /**
     * 后缀表达式求值 150  "10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        String reg = "[\\+\\-\\*\\/]";
        Pattern pattern = Pattern.compile(reg);
        Stack<String> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (pattern.matcher(tokens[i]).matches()) {
                int m = Integer.valueOf(stack.pop());
                int n = Integer.valueOf(stack.pop());
                res = expr(tokens[i], n, m);
                stack.push(res + "");
            } else {
                stack.push(tokens[i]);
            }
        }
        return Integer.valueOf(stack.pop());
    }

    @Test
    public void evalRPN() {
        System.out.println(Pattern.matches("[\\+\\-\\*\\/]", "+"));
        System.out.println(Pattern.matches("[\\+\\-\\*\\/]", "-"));
        System.out.println(Pattern.matches("[\\+\\-\\*\\/]", "*"));
        System.out.println(Pattern.matches("[\\+\\-\\*\\/]", "/"));
        System.out.println(Pattern.matches("[\\+\\-\\*\\/]", "123"));
        System.out.println(Pattern.matches("[\\+\\-\\*\\/]", "12"));
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens));
    }

    /**
     * @param eval 运算符号
     * @param i    左操作数
     * @param j    右操作数
     * @return
     */
    public int expr(String eval, int i, int j) {
        switch (eval) {
            case "+":
                return i + j;
            case "-":
                return i - j;
            case "*":
                return i * j;
            case "/":
                return i / j;
        }
        return -1;
    }


    @Test
    public void subarraySum() {
//        int[] nums = {1,1,1};
//        int[] nums = {1,2,3};
        int[] nums = {28, 54, 7, -70, 22, 65, -6};
        int k = 100;
        System.out.println(subarraySum(nums, k));
    }

    /**
     * 和为K的子数组的个数
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int i = 0;
        int j = i + 1;
        int count = 0;
        int sum = 0;
        while (i < nums.length) {
            sum += nums[i];
            if (nums[i] == k) {
                count++;
            }
            while (j < nums.length) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
                j++;
            }
            sum = 0;
            i++;
            j = i + 1;
        }
        return count;
    }

    @Test
    public void subarraysum1() {
        int[] nums = {28, 54, 7, -70, 22, 65, -6};
        int k = 100;
        System.out.println(subarraysum1(nums, k));
    }

    public int subarraysum1(int[] nums, int k) {
        // 计算以第i个元素结尾时，是否有子数组和为k
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void subarraysum2() {
        int[] nums = {28, 54, 7, -70, 22, 65, -6};
        int k = 100;
        System.out.println(subarraysum2(nums, k));
    }

    public int subarraysum2(int[] nums, int k) {
        // 求出第i个元素的前缀和后，依次遍历 left到right的和是否为k
        int count = 0;
        int[] pre = new int[nums.length + 1];
        pre[0] = 0;
        for (int i = 1; i < pre.length; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (pre[i + 1] - pre[j] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void subarraysum3() {
        int[] nums = {28, 54, 7, -70, 22, 65, -6};
        int k = 100;
        System.out.println(subarraysum3(nums, k));
    }

    public int subarraysum3(int[] nums, int k) {
        // p(i) 表示 前i个元素的前缀和
        // 根据求子数组和为k得出  p(i)  p(j-1) = k   ==>  p(i) -k = p(j)
        // 需要求出第i个元素时 map(p(i)-k)的个数，即p(j)的个数  遍历0-i 求出所有满足的条件的总个数
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // 初始化 表示0-i的子数组和为k的情况
        map.put(0, 1);
        // pre用来记录前i个前缀和
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)) {
                // 表示 存在子数组和为k的情况
                count += map.get(pre - k);
            }
            // 将当前前缀和的记录存在到map中
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    @Test
    public void desc2oct() {
        String s = "158";
        String s1 = Integer.toOctalString(158);
        System.out.println(s1);
        System.out.println(dec2oct(s));
    }

    public String dec2oct(String src) {
        int s = Integer.parseInt(src, 10);
        Stack<Integer> stack = new Stack<>();
        while (true) {
            if (s / 8 == 0) {
                stack.push(s % 8);
                break;
            }
            stack.push(s % 8);
            s = s / 8;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 20
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        // 使用栈进行括号匹配
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (!map.get(pop).equals(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void isValid() {
        String s = "()";
        System.out.println(isValid(s));
    }

    @Test
    public void handlerStr() {
        /*Pattern pattern = Pattern.compile("[\\+\\-\\*\\/(\\)]");
        Matcher matcher = pattern.matcher(suf);
        while (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
        }*/

        Pattern p1 = Pattern.compile("\\d{1,}");
        Pattern p2 = Pattern.compile("[\\+\\-\\*\\/(\\)]");
        String suf = "(61+3)*(522*4+(431-2))";
        Matcher m1 = p1.matcher(suf);
        Matcher m2 = p2.matcher(suf);
        List<String> list = new ArrayList<>();
        while (true) {
            String s;
            if (m1.find()) {
                s = m1.group();
            } else if (m2.find()) {
                s = m2.group();
            } else {
                break;
            }
            list.add(s);
        }
        System.out.println(list);
    }

    @Test
    public void transform() {
        // (6+3*(7-4))-8/2     6374-*+82/-
//        String[] suf = {"(", "6", "+", "3", "*", "(", "7", "-", "4", ")", ")", "-", "8", "/", "2"};
        String[] suf = {"6", "-", "(", "3", "+", "2", ")", "*", "3"};
        System.out.println(transform(suf));
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param suf
     * @return
     */
    public String transform(String[] suf) {
        /* 使用两个栈，一个数字栈，一个符号栈，当遇到数字时，将数字压入数字栈， 当遇到符号栈时，
           比较运算符号的优先级（priority） 左括号最小， +-依次，/*最高，
           比较当前符号与符号栈顶的元素  若当前元素的优先级大于栈顶元素, 则入栈， 若小于栈顶元素的优先级
           ,则一直将栈顶的元素弹压，并压入数字栈中，
           若遇到右括号，则一直弹栈，并入数栈，直到遇到左括号,
           最后若符号栈中还有元素，则压入数字栈中，
         */
        Pattern p1 = Pattern.compile("\\d{1,}");
        Pattern p2 = Pattern.compile("[\\+\\-\\*\\/(\\)]");
        // 数字栈
        Stack<String> s1 = new Stack<>();
        // 符号栈
        Stack<String> s2 = new Stack<>();
        for (int i = 0; i < suf.length; i++) {
            String s = suf[i];
            if (p1.matcher(s).matches()) {
                // 数字
                s1.push(s);
                continue;
            }
            // 匹配到符号，则需要比较优先级
            if (s.equals(")")) {
                // 若匹配到右括号，则直接弹出栈顶元素，直到遇到(
                while (!s2.isEmpty()) {
                    String pop = s2.pop();
                    if (pop.equals("(")) {
                        break;
                    }
                    s1.push(pop);
                }
            } else if (s.equals("(")) {
                s2.push(s);
            } else {
                // 符号需要比较优先级别

                while (!s2.isEmpty()) {
                    int compare = priority(s) - priority(s2.peek());
                    if (compare > 0) {
                        // 栈顶元素优先级小于当前元素
                        s2.push(s);
                        break;
                    } else {
                        s1.push(s2.pop());
                    }
                }
                if (s2.isEmpty()) {
                    s2.push(s);
                }
            }
        }
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        // 计算后缀表达式的解
        /*while (!s1.isEmpty()) {
            String pop = s1.pop();
            if (p1.matcher(pop).matches()) {
                // 数字直接添加到栈中
                s2.push(pop);
            } else {
                // 符号，则栈中头两个数字进行计算
                Integer i1 = Integer.valueOf(s2.pop());
                Integer i2 = Integer.valueOf(s2.pop());
                int expr = expr(pop, i1, i2);
                s2.push(expr+"");
            }
        }
        System.out.println(s2.peek());*/
        return s1.toString();
    }

    private int priority(String s1) {
        if (s1.equals("(")) {
            return 0;
        } else if (s1.equals("+") || s1.equals("-")) {
            return 1;
        } else if (s1.equals("*") || s1.equals("/")) {
            return 2;
        }
        return -1;
    }

    @Test
    public void shugezi() {
        int[][] arr = {
                {1, 0, 1, 0},
                {1, 1, 1, 1},
                {0, 1, 0, 1},
                {0, 0, 1, 1}};
        int[] target = {3, 3};
        System.out.println(shugezi(arr, target));
    }

    /**
     * 数格子  给定一个 n*n 二维整型数组，在二维数组上随机分布着0和1的值。现输入一个坐标点，输出坐标周围一圈一的个数
     *
     * @param arr
     * @param target
     * @return
     */
    public int shugezi(int[][] arr, int[] target) {
        int n = arr.length;
        int i = target[0];
        int j = target[1];
        int count = 0;
        for (int x = i - 1, p = 3; p > 0; x++, p--) {
            for (int y = j - 1, q = 3; q > 0; y++, q--) {
                if (x == i && y == j) {
                    continue;
                } else if (x >= 0 && y >= 0 && x < n && y < n) {
                    if (arr[x][y] == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 格式化字符串输出
     * 输入形如 AB-ABC-cABd-Cb@ 的字符串，输入待分隔长度k；
     * 要求输出保留第一个“-”前面的字符串格式，后面的每k个字符一分格，
     * 每三个字符中，大写字母数多的三个字母转大写，小写字母数多的三个字母转小写，一样多的不处理。
     *
     * @param src
     */
    public void sprint(String src, int k) {
        int index = src.indexOf("-");
        StringBuilder sb = new StringBuilder();
//        sb.append(src.substring(index + 1)).replace(0,sb.length(), "-");
        Arrays.stream(src.substring(index + 1).split("-")).forEach(sb::append);
        String s = sb.toString();
        sb.setLength(0);
        for (int i = 0; i < s.length(); i += 3) {
            int da = 0;
            int xiao = 0;
            int j = 0;
            while (j < 3 && i + j < s.length()) {
                if (s.charAt(i + j) >= 'A' && s.charAt(i + j) <= 'Z') {
                    da++;
                } else if (s.charAt(i + j) >= 'a' && s.charAt(i + j) <= 'z') {
                    xiao++;
                }
                j++;
            }
            String s1 = s.substring(i, i + j);
            sb.append(da > xiao ? s1.toUpperCase() : s1.toLowerCase());
        }
        k += 1;
        for (int i = k - 1; i < sb.length(); i += k) {
            sb.insert(i, "-");
        }
        sb.insert(0, src.substring(0, index + 1));
        System.out.println(sb);
    }

    @Test
    public void sprint() {
        String src = "AB-ABC-cABd-Cb@";
        sprint(src, 3);
    }

    @Test
    public void insert() {
        StringBuilder sb = new StringBuilder("afeaf");
        sb.insert(1, "-");
        System.out.println(sb);
    }

    @Test
    public void treeFolded() {
        int[][] arr = {
                {3, 1},
                {5, 1},
                {4, 3},
                {10, 5},
                {11, 5},
                {12, 4},
                {9, 10},
                {8, 9}
        };
        int k = 5;
        treeFolded(arr, k);
    }

    public void treeFolded(int[][] arr, int k) {
        int n = arr.length;
        // 使用标识判断第i个数组是否存在
        int[] alive = new int[n];
        // 使用集合保存需要删除的子节点
        List<Integer> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(k);
        while (!temp.isEmpty()) {
            list.addAll(temp);
            temp.clear();
            for (int j = list.size() - 1; j >= 0; j--) {
                for (int i = 0; i < n; i++) {
                    if (alive[i] == 0 && arr[i][1] == list.get(j)) {
                        // 当前节点为需要删除的子节点，标记该数组，并将子节点添加到temp中
                        alive[i] = 1;
                        temp.add(arr[i][0]);
                    }
                }
                list.remove(j);
            }
        }
        for (int i = 0; i < n; i++) {
            if (alive[i] == 0 && arr[i][0] != k) {
                System.out.println(arr[i][0] + " " + arr[i][1]);
            }
        }
    }

    /**
     * 求解方程式
     * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
     * 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
     * <p>
     * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
     *
     * @param equation
     * @return
     */
    public String solveEquation(String equation) {
        // 将右边项移动到左边，符号置反， 使用factor保存系数项的值 ， 使用val保存常数项的值   结果为 -val/factor
        // 使用sign1保存默认项为1， 在=号左边时为1，在=号右边时为-1
        // 使用sign2保存系统项的正负  使用flag判断系数项是否有值，因为x前可能系数为1
        int factor = 0, val = 0, sign1 = 1, index = 0;
        int n = equation.length();
        while (index < n) {
            if (equation.charAt(index) == '=') {
                // 表示计算右边项，sign1标识为-1
                sign1 = -1;
                index++;
                continue;
            }
            int sign2 = sign1;
            if (equation.charAt(index) == '+' || equation.charAt(index) == '-') {
                // 系数项为负时，需要转换
                sign2 = equation.charAt(index) == '-' ? -sign1 : sign1;
                index++;
            }
            // 使用num保存常数项
            int num = 0;
            // 使用flag标识x前是否有常数项
            boolean flag = false;
            while (index < n && Character.isDigit(equation.charAt(index))) {
                num = num * 10 + equation.charAt(index) - 48;
                flag = true;
                index++;
            }
            // 计算系数项与常数项
            if (index < n && equation.charAt(index) == 'x') {
                factor += flag ? num * sign2 : sign2;
                index++;
            } else {
                val += num * sign2;
            }
        }
        // 处理结果
        if (factor == 0 && val == 0) {
            return "Infinite solutions";
        } else if (factor == 0 && val != 0) {
            return "No solution";
        }
        return "x=" + ((-val) / factor);
    }

    @Test
    public void solveEquation() {
//        String equation = "x+5-3+x=6+x-2" ;
//        String equation = "2x=x" ;
        String equation = "3x=33+22+11";
//        String equation = "x+5-3+x=6+x-2" ;
        System.out.println(solveEquation(equation));
    }

    /**
     * 给定一个非空整型数组，其元素数据类型为整型，请按照数组元素十进制最低位从小到大进行排序，十进制最低位相同的元素，相对位置保持不变。
     * 当数组元素为负值时，十进制最低位等同于去除符号位后对应十进制值最低位。
     *
     * @param arr
     */
    public void sortByLower(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                int m = arr[j] < 0 ? arr[j] * -1 % 10 : arr[j] % 10;
                int n = arr[j + 1] < 0 ? arr[j + 1] * -1 % 10 : arr[j + 1] % 10;
                if (m > n) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    @Test
    public void sortByLower() {
        int[] arr = {-21, 1, 2, 5, 22, 11, 55, -101, 42, 8, 7, 32};
        sortByLower(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 早餐组合 一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks 中记录了每种饮料的价格。
     * 小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/2vYnGI
     *
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        // 暴力法
        int m = staple.length;
        int n = drinks.length;
        int total = 0;
        for (int i = 0; i < m; i++) {
            int max = x - staple[i];
            for (int j = 0; j < n; j++) {
                if (max > 0 && drinks[j] <= max) {
                    total++;
                }
            }
        }
        return total;
    }

    @Test
    public void breakfastNumbers() {
        int[] staple = {10, 20, 5};
        int[] drinks = {5, 5, 2};
        int x = 15;
        System.out.println(breakfastNumber1(staple, drinks, x));
    }

    public int breakfastNumber1(int[] staple, int[] drinks, int x) {
        // 排序+二分法
        int m = staple.length;
        int n = drinks.length - 1;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        long total = 0;
        for (int i = 0; i < m; i++) {
            while (n >= 0 && drinks[n] + staple[i] > x) {
                n--;
            }
            total += n + 1;

        }
        return (int) (total % 1000000007);
    }

    @Test
    public void breakfastNumbers2() {
        int[] staple = {2, 1, 1};
        int[] drinks = {8, 9, 5, 1};
        int x = 9;
        System.out.println(breakfastNumber2(staple, drinks, x));
    }

    public int breakfastNumber2(int[] staple, int[] drinks, int x) {
        // 排序+二分法
        int m = staple.length;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        long total = 0;
        for (int i = 0; i < m; i++) {
            int target = x - staple[i];
            if (target < 0) {
                continue;
            }
            int index = binarySearch1(drinks, target);
            total += index;
        }
        return (int) (total % 1000000007);
    }

    private int binarySearch1(int[] drinks, int i) {
        // 找出drinks数组中比i小的数的个数
        int left = 0;
        int right = drinks.length - 1;
        while (left <= right) {
            int mid = (left & right) + ((left ^ right) >> 1);
            if (drinks[mid] > i) {
                right = mid - 1;
            } else if (drinks[mid] <= i) {
                left = mid + 1;
            }
        }
        return drinks[left] > i ? left : left + 1;
    }

    @Test
    public void test() {
        int i = (2 & 3) + ((2 ^ 3) >> 1);
        System.out.println(i);
    }

    @Test
    public void threeSum() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        System.out.println(threeSum(nums));
        System.out.println(7258 ^ 6579 ^ 2602 ^ 6716 ^ 3050 ^ 3564 ^ 5396 ^ 1773);
    }

    /**
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，
     * 使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组
     * 注意：答案中不可以包含重复的三元组。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/3sum
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 暴力
        List<List<Integer>> list = new ArrayList<>();
        int len = nums.length;
        Set<Integer> set = new LinkedHashSet<>();
        int index = len - 1;
        for (int i = 0; i < index; i++) {
            if (!set.add(nums[i])) ;
            {
                while (i > index && !set.add(nums[index])) {
                    index--;
                }
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
        for (int i = 0; i < index; i++) {
            for (int j = i + 1; j < index; j++) {
                for (int k = j + 1; k < index; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        list.add(temp);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 全排序
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        // [1,2,3]  => [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        permute(nums, lists, list);
        return lists;
    }

    private void permute(int[] nums, List<List<Integer>> lists, List<Integer> list) {
        if (list.size() == nums.length) {
            lists.add(new ArrayList<>(list));
        }
        for (int num : nums) {
            if (list.contains(num)) {
                continue;
            }
            list.add(num);
            permute(nums, lists, list);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void permute() {
//        int[] nums = {1,2,3};
//        int[] nums = {1, 0};
        int[] nums = {1, 1, 2};
        List<List<Integer>> permute = permute(nums);
        System.out.println(permute);
    }

    public List<List<Integer>> permuteII(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] sign = new boolean[nums.length];
        Arrays.sort(nums);
        permuteII(nums, 0, lists, list, sign);
        return lists;
    }

    @Test
    public void permuteII() {
        int[] nums = {1, 1, 2};
        System.out.println(permuteII(nums));
    }

    private void permuteII(int[] nums, int index, List<List<Integer>> lists, List<Integer> list, boolean[] sign) {
        if (list.size() == nums.length) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (sign[i] || (i > 0 && nums[i] == nums[i - 1] && !sign[i - 1])) {
                continue;
            }
            list.add(nums[i]);
            sign[i] = true;
            permuteII(nums, index + 1, lists, list, sign);
            sign[i] = false;
            list.remove(index);
        }
    }

    /**
     * 分糖果
     * 给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的 最多 种类数。
     *
     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {
        Arrays.sort(candyType);
        int target = candyType.length / 2;
        int total = 1;
        int pre = candyType[0];
        for (int i = 1; i < candyType.length; i++) {
            if (total == target) {
                break;
            }
            if (candyType[i] != pre) {
                total++;
                pre = candyType[i];
            }
        }
        return total;
    }

    public int distributeCandies1(int[] candyType) {
        int target = candyType.length / 2;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < candyType.length; i++) {
            if (set.size() == target) {
                break;
            }
            set.add(candyType[i]);
        }
        return set.size();
    }

    @Test
    public void distributeCandies() {
        int[] candyType = {1, 1, 2, 2, 3, 3};
//        int[] candyType = {1,1,2,3};
//        int[] candyType = {6,6,6,6};
        System.out.println(distributeCandies1(candyType));
    }

    /**
     * 分糖果II
     * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n颗糖果。
     * 然后，我们再回到队伍的起点，给第一个小朋友  n+ 1 颗糖果，第二个小朋友 n+ 2 颗，依此类推，
     * 直到给最后一个小朋友 2 * n颗糖果。
     * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
     *
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int n = 1;
        int index = 0;
        while (candies > 0) {
            if (candies < n) {
                n = candies;
            }
            res[index] = res[index] + n;
            candies -= n;
            n++;
            index = (index + 1) % num_people;
        }
        return res;
    }

    @Test
    public void distributeCandies2() {
        int[] res = distributeCandies(10, 3);
        System.out.println(Arrays.toString(res));
    }

    public int shareApple(int[] apples) {
        // 先遍历计算满足A的重量
        int a = 0;
        for (int i = 0; i < apples.length; i++) {
            a ^= apples[i];
        }
        int sum = Arrays.stream(apples).sum();
        System.out.println(String.format("a = %s  and sum = %s", a, sum));
        int target = sum - a;
        // 转换为背包最大载重为target时，所能最多的重量
        int[] dp = new int[target + 1];
        // 初始化 最大为0时，最多为0
        // 转换方程 dp[i] = max(dp[i-1],dp[i-apples[i]]+apples[i])
        for (int i = 0; i < apples.length; i++) {
            for (int j = target; j >= apples[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - apples[i]] + apples[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[target];
    }

    @Test
    public void shareApples() {
        int[] apples = {1, 2, 6};
        System.out.println(shareApple(apples));
    }

    public String printBinaryTree(String s) {
        return "";
    }


    /**
     * 组合总数
     * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSum(candidates, target, lists, list, 0, 0);
        return lists;
    }

    @Test
    public void combinationSum() {
//        int[] candidates = {2, 3, 6, 7};
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> lists = combinationSum(candidates, target);
        System.out.println(lists);
    }

    private void combinationSum(int[] candidates, int target, List<List<Integer>> lists, List<Integer> list, int index, int sum) {
        if (index >= candidates.length || sum > target) {
            if (sum == target) {
                lists.add(new ArrayList<>(list));
            }
            return;
        }
        // 选择当前数
        if (sum < target) {
            list.add(candidates[index]);
            combinationSum(candidates, target, lists, list, index, sum + candidates[index]);
            list.remove(list.size() - 1);
        }
        // 不选当前数
        combinationSum(candidates, target, lists, list, index + 1, sum);
    }

    /**
     * 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * <p>
     * 你可以按 任何顺序 返回答案。
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combine(n, k, 1, lists, list);
        return lists;
    }

    @Test
    public void combine() {
        int n = 4;
        int k = 2;
        System.out.println(combine(n, k));
    }

    private void combine(int n, int k, int num, List<List<Integer>> lists, List<Integer> list) {
        if (num > n + 1) {
            return;
        }
        if (list.size() == k) {
            lists.add(new ArrayList<>(list));
            return;
        }
        // 选择当前数
        list.add(num);
        combine(n, k, num + 1, lists, list);
        list.remove(list.size() - 1);
        combine(n, k, num + 1, lists, list);
    }

    /**
     * 零钱兑换  322
     * 并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        // f(m) = Math.min(f(m- coins[i])+1, f(m-coins[i+1])+1,...)
        int[] dp = new int[amount + 1];
        // 初始化
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount];
    }

    @Test
    public void coinChange1() {
        int[] coins = {2, 5, 7};
        System.out.println(coinChange(coins, 27));
    }

    int min_coin = Integer.MAX_VALUE;

    public int coinChange2(int[] coins, int amount) {
        coinChange2(coins, amount, 0, 0, 0);
        return min_coin == Integer.MAX_VALUE ? -1 : min_coin;
    }

    private void coinChange2(int[] coins, int amount, int index, int sum, int coinNum) {
        if (index >= coins.length || sum > amount) {
            if (sum == amount && min_coin > coinNum) {
                min_coin = coinNum;
            }
            return;
        }
        coinChange2(coins, amount, index, sum + coins[index], coinNum + 1);
        // 不选
        coinChange2(coins, amount, index + 1, sum, coinNum);
    }

    @Test
    public void coinChange2() {
//        int[] coins = {2, 5, 7};
        int[] coins = {1, 2, 5};
        System.out.println(coinChange2(coins, 11));
    }

    public int coinChange3(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        int[] counts = new int[amount];
        int res = coinChange3(coins, amount, counts);
        System.out.println(Arrays.toString(counts));
        return res;
    }

    @Test
    public void coinChange3() {
//        int[] coins = {2, 3, 5};
        int[] coins = {1, 2, 5};
        int amount = 3;
        System.out.println(coinChange3(coins, amount));
    }

    private int coinChange3(int[] coins, int rem, int[] counts) {
        if (rem < 0) {
            // 此方案不通
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (counts[rem - 1] != 0) {
            // 返回记录金额为rem时的最小硬币数, 减少回溯计算
            return counts[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange3(coins, rem - coin, counts);
            if (res >= 0 && res < min) {
                // 说明此方案可行，且硬币少于初始值
                min = res + 1;
            }
        }
        counts[rem - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return counts[rem - 1];
    }


    /**
     * 数塔问题
     *
     * @param lists
     * @return
     */
    public int maxRoute(List<List<Integer>> lists) {
        // dp[i][j]表示 第i层，j列时最大路径
        // 有两种情况，最左列，或最右列dp[i][j] = dp[i-1][0]+list.get(0)
        int[][] dp = new int[lists.size()][lists.get(lists.size() - 1).size()];
        int max = lists.get(0).get(0);
        dp[0][0] = max;
        for (int i = 1; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                int num = lists.get(i).get(j);
                dp[i][j] = Math.max(dp[i - 1][j - 1] + num, dp[i - 1][j] + num);
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * 回溯算法
     *
     * @param lists
     * @return
     */
    public int maxRoute1(List<List<Integer>> lists) {
        return maxRoute1(lists, 0, 0);
//        return max_route;
    }

    private int maxRoute1(List<List<Integer>> lists, int x, int y) {
        if (x == lists.size() - 1) {
            return lists.get(x).get(y);
        }
        return lists.get(x).get(y) + Math.max(maxRoute1(lists, x + 1, y), maxRoute1(lists, x + 1, y + 1));
    }

    int max_route = Integer.MIN_VALUE;

    @Test
    public void maxRoute() {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Lists.newArrayList(7));
        list.add(Lists.newArrayList(3, 8));
        list.add(Lists.newArrayList(8, 1, 0));
        list.add(Lists.newArrayList(2, 7, 4, 4));
        list.add(Lists.newArrayList(4, 5, 2, 6, 5));
        System.out.println(maxRoute2(list));
    }

    public int maxRoute2(List<List<Integer>> lists) {
        // 回溯加枝剪
        int[][] dp = new int[lists.size()][lists.size()];
        return maxRoute2(lists, 0, 0, dp);
    }

    private int maxRoute2(List<List<Integer>> lists, int x, int y, int[][] dp) {
        if (x == lists.size() - 1) {
            return lists.get(x).get(y);
        }
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        // 记录x,y的最大路径
        dp[x][y] = lists.get(x).get(y) + Math.max(maxRoute2(lists, x + 1, y, dp), maxRoute2(lists, x + 1, y + 1, dp));
        return dp[x][y];
    }

    /**
     * 零钱兑换
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * <p>
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/coin-change-2
     *
     * @param amount
     * @param coins
     * @return
     */
    public int coinChangeII(int amount, int[] coins) {
        // 此题与数组总和相似
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
//        coinChangeII(amount, coins, 0, lists, list);
        int[] count = new int[amount];
        int res = coinChangeII(amount, coins, 0, list, count);
//        System.out.println(lists);
        System.out.println(Arrays.toString(count));
        return res;
    }

    @Test
    public void coinChangeII() {
        int[] coins = {1, 2, 5};
//        int[] coins = {3,5,7,8,9,10,11};
        int amount = 5;
        System.out.println(coinChangeII(amount, coins));
    }

    int coinChange = 0;

    private int coinChangeII(int amount, int[] coins, int index, List<Integer> list, int[] count) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (count[amount - 1] != 0) {
            return count[amount - 1];
        }
        /*int n = 0;
        for (int coin : coins) {
            int res = coinChangeII(amount - coin, coins, 0, list, count);
            if (res >= 0 ) {
                if (res == 0) {
                    n++;
                } else {
                    n=res;
                }
            }
        }
        count[amount-1] = n == 0?0:n;
        return count[amount - 1];*/
        list.add(coins[index]);
        // 放index的硬币
        int res = coinChangeII(amount - coins[index], coins, index, list, count);

        list.remove(list.size() - 1);
        // 不放index的硬币
        coinChangeII(amount, coins, index + 1, list, count);
        return res;
    }

    private void coinChangeII(int amount, int[] coins, int index, List<List<Integer>> lists, List<Integer> list) {
        if (amount <= 0 || index >= coins.length) {
            if (amount == 0) {
                coinChange++;
            }
            return;
        }
        list.add(coins[index]);
        // 放index的硬币
        coinChangeII(amount - coins[index], coins, index, lists, list);
        list.remove(list.size() - 1);
        // 不放index的硬币
        coinChangeII(amount, coins, index + 1, lists, list);
    }

    @Test
    public void change() {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println(change(amount, coins));
    }

    public int change(int amount, int[] coins) {
        // dp[i][j] 表示放第i个硬币时，最大金额为j,时，有dp[i][j]种方式
        // dp[i][j] 有两种情况，当coins[i] > j 时，放不了，dp[i-1][j] , 当coins[i] < j 时, 放得了， dp[i-j][j]
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i < coins.length + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (coins[i - 1] <= j) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        for (int i = 0; i <= coins.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[coins.length][amount];
    }

    public int changeII(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    @Test
    public void changeII() {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println(changeII(amount, coins));
    }


    public boolean jumpGame(int[] jump) {
        int dis = jump[0];
        for (int i = 0; i < jump.length; i++) {
            if (dis < i) {
                return false;
            }
            dis = Math.max(dis, jump[i] + i);
        }
        return true;
    }

    int min_jump = Integer.MAX_VALUE;

    public int jumpGame1(int[] jump) {
        return -1;
    }

    /**
     * 完美数
     * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
     * 28 = 1 + 2 + 4 + 7 + 14
     * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
     *
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if (num < 6) {
            return false;
        }
        int sum = 1;
        int index = 2;
        while (index < num) {
            if (num % index == 0) {
                sum += index;
            }
            index++;
        }
        return sum == num;
    }

    @Test
    public void checkPerfectNumber() {
        int num = 28;
        System.out.println(checkPerfectNumberII(2016));
    }

    public boolean checkPerfectNumberII(int num) {
        if (num < 6) {
            return false;
        }
        int sum = 1;
        int index = 2;
        while (index < Math.sqrt(num)) {
            if (num % index == 0) {
                sum += index;
                sum += num / index;
            }
            index++;
        }
        return sum == num;
    }

    public int jumpII(int[] arr) {
        // 从后向前推导 每次取跳跃的最大步数
        int position = arr.length - 1;
        int step = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + arr[i] >= position) {
                    position = i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }

    public int jumpII2(int[] arr) {
        if (arr.length == 1) {
            return 1;
        }
        int step = 0;
        int start = 0;
        int end = arr[0];
        while (end < arr.length) {
            int maxPosition = 0;
            while (start <= end) {
                maxPosition = Math.max(maxPosition, start + arr[start]);
                start++;
            }
            start = end;
            end = maxPosition;
            step++;
        }
        return step;
    }

    public int jumpII3(int[] arr) {
        // 优化
        // 从前往后跳, 取初始点为跳跃的最大距离, 遍历数组，更新最大距离, 当最大距离>= arr.length-1时，得到最小步数
        int maxPosition = 0;
        // end表示更新增加步数
        int end = 0;
        int step = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + arr[i]);
            if (i == end) {
                end = maxPosition;
                step++;
            }
        }
        return step;
    }

    /**
     * 数青蛙
     * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。
     * 由于同一时间可以有多只青蛙呱呱作响，所以croakOfFrogs 中会混合多个 “croak” 。
     * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
     * <p>
     * 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，
     * 那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
     *
     * @param croakOfFrogs
     * @return
     */
    public int minNumberOfFrogs(String croakOfFrogs) {
        // 当遇到c字符时，表示有一只青蛙， 当遇到k时，表示有一只青蛙已经叫完，
        // 保证发声合法，则c >= r && r>=o o >=a a>=k
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        // 青蛙的数量
        int frog = 0;
        // 结果
        int res = 0;
        char[] chs = croakOfFrogs.toCharArray();
        for (char ch : chs) {
            if (ch == 'c') {
                c++;
                frog++; // 需要有一只青蛙发声
            } else if (ch == 'r') {
                r++;
            } else if (ch == 'o') {
                o++;
            } else if (ch == 'a') {
                a++;
            } else if (ch == 'k') {
                k++;
                frog--; // 表示一只青蛙已经叫完，可以进行下一轮
            }
            // 统计结果
            res = Math.max(res, frog);
            if (c >= r && r >= o && o >= a && a >= k) {
                continue;
            } else {
                return -1;
            }
        }
        if (c != r || r != o || o != a || a != k) {
            return -1;
        }
        return res;
    }

    @Test
    public void minNumberOfFrogsII() {
        String frogs = "croakcroak";
        System.out.println(minNumberOfFrogsII(frogs));
    }

    /**
     * 数青蛙
     *
     * @param croakOfFrogs
     * @return
     */
    public int minNumberOfFrogsII(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int res = 0;
        char[] chars = croakOfFrogs.toCharArray();
        for (char ch : chars) {
            if (ch == 'c') {
                if (k > 0) {
                    k--;
                } else {
                    res++;
                }
                c++;
            } else if (ch == 'r') {
                r++;
                c--;
            } else if (ch == 'o') {
                o++;
                r--;
            } else if (ch == 'a') {
                a++;
                o--;
            } else if (ch == 'k') {
                k++;
                a--;
            }

            if (c < 0 || r < 0 || o < 0 || a < 0 || k < 0) {
                return -1;
            }
        }
        if (c != 0 || r != 0 || o != 0 || a != 0) {
            return -1;
        }
        return res;
    }

    @Test
    public void sortList() {
        List<Integer> list = Lists.newArrayList(23, 12, 52, 315, 223);
        list.stream().sorted(Integer::compareTo).min(Integer::min);
        System.out.println(list);
    }

    @Test
    public void topK() {
        int[] arr = {324, 35, 234, 535, 3421, 53, 234, 234, 53, 34, 14};
        int k = 5;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.comparingInt(o -> o));
        for (int i = 0; i < arr.length; i++) {
            if (pq.size() < k) {
                pq.offer(arr[i]);
            } else {
                int peek = pq.peek();
                if (arr[i] > peek) {
                    pq.poll();
                    pq.offer(arr[i]);
                }
            }
        }
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

    /**
     * 11. 盛最多水的容器
     * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
     * <p>
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     * <p>
     * 返回容器可以储存的最大水量。
     * <p>
     * 说明：你不能倾斜容器。
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        // [1,8,6,2,5,4,8,3,7]  => 8,7 * 7  42
        // 暴力
        int max = Integer.MIN_VALUE;
        for (int i = 0, len = height.length; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int h = height[j] > height[i] ? height[i] : height[j];
                int l = j - i;
                max = Math.max(max, h * l);
            }
        }
        return max;
    }

    @Test
    public void maxArea() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }
}

