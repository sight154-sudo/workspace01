package com.huawei.algorithm.leetcode.greedyPractice;

import javafx.scene.layout.Priority;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 贪心算法
 *
 * @author king
 * @date 2023/7/12-22:17
 * @Desc
 */
public class GreedyPractice {

    /**
     * 给定一个字符串数组， 进行重新排序后生成一个字典序最小的字符串
     * 贪心策略: 两个字符串组合时，求最小的字典序后再与下一个字符串组合为最小的字典序，最终结果为最小的字典序
     *
     * @param str
     * @return
     */
    public String getMinDict(String[] str) {
        if (str == null) {
            return "";
        }
        String[] tmp = Arrays.copyOf(str, str.length);
        Arrays.sort(tmp, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder sb = new StringBuilder();
        for (String s : tmp) {
            sb.append(s);
        }
        return sb.toString();
    }


    public String getMinDict1(String[] str) {
        // 暴力解法
        TreeSet<String> set = new TreeSet<>();
        getTreeSet(str, new ArrayDeque<>(), set);
        return set.first();
    }

    public void getTreeSet(String[] str, Deque<String> deque, TreeSet<String> ans) {
        if (str.length == deque.size()) {
            String s = concatStr(deque);
            ans.add(s);
        }
        for (String s : str) {
            if (!deque.contains(s)) {
                deque.addLast(s);
                getTreeSet(str, deque, ans);
                deque.removeLast();
            }
        }
    }

    private String concatStr(Deque<String> deque) {
        StringBuilder sb = new StringBuilder();
        for (String s : deque) {
            sb.append(s);
        }
        return sb.toString();
    }

    public String getMinDict2(String[] str) {
        List<String> list = new ArrayList<>(Arrays.asList(str));
        List<String> ans = new ArrayList<>();
        int start = 0;
        getMaxDictByBatchTrace(ans, list, start);
        String s = ans.get(0);
        for (String an : ans) {
            if (an.compareTo(s) < 0) {
                s = an;
            }
        }
        return s;
    }

    @Test
    public void getMinDictTest() {
        String[] str = {"b", "ba", "ac"};
        System.out.println(getMinDict(str));
        System.out.println(getMinDict1(str));
        validMaxDict(10, 10, 10);
    }

    public void validMaxDict(int size, int len, int count) {
        boolean flag = false;
        for (int i = 0; i < count; i++) {
            String[] s1 = new String[size];
            String[] s2 = new String[size];
            for (int j = 0; j < size; j++) {
                String s = randomString(len);
                s1[i] = s;
                s2[i] = s;
            }
            String[] s = Arrays.copyOf(s1, size);
            if (!getMinDict(s1).equals(getMinDict2(s2))) {
                flag = true;
                System.out.println(Arrays.toString(s));
                System.out.println("Fk code!!!!");
            }
        }
        if (!flag) {
            System.out.println("Nice Code!!");
        }
    }

    private String randomString(int len) {
        int m = (int) (Math.random() * len) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            char c = Math.random() > 0.5 ? (char) (int) ((Math.random() * 26) + 65) : (char) (int) ((Math.random() * 26) + 97);
            sb.append(c);
        }
        return sb.toString();
    }


