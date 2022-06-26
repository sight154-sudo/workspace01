package com.huawei.algorithm.sort;

import com.huawei.algorithm.Node;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

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
        int[] nums = {2,4,3,1,5,6};
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
        int[] tmp = new int[j - i + 1];
        int index = 0;
        for (; i < mid + 1; i++) {
            if (nums[l_start] < nums[r_start]) {
                tmp[index++] = nums[l_start++];
            } else {
                // 左边数大于右边  为逆序对
                count += (mid-i+1);
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
            nums[base+k] = tmp[k];
        }
    }

}
