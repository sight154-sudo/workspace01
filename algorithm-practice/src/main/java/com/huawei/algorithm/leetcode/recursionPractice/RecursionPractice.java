package com.huawei.algorithm.leetcode.recursionPractice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * @author king
 * @date 2023/4/13-23:55
 * @Desc
 */
public class RecursionPractice {

    /**
     * 汉诺塔问题
     *
     * @param n
     */
    public void hanno1(int n) {
        left2Right(n);
    }

    public void left2Right(int n) {
        if (n == 1) {
            System.out.println("The 1 from left to right");
            return;
        }
        left2Mid(n - 1);
        System.out.printf("The %s from left to right\n", n);
        mid2Right(n - 1);
    }

    private void mid2Right(int n) {
        if (n == 1) {
            System.out.println("The 1 from mid to right");
            return;
        }
        mid2left(n - 1);
        System.out.printf("The %s from mid to right\n", n);
        left2Right(n - 1);
    }

    private void left2Mid(int n) {
        if (n == 1) {
            System.out.println("The 1 from left to mid");
            return;
        }
        left2Right(n - 1);
        System.out.printf("The %s from left to mid\n", n);
        right2mid(n - 1);
    }

    private void right2mid(int n) {
        if (n == 1) {
            System.out.println("The 1 from right to mid");
            return;
        }
        right2left(n - 1);
        System.out.printf("The %s from right to mid\n", n);
        left2Mid(n - 1);
    }

    private void mid2left(int n) {
        if (n == 1) {
            System.out.println("The 1 from mid to left");
            return;
        }
        mid2Right(n - 1);
        System.out.printf("The %s from mid to left\n", n);
        right2left(n - 1);
    }

    private void right2left(int n) {
        if (n == 1) {
            System.out.println("The 1 from right to left");
            return;
        }
        right2mid(n - 1);
        System.out.printf("The %s from right to left", n);
        mid2left(n - 1);
    }