    public void getMaxDictByBatchTrace(List<String> ans, List<String> list, int start) {
        if (start == list.size() - 1) {
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s);
            }
            ans.add(sb.toString());
            return;
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            getMaxDictByBatchTrace(ans, list, start + 1);
            Collections.swap(list, start, i);
        }
    }

    /**
     * 给定一个二维数组，每个数组中有两个元素，代表会议的开始时间与结束时间，让你安排这些会议的进程，
     * 会议的开始时间不能冲突，求安排最多的会议数量
     *
     * @param arr
     * @return
     */
    public int getMaxMeeting(int[][] arr) {
        // 贪心策略: 根据每个会议的结束时间进行排序后，选择会议，求出最大值
        int[][] tmp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(tmp, Comparator.comparingInt(a -> a[1]));
        int max = 0;
        int beginTime = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (beginTime <= tmp[i][0]) {
                max++;
                beginTime = tmp[i][1];
            }
        }
        return max;
    }

    @Test
    public void getMaxMeetingTest() {
        int[][] arr = {{1, 3}, {2, 3}, {3, 5}, {4, 6}, {5, 8}, {7, 9}};
        System.out.println(getMaxMeeting(arr));
        System.out.println(getMaxMeeting1(arr));
        validMaxMeeting(60, 10000, 10);
    }

    public void validMaxMeeting(int size, int maxValue, int count) {
        boolean flag = false;
        for (int i = 0; i < count; i++) {
            int[][] a1 = new int[size][2];
            int[][] a2 = new int[size][2];
            for (int j = 0; j < size; j++) {
                int m = (int) (Math.random() * maxValue) + 1;
                int n = (int) (Math.random() * maxValue) + 1;
                while (n < m) {
                    n = (int) (Math.random() * maxValue) + 1;
                }
                a1[j][0] = m;
                a1[j][1] = n;
                a2[j][0] = m;
                a2[j][1] = n;
            }
            int[][] a = Arrays.copyOf(a1, size);
            if (getMaxMeeting(a1) != getMaxMeeting1(a2)) {
                flag = true;
                System.out.println(Arrays.toString(a));
                System.out.println("Fk code");
            }
        }
        if (!flag) {
            System.out.println("Nice Code!!!!");
        }
    }

    public int getMaxMeeting1(int[][] arr) {
        return getMaxMeetingBackTrace(arr, 0, 0);
    }

    public int getMaxMeetingBackTrace(int[][] arr, int done, int beginTime) {
        if (arr.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] >= beginTime) {
                int[][] tmp = removeCurArr(arr, i);
                max = Math.max(max, getMaxMeetingBackTrace(tmp, done + 1, arr[i][1]));
            }
        }
        return max;
    }

    private int[][] removeCurArr(int[][] arr, int index) {
        int[][] tmp = new int[arr.length - 1][2];
        int n = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                tmp[n++] = arr[i];
            }
        }
        return tmp;
    }

    public int getMinSplitConsume(int[] arr) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i : arr) {
            priorityQueue.offer(i);
        }
        int min = 0;
        while (priorityQueue.size() > 1) {
            int t = priorityQueue.poll() + priorityQueue.poll();
            min += t;
            priorityQueue.offer(t);
        }
        return min;
    }

    @Test
    public void getMinSplitConsumeTest() {
        int[] arr = {4, 2, 3, 7, 9};
        System.out.println(getMinSplitConsume(arr));
        System.out.println(getMinSplitConsume1(arr));
        validMinSplit(10, 20, 8);
    }

    public void validMinSplit(int size, int maxValue, int count) {
        boolean flag = false;
        for (int i = 0; i < count; i++) {
            int[] a1 = new int[size];
            int[] a2 = new int[size];
            for (int j = 0; j < size; j++) {
                int n = (int) (Math.random() * (maxValue + 1)) + 1;
                a1[j] = n;
                a2[j] = n;
            }
            int[] a = Arrays.copyOf(a1, a1.length);
            if (getMinSplitConsume(a1) != getMinSplitConsume1(a2)) {
                System.out.println(Arrays.toString(a));
                System.out.println("Fk Code");
            }
        }
        if (!flag) {
            System.out.println("Nice Code!!!");
        }
    }


    public int getMinSplitConsume1(int[] arr) {
        return getMinSplitBackTrace(arr, 0);
    }

    private int getMinSplitBackTrace(int[] arr, int does) {
        if (arr.length == 1) {
            return does;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int[] tmp = mergeArr(arr, i, j);
                min = Math.min(min, getMinSplitBackTrace(tmp, does + arr[i] + arr[j]));
            }
        }
        return min;
    }

    public int[] mergeArr(int[] arr, int i, int j) {
        int[] tmp = new int[arr.length - 1];
        int n = 0;
        int index = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k == i || k == j) {
                n += arr[k];
            } else {
                tmp[index++] = arr[k];
            }
        }
        tmp[index] = n;
        return tmp;
    }

    /**
     * 二维数组中存入每个项目的花费与利润， 现在你有初始资金m, 可以进行k个项目的运行（串行） 求选择k个项目运行后的最大金额
     *
     * @param arr
     * @return
     */
    public int getMaxProfit(int[][] arr, int k, int m) {
        // 如{{5,1},{1,3}, {2,3}, {3,4}, {6,5}} k = 3  ->  选择{1,3},{3,4},{6,5}  => 13
        // 使用小顶堆存放项目的花费， 使用大顶堆存在每个项目的利润 每次从大顶堆中取最大的利润
        PriorityQueue<int[]> minPriority = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> maxPriority = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int[] ints : arr) {
            minPriority.add(ints);
        }
        int ans = m;
        for (int i = 0; i < k; i++) {
            while (!minPriority.isEmpty() && minPriority.peek()[0] <= ans) {
                maxPriority.add(minPriority.poll());
            }
            if (maxPriority.isEmpty()) {
                return ans;
            }
            ans += maxPriority.poll()[1];
        }
        return ans;
    }

    @Test
    public void getMaxProfitTest() {
        int[][] arr = {{5, 1}, {1, 4}, {2, 3}, {3, 4}, {6, 5}};
        System.out.println(getMaxProfit(arr, 3, 2));
        System.out.println(getMaxProfit1(arr, 3, 2));
        validGetMaxProfit(11, 20, 100);
    }

    public void validGetMaxProfit(int size, int maxValue, int count) {
        boolean flag = true;
        for (int i = 0; i < count; i++) {
            int[][] arr1 = new int[size][2];
            int[][] arr2 = new int[size][2];
            for (int j = 0; j < size; j++) {
                int a = (int) (Math.random() * maxValue + 1);
                int b = (int) (Math.random() * maxValue + 1);
                while (a > b) {
                    b = (int) (Math.random() * maxValue + 1);
                }
                arr1[j][0] = a;
                arr1[j][1] = b;
                arr2[j][0] = a;
                arr2[j][1] = b;
            }
            int k = (int) (Math.random() * size + 1);
            int m = (int) (Math.random() * maxValue + 1);
            int[][] arr = Arrays.copyOf(arr1, arr1.length);
            if (getMaxProfit(arr1, k, m) != getMaxProfit1(arr2, k, m)) {
                flag = false;
                System.out.println(Arrays.toString(arr));
                System.out.println("Fk Code!!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code!!!");
        }
    }

    public int getMaxProfit1(int[][] arr, int k, int m) {
        return getMaxProfitBackTrace(arr, k, m);
    }

    public int getMaxProfitBackTrace(int[][] arr, int k, int m) {
        if (k == 0) {
            return m;
        }
        int max = m;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] <= m) {
                int[][] tmp = removeCurArr(arr, i);
                max = Math.max(max, getMaxProfitBackTrace(tmp, k - 1, m + arr[i][1]));
            }
        }
        return max;
    }

    /**
     * 给定一个字符串，字符串中只能'X'与'.',  X表示为墙，不能放灯，  .的位置表示可以放灯，但每个灯的照亮位置为i-1至i+1
     * 求最小放置多小盏灯可以将.的位置都照亮
     *
     * @param s
     * @return
     */
    public int getMinLight(String s) {
        char[] chs = s.toCharArray();
        int index = 0;
        int ans = 0;
        // 当index为X表示不能放灯，直接到i+1的位置
        // 当index为.时，如果i+1为X，直接在index位置放灯，且index为index+2;
        // 当index为.时， 如果 i+1为. 判断i+2的位置，如果为X, 则ans+1, 且index = index+3
        //                                         如果为., 则ans+1, 且index = index+3
        while (index < chs.length) {
            if (chs[index] == 'X') {
                index++;
            } else {
                ans++;
                if (index+1 < chs.length && chs[index+1] == 'X') {
                    index = index+2;
                } else {
                    index = index+3;
                }
            }
        }
        return ans;
    }

    @Test
    public void getMinLightTest() {
        String s = "X.X.X..X..X...X.";
        System.out.println(getMinLight(s));
    }

}
