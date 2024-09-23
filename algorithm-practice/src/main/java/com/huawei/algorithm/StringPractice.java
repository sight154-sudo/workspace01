package com.huawei.algorithm;/**
 * @author king
 * @date 2022/2/12-0:56
 * @Desc
 */

import lombok.ToString;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

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
                set.remove(s.charAt(i - 1));
            }
            while (idx + 1 < s.length() && !set.contains(s.charAt(idx + 1))) {
                set.add(s.charAt(idx + 1));
                idx++;
            }
            maxLen = Math.max(maxLen, idx - i + 1);
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
                idx = Math.max(idx, map.get(s.charAt(i)) + 1);
            }
            // 每一次遍历都需要更新当前字符在map中的位置
            map.put(s.charAt(i), i);
            // 比较当前子串长度是否最大   子串的长度为i-idx+1
            maxLen = Math.max(maxLen, i - idx + 1);
        }
        return maxLen;

    }

    @Test
    public void fun123() {
        String s = "aefaw";
        System.out.println(s.substring(-1, 3));
    }


    public int kmp(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return -1;
        }
        if (s1.length() < s2.length()) {
            return -1;
        }
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int[] next = getNext(ch2);
        // 表示s1查找的位置
        int x = 0;
        // 表示s2查找的位置
        int y = 0;
        while (x < ch1.length && y < ch2.length) {
            if (ch1[x] == ch2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == ch2.length ? x - y : -1;
    }

    @Test
    public void kmpTest() {
        String s = "wefwgwewqewfewtgywerfwio";
        String s2 = "wewqew";
        System.out.println(kmp(s, s2));
    }

    private int[] getNext(char[] ch2) {
        if (ch2.length == 1) {
            return new int[]{-1};
        }
        int n = ch2.length;
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < ch2.length) {
            if (ch2[cn] == ch2[i - 1]) {
                next[i++] = ++cn;
            } else if (next[cn] > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public String removeStr(String num, int start, int end) {
        if (start == end) {
            return num;
        }
        return num.substring(0, start) + num.substring(end);
    }

    public String removeKdigits(String num, int k) {
        if (k == num.length()) {
            return "0";
        }
        Stack<Integer> stack = new Stack<>();
        int w = 0;
        f1:
        while (w < num.length()) {
            int c = num.charAt(w) - 48;
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && c < stack.peek()) {
                    k--;
                    stack.pop();
                    if (k == 0) {
                        while (w < num.length()) {
                            stack.add(num.charAt(w) - 48);
                            w++;
                        }
                        break f1;
                    }
                }
            }
            stack.add(c);
            w++;
        }
        while (k > 0) {
            stack.pop();
            k--;
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String s = sb.reverse().toString();
        int start = 0;
        while (start < s.length() && s.charAt(start) == '0') {
            start++;
        }
        return start >= s.length() ? "0" : s.substring(start);
    }

    @Test
    public void removeKdigitsTest() {
        String str = "1432219";
//        String str = "112";
//        String str = "112";
        int k = 3;
        System.out.println(removeKdigits(str, k));
    }

    public String getNewString(String str, int k) {
        /*int min = 10;
        int w = 0;
        int index = 0;
        while (w <= k && w < str.length()) {
            int c = str.charAt(w) - 48;
            if (c < min) {
                min = c;
                index = c;
            }
            if (min == 0) {
                break;
            }
            w++;
        }*/
        return null;
    }

    @Test
    public void getSubPosTest() {
        String s = "";
        String b = "abc";
        System.out.println(getSubPos(s, b));
        randomGetSubPos(10000, 100000);
    }

    public void randomGetSubPos(int count, int len) {
        for (int c = 0; c < count; c++) {
            StringBuffer sb1 = new StringBuffer();
            StringBuilder sb2 = new StringBuilder();
            int n1 = (int) (Math.random() * len);
            int n2 = (int) (Math.random() * len);
            for (int i = 0; i < n1; i++) {
                sb1.append((char) (int) (Math.random() * 26 + 97));
            }
            for (int i = 0; i < n2; i++) {
                sb2.append((char) (int) (Math.random() * 26 + 97));
            }
            String s = sb1.toString();
            String l = sb2.toString();
            if (getSubPos(s, l) != getSubPos1(s, l)) {
                System.out.println("Fuck Code");
            }
        }
    }

    public int getSubPos(String S, String L) {
        if (S.length() > L.length() || S.length() == 0) {
            return -1;
        }
        int i = 0;
        int j = 0;
        while (j < L.length()) {
            if (i == S.length() - 1 && S.charAt(i) == L.charAt(j)) {
                return j;
            }
            if (S.charAt(i) == L.charAt(j)) {
                i++;
            }
            j++;
        }
        return -1;
    }

    public int getSubPos1(String S, String L) {
        int n1 = S.length();
        int n2 = L.length();
        if (n1 > n2 || n1 == 0) {
            return -1;
        }
        int i = 0;
        int ans = -1;
        for (int j = 0; j < n2; j++) {
            if (S.charAt(i) == L.charAt(j)) {
                i++;
                if (i == n1) {
                    ans = j;
                    break;
                }
            }
        }
        return ans;
    }

    @Test
    public void computeExprTest() {
//        String s = "1+2+3";
//        String s = "1 + 5 * 7 / 8";
//        String s = "1 / (0 - 5)";
//        String s = "1+(4+(2*7))";
//        String s = "15350-1034000/(40023400-40240024/(2232435*73324234))";
        String s = "6*((1-2)/1)";
        computeExpr(s);
    }

    public String computeExpr(String s) {
        // 1+2*(3/(4-2)+4)
        s = s.replaceAll("\\s+", "");
        Stack<FuShu> stack = new Stack<>();
        Stack<Character> symbol = new Stack<>();
        try {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    int num = c - 48;
                    while (Character.isDigit(s.charAt(++i))) {
                        num *= 10 + (s.charAt(i) - 48);
                    }
                    stack.add(new FuShu(num, 1));
                    i -= 1;
                } else if (c == '(') {
                    symbol.add(c);
                } else if (c == ')') {
                    while (!symbol.isEmpty() ) {
                        Character f = symbol.pop();
                        if (f == '(') {
                            break;
                        }
                        FuShu fuShu2 = stack.pop();
                        FuShu fuShu1 = stack.pop();
                        stack.add(calc(fuShu1, fuShu2, f));
                    }
                } else {
                    if (!symbol.isEmpty()) {
                        while (!symbol.isEmpty() && exprWeight(symbol.peek()) > exprWeight(c)) {
                            FuShu fuShu2 = stack.pop();
                            FuShu fuShu1 = stack.pop();
                            stack.add(calc(fuShu1, fuShu2, symbol.pop()));
                        }
                    }
                    symbol.add(c);
                }
            }
            while (!symbol.isEmpty()) {
                FuShu fuShu2 = stack.pop();
                FuShu fuShu1 = stack.pop();
                stack.add(calc(fuShu1, fuShu2, symbol.pop()));
            }
        } catch (RuntimeException exp) {
            System.out.println("ERROR");
            return s;
        }
        System.out.println(stack.pop());
        return s;
    }

    public int exprWeight(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }

    public FuShu calc(FuShu f1, FuShu f2, char c) throws RuntimeException {
        switch (c) {
            case '+':
                return f1.add(f2);
            case '-':
                return f1.sub(f2);
            case '*':
                return f1.mul(f2);
            default:
                return f1.div(f2);
        }
    }

    @Test
    public void FuShuTest() {
        FuShu fuShu1 = new FuShu(7, 8);
        FuShu fuShu2 = new FuShu(-8, 9);
//        FuShu fuShu = fuShu1.add(fuShu2);
        FuShu fuShu = fuShu1.div(fuShu2);
        FuShu fuShu3 = new FuShu(0, 10);
        System.out.println(fuShu3.mul(fuShu1));
        System.out.println(fuShu);
    }

    class FuShu {
        public int fz;
        public int fm;

        public FuShu(int fz, int fm) {
            this.fz = fz;
            this.fm = fm;
        }

        public FuShu add(FuShu other) {
            int fz = this.fz * other.fm + other.fz * this.fm;
            int fm = this.fm * other.fm;
            return new FuShu(fz, fm).simply();
        }

        public FuShu sub(FuShu other) {
            int fz = this.fz * other.fm - this.fm * other.fz;
            int fm = this.fm * other.fm;
            return new FuShu(fz, fm).simply();
        }

        public FuShu mul(FuShu other) {
            return new FuShu(this.fz * other.fz, this.fm * other.fm).simply();
        }

        public FuShu div(FuShu other) {
            int fz = this.fz * other.fm;
            int fm = this.fm * other.fz;
            if (fm == 0) {
                throw new ArithmeticException();
            }
            return new FuShu(fz, fm).simply();
        }

        @Override
        public String toString() {
            if (this.fm == 1) {
                return this.fz + "";
            }
            int fz = Math.abs(this.fz);
            int fm = Math.abs(this.fm);
            if (this.fz * this.fm < 0) {
                return String.format("-%d/%d", fz, fm);
            }
            return String.format("%d/%d", fz, fm);
        }

        public FuShu simply() {
            int gcd = gcd(Math.abs(this.fz), Math.abs(this.fm));
            this.fz /= gcd;
            this.fm /= gcd;
            return this;
        }

    }

    public int gcd(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        while (b > 0) {
            int p = b;
            b = a % b;
            a = p;
        }
        return a;
    }

    @Test
    public void gcdTest() {
        int a = 126;
        int b = 9;
        System.out.println(gcd(a, b));
    }

}
