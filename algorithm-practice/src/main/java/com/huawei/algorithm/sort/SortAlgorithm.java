package com.huawei.algorithm.sort;

import com.google.common.collect.Lists;
import com.huawei.algorithm.Node;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
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
        int[] stones = {1,4,1,8,1};
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
        int[] res = new int[target+1];
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
        int[] arr = {1,1,1,1,1};
        targetAdd(arr,0,0);
        System.out.println(count1);
    }

    public void targetAdd(int[] arr,int i,int res) {
        if ( i == arr.length) {
            if (res == target) {
                count1++;
            }
            return;
        }
        // 当前数前为+号
        targetAdd(arr,i+1,res+arr[i]);
        targetAdd(arr,i+1,res-arr[i]);
    }

    /**
     * 目标和
     * @param arr
     * @param target
     * @return
     */
    public int targetAdd1(int[] arr,int target) {

        return 0;
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
