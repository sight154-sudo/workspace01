package com.huawei.algorithm.leetcode.backTrack;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author king
 * @date 2023/12/7-22:48
 * @Desc
 */
public class BackTrackPractice {

    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * <p>
     * 你可以按 任何顺序 返回答案。
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        combine(n, k, 1, list, result);
        return list;
    }

    @Test
    public void combineTest() {
        List<List<Integer>> combine = combine(4, 2);
        System.out.println(combine);
    }

    public void combine(int n, int k, int cur, List<List<Integer>> list, List<Integer> result) {
        if (result.size() == k) {
            list.add(new ArrayList<>(result));
            return;
        }
        for (int i = cur; i <= n; i++) {
            if (result.contains(i)) {
                continue;
            }
            result.add(i);
            combine(n, k, i, list, result);
            result.remove(result.size() - 1);
        }
    }

    /**
     * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * 只使用数字1到9
     * 每个数字 最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        combinationSum3(n, k, 1, list, result);
        return list;
    }

    @Test
    public void combinationSum3Test() {
        int n = 9, k = 3;
        System.out.println(combinationSum3(k, n));
    }

    public void combinationSum3(int n, int k, int cur, List<List<Integer>> list, List<Integer> result) {
        if (k < 0 || n < 0) {
            return;
        }
        if (n == 0 && k == 0) {
            list.add(new ArrayList<>(result));
            return;
        }
        for (int i = cur; i <= 9; i++) {
            if (result.contains(i)) {
                continue;
            }
            result.add(i);
            k -= 1;
            combinationSum3(n - i, k, i + 1, list, result);
            k += 1;
            result.remove(result.size() - 1);
        }
    }

    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        String[] strs = new String[]{"abc", "def", "ghi", "jkl", "mon", "pqrs", "tuv", "wxyz"};
        char[] chars = digits.toCharArray();
        if (chars.length == 0) {
            return list;
        }
        letterCombinations(0, list, chars, strs, "");
        return list;
    }

    @Test
    public void letterCombinationsTest() {
        String digits = "239";
        System.out.println(letterCombinations(digits));
    }

    public void letterCombinations(int index, List<String> list, char[] src, String[] strs, String str) {
        if (index == src.length) {
            list.add(str);
            return;
        }
        StringBuilder sb = new StringBuilder(str);
        char[] chars = strs[src[index] - 50].toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            letterCombinations(index + 1, list, src, strs, sb.toString());
            sb.setLength(sb.length() - 1);
        }
    }

    /**
     * 39. 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
     * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, target, result, list, 0);
        return list;
    }

    @Test
    public void combinationSumTest() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
        double d = 23423423523.32;
        System.out.printf("%.5f", d);
    }

    @Test
    public void printf() {
        double pi = Math.PI;
        Integer i = 100000;
        System.out.printf("%f%n", pi);
        System.out.printf("%f, %e, %E, %,8f %n", pi, pi, pi, pi);
        System.out.printf("%,8d, %o, %x, %X%n", i, i, i, i);
        double d = 23423423523.32;
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        System.out.println(decimalFormat.format(d));
    }

    private void combinationSum(int[] candidates, int target, List<Integer> result, List<List<Integer>> list, int index) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            list.add(new ArrayList<>(result));
            return;
        }
        for (int i = index; i < candidates.length && target >= candidates[i]; i++) {
            target -= candidates[i];
            result.add(candidates[i]);
            combinationSum(candidates, target, result, list, i);
            result.remove(result.size() - 1);
            target += candidates[i];
        }
    }

    @Test
    public void findMedianSortedArraysTest() {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length != 0) {
            return findResult(nums2);
        } else if (nums1.length != 0 && nums2.length == 0) {
            return findResult(nums1);
        } else {
            int pre = 0;
            int cur = 0;
            int len = nums1.length + nums2.length;
            int mid = len / 2;
            int i = 0;
            int n1 = 0;
            int n2 = 0;
            while (i <= mid) {
                pre = cur;
                if (n1 >= nums1.length) {
                    cur = nums2[n2++];
                } else if (n2 >= nums2.length) {
                    cur = nums1[n1++];
                } else if (nums1[n1] < nums2[n2]) {
                    cur = nums1[n1++];
                } else {
                    cur = nums2[n2++];
                }
                i++;
            }
            if ((len & 1) == 0) {
                return (pre + cur) / 2.0;
            }
            return cur;
        }
    }

    private double findResult(int[] nums2) {
        int len = nums2.length;
        int mid = len / 2;
        if ((len & 1) == 0) {
            return (nums2[mid - 1] + nums2[mid]) / 2.0;
        }
        return nums2[mid];
    }

    /**
     * 40. 组合总和 II
     * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     * 注意：解集不能包含重复的组合。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Arrays.sort(candidates);
        boolean[] bool = new boolean[candidates.length];
        combinationSum2(candidates, target, list, result, 0, bool);
        return list;
    }

    @Test
    public void combinationSum2Test() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(combinationSum2(candidates, target));
    }

    private void combinationSum2(int[] candidates, int target, List<List<Integer>> list, List<Integer> result, int index, boolean[] bool) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            list.add(new ArrayList<>(result));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > 0 && (candidates[i - 1] == candidates[i] && !bool[i - 1])) {
                continue;
            }
            target -= candidates[i];
            bool[i] = true;
            result.add(candidates[i]);
            combinationSum2(candidates, target, list, result, i + 1, bool);
            result.remove(result.size() - 1);
            bool[i] = false;
            target += candidates[i];
        }
    }

    /**
     * 131. 分割回文串
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
//        partitionForce(result, list, 0, s.toCharArray(), s.length());
        boolean[][] dp = getDp(s);
//        partitionForce2(result, list, 0, s.toCharArray());
        partitionForce3(result, list, 0, s.toCharArray(), dp);
        return result;
    }

    @Test
    public void partitionTest() {
        String s = "aab";
        System.out.println(partition(s));
    }

    public void partitionForce3(List<List<String>> result, List<String> list, int index, char[] chs, boolean[][] dp) {
        if (index >= chs.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < chs.length; i++) {
            if (dp[index][i]) {
                list.add(new String(chs, index, i-index+1));
            } else {
                continue;
            }
            partitionForce2(result, list, i+1, chs);
            list.remove(list.size()-1);
        }
    }

    public void partitionForce2(List<List<String>> result, List<String> list, int index, char[] chs) {
        if (index >= chs.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < chs.length; i++) {
            String s = new String(chs, index, i - index + 1);
            if (isPalindrome(s)) {
                list.add(s);
            } else {
                continue;
            }
            partitionForce2(result, list, i+1, chs);
            list.remove(list.size()-1);
        }
    }

    public boolean[][] getDp(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        boolean[][] dp = new boolean[len][len];
        for (int left = len-1; left >= 0 ; left--) {
            for (int right = left; right < len; right++) {
                if (left == right) {
                    dp[left][right] = true;
                } else {
                    if (ch[left] == ch[right] && (right - left <= 2 || dp[left+1][right-1])) {
                        dp[left][right] = true;
                    }
                }
            }
        }
        return dp;
    }

    public void partitionForce(List<List<String>> result, List<String> list, int index, char[] chs, int length) {
        if (length == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < chs.length; i++) {
            for (int len = 1; i + len <= chs.length; len++) {
                String s = new String(chs, i, len);
                if (!isPalindrome(s)) {
                    continue;
                }
                list.add(s);
                length -= s.length();
                partitionForce(result, list, i + len, chs, length);
                length += s.length();
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }

}
