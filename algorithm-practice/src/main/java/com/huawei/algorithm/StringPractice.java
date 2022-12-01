package com.huawei.algorithm;/**
 * @author king
 * @date 2022/2/12-0:56
 * @Desc
 */

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @program: springboot-demo
 * @author: Mr.King
 * @create: 2022-02-12 00:56
 **/

public class StringPractice {

    @Test
    public void testStrstr() {
        String src = "";
        String target = "";
        int index = strstr(src, target);
        System.out.println("index = " + index);
    }

    /**
     * 实现strstr函数，找出target位于src的位置
     *
     * @param src
     * @param target
     * @return
     */
    public int strstr(String src, String target) {
        if (src.length() < target.length()) {
            return -1;
        }
        int len = target.length();
        for (int i = 0; i < src.length() - len + 1; i++) {
            boolean flag = true;
            for (int j = 0; j < len; j++) {
                if (src.charAt(i + j) != target.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    Scanner sc = new Scanner(System.in);

    @Test
    public void testScanner() {
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
     * <p>
     * 3. 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 暴力解法
        // abcabcbb => 3 'abc'     bbbbb => 1 'b'     pwwkew  => 3 'wke'
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                if (sb.indexOf(String.valueOf(chars[j])) != -1) {
                    break;
                }
                sb.append(chars[j]);
            }
            if (maxLen < sb.length()) {
                maxLen = sb.length();
            }
            sb.setLength(0);
        }
        return maxLen;
    }

    @Test
    public void lengthOfLongestSubstring() {
        // abcabcbb => 3 'abc'     bbbbb => 1 'b'     pwwkew  => 3 'wke'
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring02(s));
    }

    public int lengthOfLongestSubstring01(String s) {
        // 滑动窗口  根据题意需要求出最长的无重复字符子串, 定义一个窗口，依次向右移动，若新添加的元素有重复，
        // 则窗口往右移动 移动的位置到重复字符位置加1
        // abcabcbb => 3 'abc'     bbbbb => 1 'b'     pwwkew  => 3 'wke'
        // 记录当前窗口的右索引位置
        int idx = -1;
        // 子串的最大长度
        int maxLen = 0;
        // 窗口列表
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                // 表示有重复字符
                set.remove(s.charAt(i-1));
            }
            while (idx+1 < s.length() && !set.contains(s.charAt(idx+1))) {
               set.add(s.charAt(idx+1));
               idx++;
            }
            maxLen = Math.max(maxLen, idx-i+1);
        }
        return maxLen;

    }

    public int lengthOfLongestSubstring02(String s) {
        // 滑动窗口  根据题意需要求出最长的无重复字符子串, 定义一个窗口，依次向右移动，若新添加的元素有重复，
        // 则窗口往右移动 移动的位置到重复字符位置加1
        // abcabcbb => 3 'abc'     bbbbb => 1 'b'     pwwkew  => 3 'wke'
        // 记录当前窗口中左索引位置
        int idx = 0;
        // 子串的最大长度
        int maxLen = 0;
        // 窗口列表 使用hashmap代替set, string保存子串， value保存左子串的首索引
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // 如果当前字符在出现了重复，则根据子串的左索引与当前字符的位置比较出新子串的左索引，判断是否需要更新子串的索引
                // 例如:abba时，要当遍历到第二个a时，不需要更新左索引的位置
                idx = Math.max(idx, map.get(s.charAt(i))+1);
            }
            // 每一次遍历都需要更新当前字符在map中的位置
            map.put(s.charAt(i), i);
            // 比较当前子串长度是否最大   子串的长度为i-idx+1
            maxLen = Math.max(maxLen, i-idx+1);
        }
        return maxLen;

    }

    @Test
    public void fun123() {
        String s = "aefaw";
        System.out.println(s.substring(-1,3));
    }

}