    public void fn(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.printf("Move 1 from %s to %s\n", from, to);
            return;
        }
        fn(n - 1, from, other, to);
        System.out.printf("Move %s from %s to %s\n", n, from, to);
        fn(n - 1, other, to, from);
    }

    public void hanno2(int n) {
        fn(n, "left", "right", "mid");
    }

    @Test
    public void hannoTest() {
//        hanno1(3);
        hanno2(3);
    }

    public List<String> getSubStr(String str) {
        List<String> list = new ArrayList<>();
        char[] chs = str.toCharArray();
        getSubStrRecursion(chs, list, 0, "");
        return list;
    }

    @Test
    public void getSubStrTest() {
        List<String> list = getSubStr("abc");
        System.out.println(list);
    }

    private void getSubStrRecursion(char[] chs, List<String> list, int idx, String str) {
        if (idx == chs.length) {
            list.add(str);
            return;
        }
        getSubStrRecursion(chs, list, idx + 1, str);
        getSubStrRecursion(chs, list, idx + 1, str + chs[idx]);
    }

    @Test
    public void reverseStackTest() {
        Stack<Object> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        System.out.println(stack);
        reverseStack(stack);
        System.out.println(stack);
    }

    public Stack reverseStack(Stack stack) {
        if (stack.isEmpty()) {
            return stack;
        }
        int r = fn(stack);
        stack.push(r);
        return stack;
    }

    public int fn(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int last = fn(stack);
        stack.push(result);
        return last;
    }

    /**
     * 机器人走到目标数 有多少种走法
     *
     * @param n      有n个方块
     * @param start  机器人的开始位置
     * @param target 机器人走到的目标位置
     * @param k      机器人可以行走的步数
     * @return
     */
    public int robotWalk(int n, int start, int target, int k) {
        int ans = robotWalkBackTracing(n, start, target, k);
        return ans;
    }

    @Test
    public void robotWalkTest() {
        System.out.println(robotWalk(4, 2, 4, 4));
        System.out.println(robotWalk1(4, 2, 4, 4));
        System.out.println(robotWalk2(4, 2, 4, 4));
    }

    public int robotWalkBackTracing(int n, int start, int target, int k) {
        if (k == 0) {
            return start == target ? 1 : 0;
        }
        int sum = 0;
        if (start == 1) {
            sum += robotWalkBackTracing(n, start + 1, target, k - 1);
        } else if (start == n) {
            sum += robotWalkBackTracing(n, start - 1, target, k - 1);
        } else {
            sum += robotWalkBackTracing(n, start + 1, target, k - 1);
            sum += robotWalkBackTracing(n, start - 1, target, k - 1);
        }
        return sum;
    }

    /**
     * 加缓存，减少计算量
     *
     * @param n
     * @param start
     * @param target
     * @param k
     * @return
     */
    public int robotWalk1(int n, int start, int target, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        int ans = robotWalkBackTracing1(n, start, target, k, dp);
        return ans;
    }

    public int robotWalk2(int n, int start, int target, int k) {
        int[][] dp = new int[n + 1][k + 1];
        dp[target][0] = 1;
        for (int i = 1; i <= k; i++) {
            dp[1][i] = dp[2][i - 1];
            for (int j = 2; j < n; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
            }
            dp[n][i] = dp[n - 1][i - 1];
        }
        return dp[start][k];
    }

    public int robotWalkBackTracing1(int n, int start, int target, int k, int[][] dp) {
        if (dp[start][k] != -1) {
            return dp[start][k];
        }
        int sum;
        if (k == 0) {
            sum = start == target ? 1 : 0;
        } else if (start == 1) {
            sum = robotWalkBackTracing1(n, start + 1, target, k - 1, dp);
        } else if (start == n) {
            sum = robotWalkBackTracing1(n, start - 1, target, k - 1, dp);
        } else {
            sum = robotWalkBackTracing1(n, start + 1, target, k - 1, dp)
                    + robotWalkBackTracing1(n, start - 1, target, k - 1, dp);
        }
        dp[start][k] = sum;
        return sum;
    }

    /**
     * 给定一个整型数组，代表数值有不同的纸牌排成一条线，玩家A和玩家B依次拿走牌，规定玩家A先拿牌，玩家B后拿，但每个玩家只能拿走最左或
     * 最右的牌，玩家A和玩家B都很聪明， 求最后获胜者的分数。
     *
     * @return
     */
    public int win1(int arr[]) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int first = f(arr, 0, arr.length - 1);
        int second = g(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    /**
     * 先手挑选最大的数，并且给最小的给后手
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g(arr, L + 1, R);
        int p2 = arr[R] + g(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    /**
     * 后手尽可能挑选最好的
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public int g(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f(arr, L + 1, R);
        int p2 = f(arr, L, R - 1);
        return Math.min(p1, p2);
    }

    @Test
    public void win1Test() {
        int[] arr = {123, 424, 5352, 12, 13, 31, 312, 23};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }

    public int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int n = arr.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f1(arr, 0, arr.length - 1, fmap, gmap);
        int second = g1(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(first, second);
    }

    private int g1(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans;
        if (L == R) {
            ans = 0;
        } else {
            int p1 = f(arr, L + 1, R);
            int p2 = f(arr, L, R - 1);
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }

    private int f1(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g1(arr, L + 1, R, fmap, gmap);
            int p2 = arr[R] + g1(arr, L, R - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        fmap[L][R] = ans;
        return ans;
    }

    public int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int n = arr.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            fmap[i][i] = arr[i];
        }
        for (int startCol = 1; startCol < n; startCol++) {
            int row = 0;
            int col = startCol;
            while (col < n) {
                fmap[row][col] = Math.max(arr[row] + gmap[row + 1][col], arr[col] + gmap[row][col - 1]);
                gmap[row][col] = Math.min(fmap[row + 1][col], fmap[row][col - 1]);

                /*int p1 = arr[L] + g1(arr, L+1, R, fmap, gmap);
                int p2 = arr[R] + g1(arr, L, R-1, fmap, gmap);*/
                row++;
                col++;
            }
        }
        return Math.max(fmap[0][n - 1], gmap[0][n - 1]);
    }

    @Test
    public void nQuenesTest() {
        List<List<String>> list = nQuenes(4);
        System.out.println(list);
    }

    public List<List<String>> nQuenes(int n) {
        int[] result = new int[n];
        List<List<String>> list = new ArrayList<>();
        nQuenesProcess(list, result, 0, n);
        return list;
    }


    public void nQuenesProcess(List<List<String>> list, int[] result, int row, int col) {
        if (row == col) {
            List<String> ans = getList(result);
            list.add(ans);
            return;
        }
        for (int i = 0; i < col; i++) {
            if (isOk(row, i, col, result)) {
                result[row] = i;
                nQuenesProcess(list, result, row + 1, col);
            }
        }
    }

    public boolean isOk(int r, int col, int n, int[] result) {
        int left = col - 1, right = col + 1;
        for (int i = r - 1; i >= 0; i--) {
            if (result[i] == col) return false;
            if (left >= 0 && result[i] == left) return false;
            if (right < n && result[i] == right) return false;
            left--;
            right++;
        }
        return true;
    }

    public List<String> getList(int[] result) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (result[i] == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            list.add(sb.toString());
            sb.setLength(0);
        }
        return list;
    }

    /**
     * 求两个字符串的莱文斯坦距离 编辑距离指的就是，将一个字符串转化成另一个字符串，需要的最少编辑操作次数
     * 莱文斯坦距离允许增加、删除、替换字符这三个编辑操作，最长公共子串长度只允许增加、删除字符这两个编辑操作。
     *
     * @param s1
     * @param s2
     * @return
     */
    public int ldDis(String s1, String s2) {
        return ldDisBackTrack(s1.toCharArray(), s2.toCharArray(), 0, 0);
    }

    @Test
    public void ldDisTest() {
        String s1 = "mit";
        String s2 = "aba";
        System.out.println(ldDis(s1, s2));
    }

    public int ldDisBackTrack(char[] c1, char[] c2, int l1, int l2) {
        if (l1 == c1.length || l2 == c2.length) {
            if (l1 < c1.length) {
                return c1.length - l1;
            }
            if (l2 < c2.length) {
                return c2.length - l2;
            }
            return 0;
        }
        if (c1[l1] == c2[l2]) {
            return ldDisBackTrack(c1, c2, l1 + 1, l2 + 1);
        } else {
            // 替换
            int p1 = ldDisBackTrack(c1, c2, l1 + 1, l2 + 1) + 1;
            // 删除c1中l1位置的字符 也相当于c2中l2位置增加一个字符
            int p2 = ldDisBackTrack(c1, c2, l1 + 1, l2) + 1;
            // 删除c2中l2位置的字符，也相当于c1中l1位置增加一个字符
            int p3 = ldDisBackTrack(c1, c2, l1, l2 + 1) + 1;
            return Math.min(p1, Math.min(p2, p3));
        }
    }

    /**
     * 求两个字符串的最长公共子串 长公共子串长度只允许增加、删除字符这两个编辑操作
     *
     * @param s1
     * @param s2
     * @return
     */
    public int lastCommonSubSequence(String s1, String s2) {
        return lastCommonSubSequenceBackTrack(s1.toCharArray(), s2.toCharArray(), 0, 0);
    }

    @Test
    public void lastCommonSubSequenceTest() {
        System.out.println(lastCommonSubSequence("bsbi", "bk"));
        System.out.println(lastCommonSubSequence2("bsbi", "bk"));
        validLastCommonSubSequence(100, 10);
    }

    private int lastCommonSubSequenceBackTrack(char[] c1, char[] c2, int l1, int l2) {
        if (l1 == c1.length || l2 == c2.length) {
            return 0;
        }
        if (c1[l1] == c2[l2]) {
            return lastCommonSubSequenceBackTrack(c1, c2, l1 + 1, l2 + 1) + 1;
        } else {
            int p1 = lastCommonSubSequenceBackTrack(c1, c2, l1 + 1, l2);
            int p2 = lastCommonSubSequenceBackTrack(c1, c2, l1, l2 + 1);
            return Math.max(p1, p2);
        }
    }

    public int lastCommonSubSequence2(String text1, String text2) {
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        int n1 = c1.length;
        int n2 = c2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                if (c1[i] == c2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    public void validLastCommonSubSequence(int n, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            String s1 = "";
            String s2 = "";
            int n1 = (int) (Math.random() * n) + 1;
            for (int i = 0; i < n1; i++) {
                char c = (char) ((int) (Math.random() * (26)) + 97);
                s1 += c;
            }
            int n2 = (int) (Math.random() * n) + 1;
            for (int i = 0; i < n2; i++) {
                char c = (char) ((int) (Math.random() * (26)) + 97);
                s2 += c;
            }
            if (lastCommonSubSequence1(s1, s2) != lastCommonSubSequence2(s1, s2)) {
                flag = false;
                System.out.println(s1);
                System.out.println(s2);
                System.out.println("Fuck Code!!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code!!!");
        }
    }


    public int lastCommonSubSequence1(String test1, String test2) {
        char[] c1 = test1.toCharArray();
        char[] c2 = test2.toCharArray();
        int[][] dp = new int[c1.length][c2.length];
        for (int i = 0; i < c1.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return lastCommonSubSequenceBackTrack1(c1, c2, 0, 0, dp);
    }

    private int lastCommonSubSequenceBackTrack1(char[] c1, char[] c2, int l1, int l2, int[][] dp) {
        if (l1 == c1.length || l2 == c2.length) {
            return 0;
        }
        if (dp[l1][l2] != -1) {
            return dp[l1][l2];
        }
        int max;
        if (c1[l1] == c2[l2]) {
            max = lastCommonSubSequenceBackTrack1(c1, c2, l1 + 1, l2 + 1, dp) + 1;
        } else {
            int p1 = lastCommonSubSequenceBackTrack1(c1, c2, l1 + 1, l2, dp);
            int p2 = lastCommonSubSequenceBackTrack1(c1, c2, l1, l2 + 1, dp);
            max = Math.max(p1, p2);
        }
        dp[l1][l2] = max;
        return max;
    }

    /**
     * 求字符串中最长回文子序列的长度
     *
     * @param s
     * @return
     */
    public int lastPalindromeSubSequence(String s) {
        // 方法一， 求字符串的正序与逆序的最长公共子序列
        // 方法二。求字符串是L..R的最长回文子序列
        if (s == null || s.length() == 0) {
            return 0;
        }
        return lastPalindromeSubSequenceBackTrack(s.toCharArray(), 0, s.length() - 1);
    }

    @Test
    public void lastPalindromeSubSequenceTest() {
        System.out.println(lastPalindromeSubSequence2("bbbab"));
//        validlongestPalinromeSubSequence(19, 100);
    }

    private int lastPalindromeSubSequenceBackTrack(char[] c1, int L, int R) {
        if (L == R) {
            // 只有一个字符
            return 1;
        }
        if (L == R - 1) {
            return c1[L] == c1[R] ? 2 : 1;
        }
        int p1 = lastPalindromeSubSequenceBackTrack(c1, L + 1, R - 1);
        int p2 = lastPalindromeSubSequenceBackTrack(c1, L + 1, R);
        int p3 = lastPalindromeSubSequenceBackTrack(c1, L, R - 1);
        int p4 = c1[L] == c1[R] ? lastPalindromeSubSequenceBackTrack(c1, L + 1, R - 1) + 2 : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public int lastPalindromeSubSequence1(String s) {
        // 方法一， 求字符串的正序与逆序的最长公共子序列
        // 方法二。求字符串是L..R的最长回文子序列
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return lastPalindromeSubSequenceBackTrack1(s.toCharArray(), 0, s.length() - 1, dp);
    }

    public void validlongestPalinromeSubSequence(int n, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            String s1 = "";
            int n1 = (int) (Math.random() * n) + 1;
            for (int i = 0; i < n1; i++) {
                char c = (char) ((int) (Math.random() * (26)) + 97);
                s1 += c;

            }
            if (lastPalindromeSubSequence2(s1) != lastPalindromeSubSequence1(s1)) {
                flag = false;
                System.out.println(s1);
                System.out.println("Fuck Code!!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code!!!");
        }
    }

    private int lastPalindromeSubSequenceBackTrack1(char[] c1, int L, int R, int[][] dp) {
        if (L == R) {
            // 只有一个字符
            return 1;
        }
        if (L == R - 1) {
            return c1[L] == c1[R] ? 2 : 1;
        }
        if (dp[L][R] != -1) {
            return dp[L][R];
        }
        int p1 = lastPalindromeSubSequenceBackTrack1(c1, L + 1, R - 1, dp);
        int p2 = lastPalindromeSubSequenceBackTrack1(c1, L + 1, R, dp);
        int p3 = lastPalindromeSubSequenceBackTrack1(c1, L, R - 1, dp);
        int p4 = c1[L] == c1[R] ? lastPalindromeSubSequenceBackTrack1(c1, L + 1, R - 1, dp) + 2 : 0;
        int max = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        dp[L][R] = max;
        return max;
    }


    public int lastPalindromeSubSequence2(String s) {
        // 方法一， 求字符串的正序与逆序的最长公共子序列
        // 方法二。求字符串是L..R的最长回文子序列
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] c1 = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int L = n - 2; L >= 0; L--) {
            for (int R = L + 1; R < n; R++) {
                int p1 = dp[L + 1][R - 1];
                int p2 = dp[L + 1][R];
                int p3 = dp[L][R - 1];
                int p4 = c1[L] == c1[R] ? dp[L + 1][R - 1] + 2 : 0;
                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][n - 1];
    }

    /**
     * 有一个9*10的棋盘， 马从（0，0）位置出发， 只可以‘走日’， 求马走k步后，到达(x,y)位置的所有可能
     *
     * @param x
     * @param y
     * @param k
     * @return
     */
    public int chessWay(int x, int y, int k) {
        return chessWayBackTrack(0, 0, x, y, k);
    }

    @Test
    public void chessWayTest() {
        System.out.println(chessWay(1, 2, 3));
        System.out.println(chessWay1(1, 2, 3));
        System.out.println(chessWay2(1, 2, 3));
    }


    public int chessWayBackTrack(int l, int r, int x, int y, int k) {
        if (l > 9 || r > 10 || l < 0 || r < 0) {
            return 0;
        }
        if (k == 0) {
            if (l == x && r == y) {
                return 1;
            }
            return 0;
        }
        int p1 = chessWayBackTrack(l + 1, r + 2, x, y, k - 1);
        int p2 = chessWayBackTrack(l + 2, r + 1, x, y, k - 1);
        int p3 = chessWayBackTrack(l - 2, r - 1, x, y, k - 1);
        int p4 = chessWayBackTrack(l - 1, r - 2, x, y, k - 1);
        int p5 = chessWayBackTrack(l + 2, r - 1, x, y, k - 1);
        int p6 = chessWayBackTrack(l + 1, r - 2, x, y, k - 1);
        int p7 = chessWayBackTrack(l - 1, r + 2, x, y, k - 1);
        int p8 = chessWayBackTrack(l - 2, r + 1, x, y, k - 1);
        return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
    }

    public int chessWay1(int x, int y, int k) {
        int[][][] dp = new int[10][11][k + 1];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                for (int l = 0; l <= k; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }
        return chessWayBackTrack1(0, 0, x, y, k, dp);
    }

    public int chessWayBackTrack1(int l, int r, int x, int y, int k, int[][][] dp) {
        if (l > 9 || r > 10 || l < 0 || r < 0) {
            return 0;
        }
        if (k == 0) {
            if (l == x && r == y) {
                return 1;
            }
            return 0;
        }
        if (dp[l][r][k] != -1) {
            return dp[l][r][k];
        }
        int p1 = chessWayBackTrack(l + 1, r + 2, x, y, k - 1);
        p1 += chessWayBackTrack(l + 2, r + 1, x, y, k - 1);
        p1 += chessWayBackTrack(l - 2, r - 1, x, y, k - 1);
        p1 += chessWayBackTrack(l - 1, r - 2, x, y, k - 1);
        p1 += chessWayBackTrack(l + 2, r - 1, x, y, k - 1);
        p1 += chessWayBackTrack(l + 1, r - 2, x, y, k - 1);
        p1 += chessWayBackTrack(l - 1, r + 2, x, y, k - 1);
        p1 += chessWayBackTrack(l - 2, r + 1, x, y, k - 1);
        dp[l][r][k] = p1;
        return p1;
    }

    public int chessWay2(int x, int y, int k) {
        int[][][] dp = new int[10][11][k + 1];
        dp[x][y][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < 10; j++) {
                for (int l = 0; l < 11; l++) {
                    int ways = getVal(dp, j + 1, l + 2, i - 1);
                    ways += getVal(dp, j + 2, l + 1, i - 1);
                    ways += getVal(dp, j - 2, l - 1, i - 1);
                    ways += getVal(dp, j - 1, l - 2, i - 1);
                    ways += getVal(dp, j + 2, l - 1, i - 1);
                    ways += getVal(dp, j + 1, l - 2, i - 1);
                    ways += getVal(dp, j - 1, l + 2, i - 1);
                    ways += getVal(dp, j - 2, l + 1, i - 1);
                    dp[j][l][i] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    private int getVal(int[][][] dp, int x, int y, int k) {
        if (x > 9 || y > 10 || x < 0 || y < 0) {
            return 0;
        }
        return dp[x][y][k];
    }

    /**
     * 给定一个数组arr, arr[i]代表第i号咖啡机泡一杯咖啡的时间
     * 给定一个正整数N，表示N个人等着咖啡机泡咖啡， 每台咖啡机只能轮流泡咖啡， 只有一台洗咖啡杯的机器，一次只能洗一个杯子，时间耗费a,
     * 洗完才能洗下一杯，每个咖啡杯也可能自己挥发干净，时间耗费b，咖啡杯可以并行挥发，假设所有人拿到咖啡杯后立即喝完，返回从开始
     * 等到所有咖啡机变干净的最短时间。
     *
     * @return
     */
    public int coffee(int[] arr, int N, int wash, int air) {
        // 先通过小顶堆分配好，每个人喝完咖啡的时间的最优解
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] + b[1]);
        // 默认设置每个咖啡机的工作时间
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(new int[]{0, arr[i]});
        }
        int[] drinks = new int[N];
        for (int i = 0; i < N; i++) {
            int[] peek = priorityQueue.poll();
            peek[0] = peek[0] + peek[1];
            drinks[i] = peek[0];
            priorityQueue.add(peek);
        }
        return coffeeBackTrack(drinks, wash, air, 0, 0);
    }

    public int coffee1(int[] arr, int N, int wash, int air) {
        // 先通过小顶堆分配好，每个人喝完咖啡的时间的最优解
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] + b[1]);
        // 默认设置每个咖啡机的工作时间
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(new int[]{0, arr[i]});
        }
        int[] drinks = new int[N];
        for (int i = 0; i < N; i++) {
            int[] peek = priorityQueue.poll();
            peek[0] = peek[0] + peek[1];
            drinks[i] = peek[0];
            priorityQueue.add(peek);
        }
        return coffeeDp(drinks, wash, air, 0, 0);
    }

    @Test
    public void coffeeTest() {
        int[] arr = {3, 1, 7};
        System.out.println(coffee(arr, 7, 3, 5));
        System.out.println(coffee1(arr, 7, 3, 5));
    }

    /**
     * @param drinks N个人喝完的时间
     * @param wash   洗杯子需要的时间
     * @param air    挥发需要的时间
     * @param index  当前是第index人的杯子
     * @param free   洗杯子机从free时间开始可以洗杯子
     * @return
     */
    private int coffeeBackTrack(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }

        // 第index人，通过洗杯机洗杯子 花费时间 需要比较洗杯机的空闲时间与当前人喝完的时间
        int selfCost1 = Math.max(drinks[index], free) + wash;
        int otherCost1 = coffeeBackTrack(drinks, wash, air, index + 1, selfCost1);
        // 求自己与其他人花费的最久时间， 木桶效应
        int p1 = Math.max(selfCost1, otherCost1);

        // 第index人，通过挥发洗杯子 花费时间
        int selfCost2 = drinks[index] + air;
        int otherCost2 = coffeeBackTrack(drinks, wash, air, index + 1, free);

        int p2 = Math.max(selfCost2, otherCost2);

        return Math.min(p1, p2);
    }

    private int coffeeDp(int[] drinks, int wash, int air, int index, int free) {
        int maxFree = 0;
        for (int i = 0; i < drinks.length; i++) {
            maxFree = Math.max(drinks[i], maxFree) + wash;
        }
        int[][] dp = new int[drinks.length + 1][maxFree];
        for (int i = drinks.length - 1; i >= 0; i--) {
            for (int j = 0; j < maxFree; j++) {

                int selfCost1 = Math.max(drinks[i], j) + wash;
                if (selfCost1 >= maxFree) {
                    continue;
                }
                int otherCost1 = dp[i + 1][selfCost1]; // (drinks, wash, air, index + 1, selfCost1);
                // 求自己与其他人花费的最久时间， 木桶效应
                int p1 = Math.max(selfCost1, otherCost1);

                // 第index人，通过挥发洗杯子 花费时间
                int selfCost2 = drinks[i] + air;
                int otherCost2 = dp[i + 1][j]; //(drinks, wash, air, index + 1, free);

                int p2 = Math.max(selfCost2, otherCost2);
                dp[i][j] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    /**
     * 给定一个二维数组， 一个机器人从(0,0)位置出发，只能向右或向下走， 求走到右下的路径和最小
     *
     * @param arr
     * @return
     */
    public int robotWay(int[][] arr) {
        return robotWayBackTrack(arr, 0, 0);
    }

    @Test
    public void robotWayTest() {
        int[][] arr = {{3, 1, 2, 3}, {4, 3, 2, 3}, {2, 4, 3, 6}};
        System.out.println(robotWay(arr));
        System.out.println(robotWay1(arr));
        System.out.println(robotWay2(arr));
        System.out.println(robotWay3(arr));
        System.out.println(robotWay4(arr));
    }

    public int robotWayBackTrack(int[][] arr, int row, int col) {
        if (row >= arr.length || col >= arr[0].length) {
            return 0;
        }
        if (row == arr.length - 1 && col == arr[0].length - 1) {
            return arr[row][col];
        }
        int num = arr[row][col];
        if (col + 1 >= arr[0].length) {
            return robotWayBackTrack(arr, row + 1, col) + num;
        } else if (row + 1 >= arr.length) {
            return robotWayBackTrack(arr, row, col + 1) + num;
        } else {
            return Math.min(robotWayBackTrack(arr, row + 1, col) + num, robotWayBackTrack(arr, row, col + 1) + num);
        }
    }

    public int robotWay1(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return robotWayBackTrack1(arr, 0, 0, dp);
    }

    public int robotWayBackTrack1(int[][] arr, int row, int col, int[][] dp) {
        if (row >= arr.length || col >= arr[0].length) {
            return 0;
        }
        if (row == arr.length - 1 && col == arr[0].length - 1) {
            return arr[row][col];
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int num = arr[row][col];
        int min;
        if (row + 1 >= arr.length) {
            min = robotWayBackTrack1(arr, row, col + 1, dp) + num;
        } else if (col + 1 >= arr[0].length) {
            min = robotWayBackTrack1(arr, row + 1, col, dp) + num;
        } else {
            min = Math.min(robotWayBackTrack1(arr, row + 1, col, dp) + num, robotWayBackTrack1(arr, row, col + 1, dp) + num);
        }
        dp[row][col] = min;
        return min;
    }

    public int robotWay2(int[][] arr) {
        int row = arr.length - 1;
        int col = arr[0].length - 1;
        int[][] dp = new int[row + 1][col + 1];
        dp[row][col] = arr[row][col];
        for (int i = row - 1; i >= 0; i--) {
            dp[i][col] = dp[i + 1][col] + arr[i][col];
        }
        for (int j = col - 1; j >= 0; j--) {
            for (int i = row; i >= 0; i--) {
                int sum;
                int num = arr[i][j];
                if (i + 1 > row) {
                    sum = dp[i][j + 1] + num;
                } else {
                    sum = Math.min(dp[i + 1][j] + num, dp[i][j + 1] + num);
                }
                dp[i][j] = sum;
            }
        }
        return dp[0][0];
    }

    public int robotWay3(int[][] arr) {
        int row = arr.length - 1;
        int col = arr[0].length - 1;
        int[][] dp = new int[row + 1][col + 1];
        dp[0][0] = arr[0][0];
        for (int i = 1; i <= col; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }
        for (int i = 1; i <= row; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
            }
        }
        return dp[row][col];
    }

    public int robotWay4(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[] dp = new int[col];
        dp[0] = arr[0][0];
        for (int i = 1; i < col; i++) {
            dp[i] = dp[i - 1] + arr[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    dp[j] = dp[j] + arr[i][j];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + arr[i][j];
                }
            }
        }
        return dp[col - 1];
    }

    /**
     * 给定一个货币数组，值都为正数，再给一个aim， 即使货币的值是相同的，也认为每一张是不同的， 求返回组成aim的种数
     * arr: {1,1,1}    aimi = 2    ==>  {1,1}第0与第1个 {1,1}第0与第2 {1,1} 第1与第2个
     *
     * @param arr
     * @return
     */
    public int coin(int[] arr, int aim) {
        return coinBackTrack(arr, 0, aim);
    }

    @Test
    public void coinTest() {
//        int[] arr = {1, 1, 1};
        int[] arr = {1, 1, 1, 1, 2};
        System.out.println(coin(arr, 2));
        System.out.println(coin1(arr, 2));
        System.out.println(coin2(arr, 2));
        validCoinTest(10, 10, 100);
    }

    public void validCoinTest(int n, int maxVal, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            int[] arr1 = new int[n];
            int[] arr2 = new int[n];
            for (int i = 0; i < n; i++) {
                int num = (int) (Math.random() * maxVal) + 1;
                arr1[i] = num;
                arr2[i] = num;
            }
            int aim = (int) (Math.random() * maxVal) + maxVal * n;
            int[] arr = Arrays.copyOf(arr1, n);
            if (coin1(arr1, aim) != coin2(arr, aim)) {
                flag = false;
                System.out.println("Fuck Code");
            }
        }
        if (flag) {
            System.out.println("Nice Code");
        }
    }

    public int coinBackTrack(int[] arr, int start, int aim) {
        if (aim < 0) {
            return 0;
        }
        if (start == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        return coinBackTrack(arr, start + 1, aim) + coinBackTrack(arr, start + 1, aim - arr[start]);
    }

    public int coin1(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = aim; j >= 0; j--) {
                if (j - arr[i] >= 0) {
                    dp[i][j] = dp[i + 1][j] + dp[i + 1][j - arr[i]];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][aim];
    }

    public int coin2(int[] arr, int aim) {
        int n = arr.length;
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = aim; j >= 0; j--) {
                if (j - arr[i] >= 0) {
                    dp[j] = dp[j] + dp[j - arr[i]];
                }
            }
        }
        return dp[aim];
    }

    /**
     * arr是面值数组， 其中的值都是正数且没有重复，钱的张数是无限的, 求返回组成aim的方法数
     * arr: {1,2}   aim = 5  => {1,1,1,1} {1,1,2} {2,2}
     *
     * @param arr
     * @param aim
     * @return
     */
    public int coinPlus(int[] arr, int aim) {
        return coinPlusBack(arr, 0, aim);
    }

    @Test
    public void coinPlusTest() {
        int[] arr = {1, 2, 3};
        System.out.println(coinPlusDp(arr, 10));
        System.out.println(coinPlusBack11(arr, 0, 10));
        validCoinPlus(5, 10, 10);
    }

    public int coinPlusBack(int[] arr, int index, int aim) {
        if (aim < 0 || index == arr.length) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        return coinPlusBack(arr, index, aim - arr[index]) + coinPlusBack(arr, index + 1, aim);
    }

    public void validCoinPlus(int n, int maxVal, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            int[] arr = new int[n];
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int num = (int) (Math.random() * maxVal) + 1;
                while (set.contains(num)) {
                    num = (int) (Math.random() * maxVal) + 1;
                }
                set.add(num);
                arr[i] = num;
            }
            int aim = (int) (Math.random() * maxVal) + 5;
            if (coinPlusDp(arr, aim) != coinPlus1(arr, aim)) {
                System.out.println(Arrays.toString(arr));
                flag = false;
                System.out.println("Fuck Code");
            }
        }
        if (flag) {
            System.out.println("Nice Code");
        }
    }

    public int coinPlusBack11(int[] arr, int index, int aim) {
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= aim; zhang++) {
            ways += coinPlusBack11(arr, index + 1, aim - (zhang * arr[index]));
        }
        return ways;
    }

    public int coinPlusDp(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                /*int ways = 0;
                for (int zhang = 0; zhang * arr[i] <= j; zhang++) {
                    ways += dp[i + 1][j - (zhang * arr[i])];
                }
                dp[i][j] = ways;*/
                dp[i][j] = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] += dp[i][j - arr[i]];
                }
            }
        }
        return dp[0][aim];
    }

    public int coinPlus1(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        return coinPlusBack1(arr, 0, aim);
    }

    public int coinPlusBack1(int[] arr, int index, int aim) {
        if (aim < 0 || index == arr.length) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        return coinPlusBack(arr, index, aim - arr[index]) + coinPlusBack(arr, index + 1, aim);
    }


    public int coinChange(int[] coins, int amount) {
        int ans = coinChangeProcess(coins, 0, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    @Test
    public void coinChangeTest() {
        int[] arr = {1, 2, 5};
        System.out.println(coinChange(arr, 11));
        System.out.println(coinChange1(arr, 11));
    }

    public int coinChangeProcess(int[] arr, int index, int amount, int num) {
        if (amount < 0 || index == arr.length) {
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return num;
        }
        return Math.min(coinChangeProcess(arr, index, amount - arr[index], num + 1), coinChangeProcess(arr, index + 1, amount, num));
//        return p1 == Integer.MAX_VALUE? -1: p1;
    }

    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        return 0;
    }

    public int coinChange1(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        int ans = coinChangeProcess1(coins, 0, amount, 0, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int coinChangeProcess1(int[] arr, int index, int amount, int num, int[][] dp) {
        if (amount < 0 || index == arr.length) {
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return num;
        }
        if (dp[index][amount] != -1) {
            return dp[index][amount];
        }
        int ans = Math.min(coinChangeProcess1(arr, index, amount - arr[index], num + 1, dp),
                coinChangeProcess1(arr, index + 1, amount, num, dp));
        dp[index][amount] = ans;
        return ans;
    }

    public int coinPlus2(int[] arr, int amount) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int[] coins = new int[map.size()];
        int[] nums = new int[map.size()];
        Iterator<Map.Entry<Integer, Integer>> iterator =
                map.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            coins[index] = next.getKey();
            nums[index++] = next.getValue();
        }
        return coinPlus2Back(coins, nums, 0, amount);
    }

    public int coinPlus2Back(int[] coins, int[] nums, int index, int amount) {
        if (index == coins.length) {
            return amount == 0 ? 1 : 0;
        }
        int way = 0;
        for (int zhang = 0; zhang <= nums[index] && zhang * coins[index] - amount >= 0; zhang++) {
            way += coinPlus2Back(coins, nums, index + 1, amount - zhang * coins[index]);
        }
        return way;
    }

    public int coinPlus3(int[] arr, int amount) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int[] coins = new int[map.size()];
        int[] nums = new int[map.size()];
        Iterator<Map.Entry<Integer, Integer>> iterator =
                map.entrySet().iterator();
        int idx = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            coins[idx] = next.getKey();
            nums[idx++] = next.getValue();
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[n][amount] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int j = 0; j <= amount; j++) {
                int way = 0;
                for (int zhang = 0; zhang <= nums[index] && zhang * coins[index] - j >= 0; zhang++) {
                    way += dp[index + 1][j - zhang * coins[index]];
                }
                dp[index][j] = way;

                dp[index][j] = dp[index + 1][j];
                if (j - coins[index] >= 0) {
                    dp[index][j] += dp[index][j - coins[index]];
                }
                if (j - coins[index] * (nums[index] + 1) > 0) {
                    dp[index][j] -= dp[index + 1][j - (nums[index] + 1) * coins[index]];
                }
            }
        }
        return dp[0][amount];
    }

    /**
     * 给定一个棋盘，bob从(x,y)开始出发，可以从上下左右四个方向走，若走出棋盘边界，则会死亡，求bob走k步后的存活机率
     *
     * @param arr
     * @param x
     * @param y
     * @param k
     * @return
     */
    public double bobWalk(int x, int y, int a, int b, int k) {
        return (double) (bobWalkBack(x, y, a, b, k) / Math.pow(4, k));
    }

    @Test
    public void bobWalkTest() {
        System.out.println(bobWalk(9, 10, 3, 4, 15));
    }

    public int bobWalkBack(int x, int y, int a, int b, int k) {
        if (a < 0 || a > x || b < 0 || b > y) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        int p1 = bobWalkBack(x, y, a + 1, b, k - 1);
        p1 += bobWalkBack(x, y, a - 1, b, k - 1);
        p1 += bobWalkBack(x, y, a, b + 1, k - 1);
        p1 += bobWalkBack(x, y, a, b - 1, k - 1);
        return p1;
    }

    /**
     * 给定三个参数n,m,k, 怪兽有n滴血，等着英雄来打自己，英雄每打击一次，都会让怪兽流失[0-m]滴血，求k次打击后，英雄把怪兽砍死的概率
     *
     * @param n
     * @param m
     * @param k
     * @return
     */
    public double killMonster(int n, int m, int k) {
        return (double) (killMonsterBack(n, m, k) / Math.pow(m + 1, k));
    }

    @Test
    public void killMonsterTest() {
        System.out.println(killMonster2(50, 12, 5));
        System.out.println(killMonster1(50, 12, 5));
        validKillMonster(5, 50, 10);
    }

    public int killMonsterBack(int n, int m, int k) {
        /*if (n <= 0) {
            return 1;
        }*/
        if (k == 0) {
            return n <= 0 ? 1 : 0;
        }
        int way = 0;
        for (int i = 0; i <= m; i++) {
            way += killMonsterBack(n - i, m, k - 1);
        }
        return way;
    }

    public void validKillMonster(int n, int maxVal, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            int N = (int) (Math.random() * maxVal) + 1;
            int M = ((int) (Math.random() * maxVal) + 1) / 2;
            int K = (int) (Math.random() * n) + 1;
            if (killMonster2(N, M, K) != killMonster1(N, M, K)) {
                System.out.println(String.format("N : %d  M : %d  K: %d", N, M, K));
                flag = false;
                System.out.println("Fuck Code!!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code!!!");
        }
    }

    public double killMonster1(int n, int m, int k) {
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            dp[0][i] = (int) Math.pow(m + 1, i);
        }
        for (int hp = 1; hp <= n; hp++) {
            for (int times = 1; times <= k; times++) {
                int way = 0;
                for (int t = 0; t <= m; t++) {
                    if (hp - t >= 0) {
                        way += dp[hp - t][times - 1];
                    } else {
                        way += Math.pow(m + 1, times - 1);
                    }
                }
                dp[hp][times] = way;
            }
        }
        return dp[n][k] / Math.pow(m + 1, k);
    }

    public double killMonster2(int n, int m, int k) {
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            dp[0][i] = (int) Math.pow(m + 1, i);
        }
        for (int hp = 1; hp <= n; hp++) {
            for (int times = 1; times <= k; times++) {
                /*int way = 0;
                for (int t = 0; t <= m; t++) {
                    if (hp - t >= 0) {
                        way += dp[hp - t][times - 1];
                    } else {
                        way += Math.pow(m + 1, times - 1);
                    }
                }
                dp[hp][times] = way;*/
                dp[hp][times] = dp[hp][times - 1] + dp[hp - 1][times];
                if (hp - m - 1 >= 0) {
                    dp[hp][times] -= dp[hp - m - 1][times - 1];
                } else {
                    dp[hp][times] -= Math.pow(m + 1, times - 1);
                }
            }
        }
        return dp[n][k] / Math.pow(m + 1, k);
    }

    public int coinChangePlus(int[] coins, int amount) {
        int ans = coinChangePlusBackTrack(coins, 0, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    @Test
    public void coinChangePlusTest() {
        int[] coins = {1, 2, 5};
        System.out.println(coinChangePlus(coins, 11));
    }

    public int coinChangePlusBackTrack(int[] coins, int index, int amount) {
        if (index == coins.length) {
            return amount == 0 ? 0 : Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int zhang = 0; amount - zhang * coins[index] >= 0; zhang++) {
            int count = coinChangePlusBackTrack(coins, index + 1, amount - zhang * coins[index]);
            if (count != Integer.MAX_VALUE) {
                min = Math.min(min, count + zhang);
            }
        }
        return min;
    }

    public int coinChangeDp(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[n][0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                int min = Integer.MAX_VALUE;
                for (int zhang = 0; rest - zhang * coins[index] >= 0; zhang++) {
                    int count = dp[index + 1][rest - zhang * coins[index]];
                    if (count != Integer.MAX_VALUE) {
                        min = Math.min(min, count + zhang);
                    }
                }
                dp[index][rest] = min;
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    public int coinChangeDpPlus(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[n][0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0 && dp[index][rest - coins[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - coins[index]] + 1);
                }
                /*int min = Integer.MAX_VALUE;
                for (int zhang = 0; rest - zhang * coins[index] >= 0; zhang++) {
                    int count = dp[index + 1][rest - zhang * coins[index]];
                    if (count != Integer.MAX_VALUE) {
                        min = Math.min(min, count + zhang);
                    }
                }*/
            }
        }
        return dp[0][amount];
    }

    @Test
    public void splitNumTest() {
        System.out.println(splitNum(13, 1));
        System.out.println(splitNumDp(13));
    }

    public int splitNum(int n, int index) {
        if (n == 0) {
            return 1;
        }
        int way = 0;
        for (int i = index; i <= n; i++) {
            if (n - i >= 0) {
                way += splitNum(n - i, i);
            } else {
                break;
            }
        }
        return way;
    }

    public int splitNumDp(int n) {
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                int way = 0;
                for (int k = j; k <= i; k++) {
                    if (i - k >= 0) {
                        way += dp[i - k][k];
                    }
                }
                dp[i][j] = way;
            }
        }
        return dp[n][1];
    }

    public int minSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int target = sum / 2;
        int ans = minSumBackTrack(arr, target, 0);
        return ans;
    }

    @Test
    public void minSumTest() {
        int[] arr = {2, 4, 5, 100};
        System.out.println(minSum(arr));
    }

    public int minSumBackTrack(int[] arr, int target, int index) {
        if (index == arr.length) {
            return 0;
        }
        int p1 = minSumBackTrack(arr, target, index + 1);
        int p2 = 0;
        if (target - arr[index] >= 0) {
            p2 = arr[index] + minSumBackTrack(arr, target - arr[index], index + 1);
        }
        return Math.max(p1, p2);
    }

    /**
     * 给定一个数组arr, 将数组拆分为两个子集，若数组长度为偶数个， 则子集的长度要相等， 若数组长度为奇数个， 则子集的长度相差不超过1
     * 两个子集拆分后子集和尽量相等，求小的子集和
     * @param arr
     * @return
     */
    public int splitArr(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (arr.length % 2 == 0) {
            return splitArrBack(arr, 0, sum / 2, arr.length / 2);
        } else {
            return Math.min(splitArrBack(arr, 0, sum / 2, arr.length / 2), splitArrBack(arr, 0, (sum / 2)+1, arr.length / 2));
        }
    }

    public int splitArr1(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (arr.length % 2 == 0) {
            return splitArrBack(arr, 0, sum / 2, arr.length / 2);
        } else {
            return Math.min(splitArrBack(arr, 0, sum / 2, arr.length / 2), splitArrBack(arr, 0, (sum / 2) + 1, arr.length / 2));
        }
    }

    public void validSplitArr(int n, int maxVal, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            int len = (int)(Math.random()*n) + 1;
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = (int)(Math.random()*maxVal) + 1;
            }
            if (splitArr(arr) != splitArrDp(arr) || splitArr1(arr) != splitArr(arr)) {
                System.out.println(Arrays.toString(arr));
                flag = false;
                System.out.println("Fuck Code!!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code!!!");
        }
    }

    @Test
    public void splitArrTest() {
        int[] arr = {357, 593, 667, 153, 484, 298, 821, 369, 558};
        System.out.println(splitArr(arr));
        System.out.println(splitArr1(arr));
        System.out.println(splitArrDp(arr));
        validSplitArr(20, 1000, 500);
    }


    public int splitArrBack(int[] arr, int index, int target, int count) {
        if (index == arr.length) {
            return 0;
        }
        int p1 = splitArrBack(arr, index + 1, target, count);
        int p2 = 0;
        if (target - arr[index] >= 0 && count >= 1) {
            p2 = arr[index] + splitArrBack(arr, index + 1, target - arr[index], count - 1);
        }
        return Math.max(p1, p2);
    }

    public int splitArrBack1(int[] arr, int index, int target, int count) {
        if (index == arr.length) {
            return count == 0 ? 0 : -1;
        }
        int p1 = splitArrBack1(arr, index + 1, target, count);
        int p2 = -1;
        int next = -1;
        if (target - arr[index] >= 0) {
            next = splitArrBack1(arr, index + 1, target - arr[index], count - 1);
        }
        if (next != -1) {
            p2 = arr[index] + next;
        }
        return Math.max(p1, p2);
    }

    public int splitArrDp(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int n = arr.length;
        int target = sum/2;
        int m = (n+1)/2;
        int[][][] dp = new int[n+1][target+1][m+1];
        for (int index = n-1; index >= 0; index--) {
            for (int rest = 0; rest <= target ; rest++) {
                for (int count = 0; count <= m ; count++) {
                    int p1 = dp[index + 1][rest][count];
                    int p2 = 0;
                    if (rest - arr[index] >= 0 && count-1 >= 0) {
                        p2 = arr[index] + dp[index + 1][rest - arr[index]][count - 1];
                    }
                    dp[index][rest][count] = Math.max(p1, p2);
                }
            }
        }
        if (n%2 == 0) {
            return dp[0][target][m];
        } else {
            return Math.min(dp[0][target][m], dp[0][target][m-1]);
        }

    }

}
