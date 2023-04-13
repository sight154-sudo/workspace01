package com.huawei.algorithm.leetcode.strPractice;

import org.junit.Test;

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
            overflow = num/2;
            sb.append(num%2);
        }
        while (i >=0) {
            int num = a.charAt(i) + overflow - 48;
            overflow = num/2;
            sb.append(num%2);
            i--;
        }
        while (j >= 0) {
            int num = b.charAt(j) + overflow - 48;
            overflow = num/2;
            sb.append(num%2);
            j--;
        }
        if (overflow > 0) {
            sb.append(overflow);
        }
        return sb.reverse().toString();
    }
}
