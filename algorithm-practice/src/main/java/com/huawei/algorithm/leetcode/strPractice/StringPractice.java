package com.huawei.algorithm.leetcode.strPractice;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author king
 * @date 2023/4/4-0:06
 * @Desc
 */
public class StringPractice {
    /**
     * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        String[] split = s.split("\\s+");
        int len = split.length;
        return split[len - 1].length();
    }

    public int lengthOfLastWord1(String s) {
        s = s.trim();
        int index = s.length() - 1;
        int len = 0;
        while (s.charAt(index) != ' ' && index >= 0) {
            len++;
            index--;
        }
        return len;
    }

    @Test
    public void addBinaryTest() {
        String a = "111";
        String b = "1";
        System.out.println(addBinary2(a, b));
    }

    public String addBinary(String a, String b) {
        int diff = a.length() - b.length();
        if (diff > 0) {
            b = fillZero(diff, b);
        } else {
            a = fillZero(-diff, a);
        }
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        int overflow = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = c1.length - 1; i >= 0; i--) {
            int num = c1[i] + c2[i] - 96 + overflow;
            overflow = num / 2;
            sb.append(num % 2);
        }
        return overflow > 0 ? sb.append(overflow).reverse().toString() : sb.reverse().toString();
    }

    private String fillZero(int diff, String s) {
        StringBuilder sb = new StringBuilder(diff);
        for (int i = 0; i < diff; i++) {
            sb.append("0");
        }
        return sb.append(s).toString();
    }

    public String addBinary2(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        int overflow = 0;
        StringBuilder sb = new StringBuilder();
        for (; i >= 0 && j >= 0; i--, j--) {
            int num = a.charAt(i) + b.charAt(j) - 96 + overflow;
            overflow = num / 2;
            sb.append(num % 2);
        }
        while (i >= 0) {
            int num = a.charAt(i) + overflow - 48;
            overflow = num / 2;
            sb.append(num % 2);
            i--;
        }
        while (j >= 0) {
            int num = b.charAt(j) + overflow - 48;
            overflow = num / 2;
            sb.append(num % 2);
            j--;
        }
        if (overflow > 0) {
            sb.append(overflow);
        }
        return sb.reverse().toString();
    }

    public String reverseWords(String s) {

        return null;
    }

    /**
     * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
     * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        // abccccdd   =>   dccaccd
        int[] map = new int[52];
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] >= 'a' && chs[i] <= 'z') {
                map[chs[i] - 'a']++;
            } else {
                map[chs[i] - 'A' + 26]++;
            }
        }
        int odd = 0;
        int even = 0;
        boolean flag = false;
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 != 0) {
                flag = true;
                odd += map[i] - 1;
            } else {
                even += map[i];
            }
        }
        return flag ? odd + even + 1 : odd + even;
    }

    @Test
    public void longestPalindromeTest() {
        String s = "abccccdd";
        System.out.println(longestPalindrome(s));
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * <p>
     * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] chs = s.toCharArray();
        String ans = String.valueOf(chs[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            for (int j = i + 1; j < chs.length; j++) {
                boolean isPalindrome = isPalindrome(chs, i, j);
                if (isPalindrome) {
                    for (int k = i; k <= j; k++) {
                        sb.append(chs[k]);
                    }
                    String str = sb.toString();
                    if (str.length() > ans.length()) {
                        ans = str;
                    }
                    sb.setLength(0);
                }
            }
        }
        return ans;
    }

    @Test
    public void longestPalindrome1Test() {
        String s = "babad";
        System.out.println(longestPalindrome1(s));
        Map map = new HashMap<>();
    }

    private boolean isPalindrome(char[] chs, int l, int r) {
        while (l < r) {
            if (chs[l++] != chs[r--]) {
                return false;
            }
        }
        return true;
    }
}
