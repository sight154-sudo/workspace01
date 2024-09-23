package com.huawei.algorithm.leetcode.strPractice;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    @Test
    public void printXTest() {
        printX(3);
    }

    public void printX(int n) {
        List<List<String>> list = new ArrayList<>();
        List<String> tmp1 = new ArrayList<>();
        tmp1.add("X");
        tmp1.add(" ");
        tmp1.add("X");
        List<String> tmp2 = new ArrayList<>();
        tmp2.add(" ");
        tmp2.add("X");
        tmp2.add(" ");
        List<String> tmp3 = new ArrayList<>();
        tmp3.add("X");
        tmp3.add(" ");
        tmp3.add("X");
        list.add(tmp1);
        list.add(tmp2);
        list.add(tmp3);
        System.out.println(list);
        List<List<List<String>>> a = new ArrayList<>();
    }


    /**
     * 给你一个下标从 0 开始的字符串 expression ，格式为 "<num1>+<num2>" ，其中 <num1> 和 <num2> 表示正整数。
     * <p>
     * 请你向 expression 中添加一对括号，使得在添加之后， expression 仍然是一个有效的数学表达式，并且计算后可以得到 最小 可能值。左括号 必须 添加在 '+' 的左侧，而右括号必须添加在 '+' 的右侧。
     * <p>
     * 返回添加一对括号后形成的表达式 expression ，且满足 expression 计算得到 最小 可能值。如果存在多个答案都能产生相同结果，返回任意一个答案。
     * leetCode 2232
     *
     * @param expression
     * @return
     */
    public String minimizeResult(String expression) {
        // 246+38
        String[] split = expression.split("\\+");
        int[] arr = new int[2];
        getExprMax(0, split[0], split[1].length(), split[1], arr, Integer.MAX_VALUE);
        String left = arr[0] == 0 ? "(" + split[0] : split[0].substring(0, arr[0]) + "(" + split[0].substring(arr[0], split[0].length());
        String right = arr[1] == split[1].length() ? split[1] + ")" : split[1].substring(0, arr[1]) + ")" + split[1].substring(arr[1], split[1].length());
        return left + "+" + right;
    }

    @Test
    public void minimizeResultTest() {
        String expression = "411611+478";
        System.out.println(minimizeResult(expression));
//        System.out.println(expression.substring(0, 0));
    }

    private void getExprMax(int i, String l, int j, String r, int[] arr, int max) {
        if (i == l.length() || j <= 0) {
            return;
        }
        String left = i == 0 ? "1" : l.substring(0, i);
        String left2 = l.substring(i, l.length());

        String right = r.substring(0, j);
        String right2 = j == r.length() ? "1" : r.substring(j, r.length());
        int result = Integer.valueOf(left) * (Integer.valueOf(left2) + Integer.valueOf(right)) * Integer.valueOf(right2);
        if (result < max) {
            max = result;
            arr[0] = i;
            arr[1] = j;
        }
        getExprMax(i + 1, l, j, r, arr, max);
        getExprMax(i, l, j - 1, r, arr, max);

    }

    public int[] getExpressionMaxResForce(String[] expr, int start, int end) {
        // 1 + 2 * 3 + -1
        if (start == end) {
            int n = Integer.valueOf(expr[start]);
            int[] res = {n, n};
            return res;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int step = 2;
        for (int i = start; i < end; i += step) {
            int[] p1 = getExpressionMaxResForce(expr, start, i);
            int[] p2 = getExpressionMaxResForce(expr, i + 2, end);
            for (int k : p1) {
                for (int j : p2) {
                    int ans = getResult(k, j, expr[i + 1]);
                    max = Math.max(max, ans);
                    min = Math.min(min, ans);
                }
            }
        }
        int[] res = new int[2];
        res[0] = max;
        res[1] = min;
        return res;
    }

    public int[] getExpresionMaxResCache(String[] expr, int start, int end, Map<String, int[]> map) {
        if (start == end) {
            int n = Integer.valueOf(expr[start]);
            return new int[]{n, n};
        }
        String key = "start:" + start + "end:" + end;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int step = 2;
        for (int i = start; i < end; i += step) {
            int[] p1 = getExpresionMaxResCache(expr, start, i, map);
            int[] p2 = getExpresionMaxResCache(expr, i + 2, end, map);
            for (int k : p1) {
                for (int j : p2) {
                    int ans = getResult(k, j, expr[i + 1]);
                    max = Math.max(max, ans);
                    min = Math.min(min, ans);
                }
            }
        }
        int[] res = {max, min};
        map.put(key, res);
        return res;
    }

    public int getExpressionMaxResult(String[] expr, int start, int end) {
        int n = expr.length;
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i += 2) {
            int num = Integer.valueOf(expr[i]);
            dp[i][i][0] = num;
            dp[i][i][1] = num;
        }
        for (int count = 2; count < n; count++) {
            for (int startIndex = 0; startIndex < n; startIndex += 2) {
                int endIndex = startIndex + 2 * (count - 1);
                if (endIndex >= n) {
                    break;
                }
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int i = startIndex; i < endIndex; i += 2) {
                    int[] p1 = dp[startIndex][i];
                    int[] p2 = dp[i + 2][endIndex];
                    for (int k : p1) {
                        for (int j : p2) {
                            int ans = getResult(k, j, expr[i + 1]);
                            max = Math.max(max, ans);
                            min = Math.min(min, ans);
                        }
                    }
                }
                dp[startIndex][endIndex][0] = max;
                dp[startIndex][endIndex][1] = min;
            }
        }

        return dp[0][n - 1][0];
    }

    @Test
    public void getExpressionResultTest() {
        String[] expr = {"-1", "+", "5", "*", "-7", "+", "1", "+", "3", "+", "0", "*", "1", "+", "-5", "*", "-4", "*", "6"};
        Map<String, int[]> map = new HashMap<>();
        System.out.println(getExpressionMaxResForce(expr, 0, expr.length - 1)[0]);
        System.out.println(getExpresionMaxResCache(expr, 0, expr.length - 1, map)[0]);
        System.out.println(getExpressionMaxResult(expr, 0, expr.length - 1));
        validGetExpressionMaxResult(10, 10, 10000);
    }

    public void validGetExpressionMaxResult(int n, int maxVal, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            String[] expr = getExpr(n, maxVal);
            Map<String, int[]> map = new HashMap<>();
            if (getExpressionMaxResult(expr, 0, expr.length - 1) != getExpresionMaxResCache(expr, 0, expr.length - 1, map)[0]) {
                System.out.println(Arrays.toString(expr));
                flag = false;
                System.out.println("Fuck Code");
            }
        }
        if (flag) {
            System.out.println("Nice Code");
        }
    }

    public String[] getExpr(int n, int maxVal) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = (int) (Math.random() * maxVal) - (int) (Math.random() * maxVal);
            sb.append(num).append(" ");
            if (i < n - 1) {
                sb.append(Math.random() > 0.5 ? "*" : "+").append(" ");
            }
        }
        return sb.substring(0, sb.length() - 1).split(" ");
    }


    public int getResult(int a, int b, String expr) {
        return expr.equals("*") ? a * b : a + b;
    }

    /**
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        int[] arr = new int[26];
        for (int i = 0; i < s1.length; i++) {
            arr[s1[i] - 'a']++;
        }
        for (int i = 0; i < t1.length; i++) {
            arr[t1[i] - 'a']--;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     * leetCode 383
     * 如果可以，返回 true ；否则返回 false 。
     * <p>
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] r1 = ransomNote.toCharArray();
        char[] m1 = magazine.toCharArray();
        int[] arr = new int[26];
        for (int i = 0; i < m1.length; i++) {
            arr[m1[i] - 'a']++;
        }
        for (int i = 0; i < r1.length; i++) {
            if (arr[r1[i] - 'a']-- <= 0) {
                return false;
            }
        }
        return true;
    }

    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char c = s[i];
            s[i] = s[j];
            s[j] = c;
        }
    }

    public String pathEncryption(String path) {
        char[] chs = path.toCharArray();
        for (int i = 0, j = chs.length - 1; i <= j; i++, j--) {
            if (chs[i] == '.') {
                chs[i] = ' ';
            }
            if (chs[j] == '.') {
                chs[j] = ' ';
            }
        }
        return new String(chs, 0, chs.length);
    }

    @Test
    public void replaceSpaceTest() {
        String s = "We are happy.";
        System.out.println(replaceSpace(s));
    }

    public String replaceSpace(String s) {
        // s = "We are happy."
        // We%20are%20happy.
        int num = 0;
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ' ') {
                num += 2;
            }
        }
        char[] target = new char[s.length() + num];
        int i = chs.length - 1;
        int j = target.length - 1;
        while (i >= 0) {
            if (chs[i] != ' ') {
                target[j--] = chs[i--];
            } else {
                target[j--] = '0';
                target[j--] = '2';
                target[j--] = '%';
                i--;
            }
        }
        return new String(target, 0, target.length);
    }

    /**
     * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
     * <p>
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * <p>
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     * <p>
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。
     * 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     *
     * @param s
     * @return
     */
    public String reverseWords1(String s) {
        // the sky is blue   =>  blue is sky the
        // "  hello world  "  => "world hello"
        // "a good   example"  => "example good a"
        // 使用原生api解题
        String[] str = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = str.length - 1; i >= 0; i--) {
            if (i == 0) {
                sb.append(str[i]);
                break;
            }
            sb.append(str[i]).append(" ");
        }
        return sb.toString();
    }

    public String reverseWords2(String s) {
        // the sky is blue   =>  blue is sky the
        // "  hello world  "  => "world hello"
        // "a good   example"  => "example good a"
        // 去除字符串首尾的空格与每个单词之间多余的空格
        StringBuilder sb = removeExtraSpace(s);
        // 将整个字符串翻转
        reverseString(sb, 0, sb.length() - 1);
        reverseWord(sb);
        return sb.toString();
    }

    @Test
    public void removeExtraSpaceTest() {
        String s = "   he  is a good girl.      ";
        System.out.println(removeExtraSpace(s));
        System.out.println(reverseWords2(s));
    }

    private StringBuilder removeExtraSpace(String s) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ' || s.charAt(end) == ' ') {
            if (s.charAt(start) == ' ') {
                start++;
            }
            if (s.charAt(end) == ' ') {
                end--;
            }
        }
        while (start <= end) {
            if (s.charAt(start) != ' ') {
                sb.append(s.charAt(start++));
            } else {
                sb.append(s.charAt(start++));
                while (s.charAt(start) == ' ') {
                    start++;
                }
            }
        }
        return sb;
    }

    private void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char c = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, c);
            start++;
            end--;
        }
    }

    private void reverseWord(StringBuilder sb) {
        int start = 0;
        int end = 0;
        int len = sb.length();
        while (end < len) {
            if (sb.charAt(end) == ' ') {
                reverseString(sb, start, end - 1);
                start = end + 1;
                end = end + 1;
            } else {
                end++;
            }
        }
        reverseString(sb, start, end - 1);
    }

    @Test
    public void reverseWordTest1() {
        String s = "  hello world  ";
        System.out.println(Arrays.toString(s.split("\\s+")));
    }


    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * leetCode 17
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        char[][] chars = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        char[] digs = digits.toCharArray();
//        List<Character> tmp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        letterCombinationsForce(list, chars, digs, 0, sb);
        return list;
    }

    @Test
    public void letterCombinationsTest() {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }

    private void letterCombinationsForce(List<String> list, char[][] chars, char[] digs, int index, StringBuilder sb) {
        if (index == digs.length) {
            list.add(sb.toString());
            return;
        }
        char[] chs = chars[digs[index] - 50];
        for (char ch : chs) {
            sb.append(ch);
            letterCombinationsForce(list, chars, digs, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public String removeDuplicates(String s) {
        char[] stack = new char[s.length()];
        char[] chs = s.toCharArray();
        int index = -1;
        for (int i = 0; i < chs.length; i++) {
            if (index == -1) {
                stack[++index] = chs[i];
            } else {
                if (stack[index] == chs[i]) {
                    index--;
                } else {
                    stack[++index] = chs[i];
                }
            }
        }
        return index == -1 ? "" : new String(stack, 0, index + 1);
    }

    @Test
    public void removeDuplicatesTest() {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }

    /**
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * leetCode 131
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        char[] src = s.toCharArray();
        List<String> tmp = new ArrayList<>();
        partitionForce(list, src, 0, 0, tmp);
        return list;
    }

    @Test
    public void partitionTest() {
        String s = "abababa";
        System.out.println(partition(s));
    }

    private void partitionForce(List<List<String>> list, char[] src, int index, int len, List<String> tmp) {
        if (len == src.length) {
            list.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = index; i < src.length; i++) {
            for (int l = 1; l + i <= src.length; l++) {
                String s = new String(src, i, l);
                if (!isPalindrome1(s)) {
                    continue;
                }
                tmp.add(s);
                len += s.length();
                partitionForce(list, src, i + l, len, tmp);
                len -= s.length();
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public boolean isPalindrome1(String s) {
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
        }
        return true;
    }

    public int evalRPN(String[] tokens) {

        return 0;
    }

    public String[] mid2Suffix(String str) {
        // 准备两个栈，s1 操作数栈   s2 运算栈
        // 从左至右依次扫描， 若扫描到数字，直接入栈， 若扫描到运算符， 若运算栈为空，或栈顶元素为(, 直接入栈
        // 若不为空，比较栈顶运算符的优先级，若高于栈顶元素，直接入栈， 否则弹出元素，并入操作数栈s1
        // 若为括号, 若为(, 直接入运算栈，
        // 若为)括号， 弹出元素入操作数栈，直至遇到(
        Stack<String> s1 = new Stack<>();
        Stack<String> s2 = new Stack<>();
        Pattern compile = Pattern.compile("\\+|-|\\*|/");

        int index = 0;
        int len = str.length();
        char[] chars = str.toCharArray();
        while (index < len) {
            String c = String.valueOf(chars[index]);
            if (compile.matcher(c).matches()) {
                if (s2.isEmpty() || s2.peek().equals("(")) {
                    s2.push(c);
                    index++;
                    continue;
                }
                int level1 = getExprLevel(c);
                int level2 = getExprLevel(s2.peek());
                if (level1 > level2) {
                    s2.push(c);
                } else {

                }
                index++;
            }
        }
        return null;
    }

    private int getExprLevel(String c) {
        switch (c) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
        }
        return 0;
    }

    /**
     * 2864. 最大二进制奇数
     *
     * @param s
     * @return
     */
    public String maximumOddBinaryNumber(String s) {
        char[] chs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '1') {
                count++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chs.length - 1; i++) {
            if (count > 1) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            count--;
        }
        sb.append("1");
        return sb.toString();
    }


    @Test
    public void maximumOddBinaryNumber() {

    }


    /**
     * 8. 字符串转换整数 (atoi)
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * 函数 myAtoi(string s) 的算法如下：
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        char[] chs = s.trim().toCharArray();
        int up = 1;
        boolean flag = false;
        long num = 0;
        char begin = chs[0];
        if (!((begin >= '0' && begin <= '9') || begin == '-')) {
            return 0;
        }
        for (char ch : chs) {
            if (flag) {
                if (ch < '0' || ch > '9') {
                    break;
                }
                int n = ch - 48;
                num = num * 10 + n;
                if (num >= Integer.MAX_VALUE) {
                    break;
                }
            } else {
                if (ch == '-') {
                    up = -1;
                } else if (ch > '0' && ch <= '9') {
                    flag = true;
                    num = ch - 48;
                }
            }
        }
        num *= up;
        return num > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) (num < Integer.MIN_VALUE ? Integer.MIN_VALUE : num);
    }

    @Test
    public void myAtoiTest() {
        String str = " -78923423523542352 words and 987";
        System.out.println(myAtoi(str));
    }

    @Test
    public void vlanStrTest() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 7, 8, 10);
        int[] arr = {0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0};
        System.out.println(vlanStr2(arr));
    }

    public String vlanStr(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Integer first = list.get(0);
        Integer pre = list.get(0);
        // 是否有连续数据
        for (int i = 1; i < list.size(); i++) {
            Integer cur = list.get(i);
            if (pre + 1 == cur) {
                pre = cur;
            } else {
                if (first == pre) {
                    sb.append(first).append(",");
                } else {
                    sb.append(first).append("-").append(pre).append(",");
                }
                first = cur;
                pre = cur;
            }
        }
        if (first == pre) {
            sb.append(first).append(",");
        } else {
            sb.append(first).append("-").append(pre).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String vlanStr2(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 1) {
                sb.append(",");
                sb.append(i);
                // 判断下一个元素是否连续
                if (++i < arr.length && arr[i] == 1) {
                    sb.append("-");
                    while (arr[++i] == 1) {

                    }
                    sb.append(i - 1);
                }
            }
        }
        String result = sb.toString();
        if (result.indexOf(",") == 0) {
            return result.substring(1);
        }
        return result;
    }

    @Test
    public void jumpGameTest() {
//        int[] arr = {2,3,1,1,4};
        int[] arr = {1, 2, 3};
        System.out.println(jumpGame(arr));
    }

    public boolean jumpGame(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (max >= i && nums[i] + i > max) {
                max = nums[i] + i;
            }
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return max >= nums.length - 1;
    }

    @Test
    public void jumpGameIItest() {
        int[] nums = {2, 3, 4};
        System.out.println(jumpGameII(nums));
    }

    public int jumpGameII(int[] nums) {
        int step = 0;
        int target = nums.length - 1;
        int init = 0;
        while (init < nums.length - 1) {
            step++;
            int maxStep = nums[init] + init;
            if (maxStep >= target) {
                return step;
            }
            init = getMaxIndex(init + 1, maxStep, nums);
        }
        return step;
    }

    public int getMaxIndex(int start, int end, int[] nums) {
        int max = start;
        int dis = nums[start];
        for (int i = start; i <= end; i++) {
            if (nums[i] + i > dis) {
                max = i;
                dis = nums[i] + i;
            }
        }
        return max;
    }

    public int jumpGameII2(int[] nums) {
        int end = 0;
        int step = 0;
        int maxPos = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(maxPos, nums[i] + i);
            if (i == end) {
                end = maxPos;
                step++;
            }
        }
        return step;
    }

    @Test
    public void jumpGameIITest() {
//        int[] nums = {1, 0};
//        int[] nums = {1, 0};
//        int[] nums = {1, 0};
        int[] nums = {4, 1, 1, 3, 1, 1, 1};
//        int[] nums = {0};
        System.out.println(jumpGameII(nums));
    }


    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) {
            return true;
        }
        boolean[] bool = new boolean[arr.length];
        return canReachRecursion(arr, start, bool);
    }

    public boolean canReachRecursion(int[] nums, int index, boolean[] bool) {
        if (index < 0 || index >= nums.length) {
            return false;
        }
        if (bool[index]) {
            return false;
        }
        if (nums[index] == 0) {
            return true;
        }
        bool[index] = true;
        boolean b1 = canReachRecursion(nums, index + nums[index], bool);
        boolean b2 = canReachRecursion(nums, index - nums[index], bool);
        return b1 || b2;
    }

    @Test
    public void canReachTest() {
//        int[] nums = {4,2,3,0,3,1,2};
        int[] nums = {3, 0, 2, 1, 2};
        int start = 0;
        System.out.println(canReach(nums, start));
    }

    public int maxResult2(int[] nums, int k) {
        int[][] dp = new int[k + 1][nums.length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        return maxResultRecursion(nums, k, 0, dp);
    }

    public int maxResult(int[] nums, int k) {
        int[][] dp = new int[k + 1][nums.length];
        int len = nums.length;
        for (int i = 0; i < dp.length; i++) {
            dp[i][len - 1] = nums[len - 1];
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = 1; j <= k && i + j < len; j++) {
                dp[j][i] = Math.max(dp[j][i] + nums[i], dp[j][i + j] + nums[i]);
            }
        }
        return dp[k][0];
    }


    public int maxResultRecursion(int[] nums, int k, int index, int[][] dp) {
        if (index == nums.length - 1) {
            return nums[index];
        }
        if (dp[k][index] != Integer.MIN_VALUE) {
            return dp[k][index];
        }
        int p1 = Integer.MIN_VALUE;
        for (int i = 1; i <= k && index + i < nums.length; i++) {
            int res = maxResultRecursion(nums, k, index + i, dp) + nums[index];
            p1 = Math.max(p1, res);
        }
        dp[k][index] = p1;
        return p1;
    }

    public int maxResult1(int[] nums, int k) {
        int result = nums[0];
        int start = 1;
        while (start < nums.length) {
            int maxIndex = getMaxResult(nums, start, start + k - 1);
            result += nums[maxIndex];
            start = maxIndex + 1;
        }
        return result;
    }

    @Test
    public void maxResultTest() {
//        int[] nums = {1, -1, -2, 4, -7, 3};
//        int[] nums = {1, -1, 2, -1};
        int[] nums = {10, -5, -2, 4, 0, 3};
//        int[] nums = {1,-5,-20,4,-1,3,-6,-3};
//        int[] nums = {-5582, -5317, 6711, -639, 1001, 1845, 1728, -4575, -6066, -7770, 128, -3254, 7281, 3966, 6857, 5477, 8968, -1771, 9986, -6267, 9010, -764, 8413, -8154, 1087, -1107, 4183, 3033, 58, 659, 4625, 2825, 5031, 6811, 5657, 3229, 8597, -5772, 8869, 5723, 2960, 4040, 7222, 4841, -1014, 581, -2830, 3881, -3800, 577, -7396, -611, -6944, 8461, 3294, 6297, 9713, -2246, -3441, 3831, -5754, 6716, 6040, -6715, 5763, 8611, 5412, -7630, 6216, 260, 2595, 6852, -8956, 2101, 6722, 1579, 3820, 7827, -3369, 7144, 1974, 7310, -5369, -6755, 3010, 5789, 1563, -3330, 5373, -2770, 4503, -4065, 8177, -3333, -4726, -2131, 2763, 9012, -4755, 2382, 3642, -5284, -7174, -9815, 6392, 9729, -1943, -8749, 5343, 1036, 8508, 1484, 919, 4225, 3733, 8036, -6346, -2088, 475, 9378, 4271, -5906, 9327, 9399, -1582, 3845, 3499, -8912, -4671, -1143, -5081, -1621, -1287, 5995, 4963, 5071, 5118, -1966, -6249, 663, -2296, -8148, -4668, -6919, 334, -6609, 2888, -4161, 118, -1867, 5629, 8588, -5325, -7853, -4868, -1487, -6544, -9697, -7038, 6422, -5545, 3376, -8656, 8800, -7698, -2928, 2279, -9739, 4198, 6236, -9087, 9010, -9894, 2145, 7353, -92, 3205, 5431, 5913, 1619, -250, 4728, -7164, -5619, -4721, -9284, -9645, 146, 7131, -6501, 4261, 2016, 2880, 4944, -8768, -6339, -3574, 539, 4633, 9188, 7227, -1549, 9271, 7110, 5706, 4968, -1275, 5545, -5844, -1985, 9560, 1560, 4630, 3169, 6076, -9433, 7007, 9927, -8385, -4557, -114, 9543, 2884, 8978, -6447, 3664, -7499, -4643, -5993, -5321, 3250, -2945, 6216, -1606, 5569, 7326, -6027, 9723, -6997, -543, -8298, -4647, 2563, 1493, 9574, 1087, -9433, -7749, -7159, -2682, 6626, 2787, -2845, -7907, -223, -8142, -5403, -3460, -2534, 5289, 999, 9404, -1958, 641, 4669, -2892, -2921, -7001, -1403, -2353, -7976, -5885, 4958, -8117, 8785, -654, 5918, 5533, 8704, 5827, -7478, -3696, 2640, 1612, -500, 5694, -1973, 5308, 5272, 3358, 9190, 4648, -7836, 658, -3407, 6733, 1061, -2010, -2707, -1920, 1272, 3944, -6537, -6090, -7429, -640, 836, 1904, -4031, 814, -1886, 8040, -8312, -9407, -1395, -9944, -2074, -6814, 2672, 1360, 8990, 5465, -2131, 3838, 799, -3472, 1086, -583, 6302, 3032, 9138, -7778, 4538, -5337, 2087, 2870, -3005, 3401, 122, -819, -8074, 9630, -698, 5326, 2650, -9355, 6487, 3801, -3209, 8293, 662, -8318, -7863, -3814, -2557, -5685, -7952, 6224, -7010, 2935, 5557, -1287, 9528, -9218, -5108, -2085, 17, 4870, -8686, -8854, -9657, 8848, -1883, -4535, 83, 9711, 4593, -3440, -6938, 3407, -6894, -6213, -883, 4552, -731, 1485, -7740, -3300, 3897, -7629, -4076, 7589, 3142, -1010, 2466, -592, -391, 3961, -7049, 7654, 5758, 6983, 6048, -4369, -5878, 3756, 2940, 9149, 8625, 8937, 5706, 6658, 9213, -5226, 284, -4524, -1577, -5296, 6423, 9977, -1805, 5462, 7587, 476, -6424, 976, -3925, 8819, 1821, 3603, -842, -9618, -7130, -6253, 2562, -7596, 3522, 6282, -3801, -3896, 6924, 441, 5944, 8535, 1253, -6154, 6872, -9548, -5358, 1604, 9593, -9256, -701, 1023, -1446, -1307, -6809, 9542, 3673, 1813, 8717, -6847, -5289, 5222, -7266, 4231, 218, -9633, -4696, 5494, 9681, 1173, -4606, 2174, -1155, -8595, -3640, -6550, -7003, 4244, -2543, 5241, 2831, 2690, 8950, -6609, -9724, 7562, -4096, 8878, 9962, 7179, -1170, 7826, -146, -2759, -5249, 253, 6206, 3205, -7708, 9448, 4622, 9260, -2853, 2486, 122, -8880, -769, -8922, 648, 7358, -6503, -6382, -4260, 3988, -5107, -6363, 2415, 8563, -9070, -5026, 2078, -2558, -2027, -7489, -4978, 5024, 4155, -9737, -221, 9930, -9472, 1052, -268, 6221, 2726, -1310, -8708, 3482, -5488, -6506, 5389, -7048, 553, -886, 2752, 85, -3938, 5940, -5112, 5855, -7295, 3735, 2657, 3269, 6231, 4771, 3229, -2009, -5748, 7256, 746, -4301, 752, -241, -6151, -2390, 9911, 825, -7679, -4960, -7224, -2739, -566, -5770, 6774, 6243, 3166, -783, -4303, -9016, 5555, -1866, -536, 8872, -3927, 4269, -3807, 1933, 9972, 981, 9256, 6857, -208, 3645, -3725, 5961, 1105, 6320, -4702, -8419, -4904, -4935, 8378, -2994, 5831, 5296, 4730, -9170, -4229, -3911, -160, 8757, -5301, -3775, 1121, 9434, -9880, 2689, 2340, -7879, 3667, -5219, -6116, -1670, 7595, 6900, 3990, 4444, 6385, -2924, 8968, -2673, -6182, 7503, 5209, 6030, 802, -3464, 1922, -8187, 1617, 4769, -4866, -3518, 5830, 3862, -7512, 5236, -5164, 6324, -5107, 6864, -7364, -1375, 5762, -275, 4975, -7448, 5719, -3162, -1546, -2776, -9411, -1845, -4913, -3474, 2550, 5643, -5527, 2946, 7158, 1938, 5125, -8015, 2475, -1461, -4900, -5151, -4031, 9362, 8571, 9815, -8438, -6519, 1980, -8031, 9615, 7079, -3573, -883, 4217, 1079, 5918, 1767, 8670, -5651, -6625, 1057, 7897, -7104, -4186, 851, -6333, -4108, -3250, 7899, 9628, -6904, -3939, 4587, 1227, 3813, -7449, -7692, -8098, -9813, 8862, -2888, -1048, -3564, 3074, 1437, -2291, 3974, 3164, 4921, -8958, 9007, -3938, 2042, 7454, -910, -998, -4450, -1103, -237, 8182, -1391, -4255, -3482, -2918, 4053, 2280, -7403, 4319, -9457, 7157, -6315, -7533, 6309, 2211, -9145, 443, 4255, -8847, -5557, -9089, 1752, -5784, -2399, -8296, -8400, 8170, 4628, -4583, 937, -7067, -3503, -549, -1194, 1576, 5004, -6963, -8837, 5567, 870, 3954, 5489, -8949, -7673, 8542, -9040, -7689, -4171, -889, 5552, -6836, -4393, 513, 3177, 6664, -5646, 2492, 9421, -342, 2570, 8816, 2869, -6820, -3389, -1903, -3332, 138, 6618, 293, -9130, 3503, -2327, -9728, 7632, 5881, 540, 9678, -7629, 8804, -2816, 7205, 7473, -5518, 7311, 3457, 9066, -1224, 2097, 7857, 6612, 186, 6759, -4516, -3491, -8268, -8928, -7412, 7162, 6274, 5463, 2157, -4131, -7061, -8476, -5584, 7300, -4348, -5940, -8592, -302, -5817, 3151, -4124, 1694, -5114, -3252, -2319, -2157, -293, 7724, -5673, 6105, 9535, 4333, 6353, 1290, 8710, -5035, 8995, -5865, 9746, 4708, -6387, -8937, 3096, -9716, -7124, 2531, -660, -4619, -8035, 3747, -7821, 8793, -727, 8242, 4957, -7175, 4064, -9911, 4995, 9725, 1634, -4275, 788, -4920, 3831, -3525, -4467, 2909, -1200, 5377, -4905, -3077, -1763, 4443, -3518, 3134, -5595, 5409, 5943, 6757, 3485, 2883, -9261, -7221, 654, 2001, -926, 7840, -5568, 2715, -7053, -2082, -2005, 7607, -9511, 7545, 7564, 2380, -7257, 1449, -3918, -3240, -1928, -6555, -4784, 1550, 2745, -5316};
        int start = 2;
        System.out.println(maxResult(nums, start));
    }

    public int getMaxResult(int[] nums, int start, int end) {
        int max = start;
        int maxDis = nums[start];
        for (int i = start; i < nums.length && i <= end; i++) {
            if (nums[i] > 0) {
                return i;
            }
            if (nums[i] > maxDis) {
                maxDis = nums[i];
                max = i;
            }
        }
        return max;
    }

    @Test
    public void maxSlidingWindowTest() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0 || k > nums.length) {
            return new int[0];
        }
        int len = nums.length;
        int[] res = new int[len - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int index = 0;
        for (int R = 0; R < len; R++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[R]) {
                deque.pollLast();
            }
            deque.addLast(R);
            if (R - k == deque.peekFirst()) {
                deque.pollFirst();
            }
            if (R + 1 >= k) {
                res[index++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        int left = 0;
        int right = k - 1;
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int winMax = maxRes(nums, left, right);
        int idx = 0;
        while (left < len - k + 1) {
            res[idx++] = winMax;
            left++;
            winMax = Math.max(winMax, nums[Math.min(left + k - 1, len - 1)]);
        }
        return res;
    }

    public int maxRes(int[] nums, int start, int end) {
        int max = nums[start];
        while (start <= end) {
            max = Math.max(max, nums[start]);
            start++;
        }
        return max;
    }

    public boolean checkInclusion(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 > l2) {
            return false;
        }
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        for (int i = 0; i < l1; i++) {
            c1[ch1[i] - 'a']++;
            c2[ch2[i] - 'a']++;
        }
        if (Arrays.equals(c1, c2)) {
            return true;
        }
        for (int i = l1; i < l2; i++) {
            c2[ch2[i] - 'a']++;
            c2[ch2[i - l1] - 'a']--;
            if (Arrays.equals(c1, c2)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkInclusion1(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        String src = getNewStr(s1);
        int len = s1.length();
        for (int i = 0; i <= s2.length() - len; i++) {
            String s = s2.substring(i, i + len);
            if (getNewStr(s).equals(src)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testCheckInClusion() {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));
    }

    public static String getNewStr(String s) {
        char[] chs = s.toCharArray();
        Arrays.sort(chs);
        StringBuilder sb = new StringBuilder();
        for (char ch : chs) {
            sb.append(ch);
        }
        return sb.toString();
    }


    @Test
    public void minWindowTest() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        int l1 = s.length();
        int l2 = t.length();
        if (l2 > l1) {
            return "";
        }
        int[] cn = new int[52];
        Set<Character> set = new HashSet<>();
        char[] c1 = t.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            set.add(c1[i]);
            cn[getIndex(c1[i])]++;
        }
        char[] c2 = s.toCharArray();
        for (int w = l2; w <= l1; w++) {
            int[] sn = new int[52];
            int i = 0;
            for (; i < l1; i++) {
                if (i >= w) {
                    if (Arrays.equals(cn, sn)) {
                        return s.substring(i - w, i);
                    }
                    if (set.contains(c2[i - w])) {
                        sn[getIndex(c2[i - w])]--;
                    }
                }
                if (set.contains(c2[i])) {
                    sn[getIndex(c2[i])]++;
                }
            }
            if (Arrays.equals(cn, sn)) {
                return s.substring(i - w, i);
            }
        }
        /*for (int i = 0; i < c2.length; i++) {
            int left = i;
            int right = i;
            while (right < c2.length) {
                if (set.contains(c2[right])) {
                    sn[getIndex(c2[right])]++;
                }
                if (right - left + 1 >= l2) {
                    if (Arrays.equals(cn, sn)) {
                        return s.substring(left, right + 1);
                    }
                }
                right++;
            }
            if (set.contains(c2[left])) {
                sn[getIndex(c2[left])]--;
            }
        }*/
        return "";
    }

    public static int getIndex(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 26;
        }
        return 0;
    }

    @Test
    public void lengthOfLongestSubstringTest() {
//        String s = "abcabcbb";
        String s = "dvdf";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int left = 0;
        int end = 0;
        int res = 0;
        int max = Integer.MIN_VALUE;
        while (left < s.length() && end < s.length()) {
            char c = s.charAt(end);
            if (!set.contains(c)) {
                set.add(c);
                res++;
                end++;
            } else {
                set.clear();
                max = Math.max(res, max);
                res = 0;
                left++;
                end = left;
            }
        }
        return Math.max(res, max);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        Integer max = Integer.MIN_VALUE;
        Integer left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    /**
     * 暴力
     *
     * @param nums
     * @return
     */
    public int longestSubarray1(int[] nums) {
        int[] other = new int[nums.length - 1];
        int max = 0;
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                flag = true;
                int index = 0;
                for (int j = 0; j < nums.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    other[index++] = nums[j];
                }
                max = Math.max(max, getLongestSub1(other));
            }
        }
        return flag ? max : nums.length - 1;
    }

    @Test
    public void longestSubarray1Test() {
        int[] nums = {1, 1, 0, 1};
//        int[] nums = {0, 1, 1, 1, 0, 1, 1, 0, 1};
//        int[] nums = {1,1,1};
        System.out.println(longestSubarray(nums));
    }

    public int longestSubarray(int[] nums) {
        int l = 0;
        int r = 0;
        int zero = 0;
        int max = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                zero++;
            }
            while (zero > 1) {
                if (nums[l] == 0) {
                    zero--;
                }
                l++;
            }
            max = Math.max(max, r - l);
        }
        return max;
    }

    public int getLongestSub1(int[] arr) {
        int left = 0;
        int max = 0;
        for (; left < arr.length; ) {
            if (arr[left] == 1) {
                int right = left;
                while (right < arr.length && arr[right] == 1) {
                    right++;
                }
                max = Math.max(max, right - left);
                left = right;
            } else {
                left++;
            }
        }
        return max;
    }

    @Test
    public void maxProfitTest() {
        int[] nums = {7, 6, 4, 3, 1};
        System.out.println(maxProfit(nums));
    }


    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int[] nums = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++) {
            nums[i - 1] = prices[i] - prices[i - 1];
        }
        int max = 0;
        int pre = nums[0];
        max = Math.max(pre, max);
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int pre = nums[0];
        int max = pre;
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(nums[i], nums[i] + pre);
            max = Math.max(max, pre);
        }
        return max;
    }


    public int totalFruit1(int[] fruits) {
        if (fruits == null || fruits.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        int max = 1;
        for (int L = 0; L < fruits.length; L++) {
            int R = L + 1;
            int left = L;
            int count = 0;
            set.add(fruits[L]);
            while (R < fruits.length) {
                if (count < 1 && fruits[R] != fruits[L]) {
                    count++;
                    left = R;
                }
                if (!set.contains(fruits[R])) {
                    set.add(fruits[R]);
                }
                if (set.size() > 2) {
                    break;
                }
                R++;
            }
            set.clear();
            max = Math.max(R - L, max);
            if (count == 1) {
                L = left - 1;
            }
        }
        return max;
    }

    @Test
    public void testFruitsTest() {
        int[] fruits = {1, 0, 1, 4, 1, 4, 1, 2, 3};
        System.out.println(totalFruit(fruits));
    }

    public int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) {
            return 0;
        }
        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        for (int i = 0; i < fruits.length; i++) {
            map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    @Test
    public void minSubArrayLenTest() {
//        int[] nums = {1, 2, 3, 4, 5};
//        int[] nums = {2, 3, 1, 2, 4, 3};
        int[] nums = {10, 5, 13, 4, 8, 4, 5, 11, 14, 9, 16, 10, 20, 8};
        int target = 80;
        System.out.println(minSubArrayLen(target, nums));
    }

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int win = 0;
        int L = 0;
        int min = Integer.MAX_VALUE;
        boolean flag = true;
        int R = 0;
        for (; R < nums.length; R++) {
            win += nums[R];
            if (win >= target) {
                flag = false;
                while (win >= target) {
                    min = Math.min(R - L + 1, min);
                    win -= nums[L];
                    L++;
                }
            }
        }
        return flag ? 0 : Math.min(R - L + 1, min);
    }

    public int minSubArrayLen1(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = nums.length;
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    flag = false;
                    min = Math.min(min, j - i + 1);
                }
            }
        }
        return flag ? 0 : min;
    }

    @Test
    public void longestOnesTest() {
//        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 3;
        System.out.println(longestOnes(nums, k));
    }

    public int longestOnes(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int zero = 0;
        int L = 0;
        int R = 0;
        int max = Integer.MIN_VALUE;
        for (; R < nums.length; R++) {
            if (nums[R] == 0) {
                zero++;
            }
            if (zero <= k) {
                max = Math.max(max, R - L + 1);
            } else {
                while (zero > k) {
                    if (nums[L] == 0) {
                        zero--;
                    }
                    L++;
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    @Test
    public void maximumUniqueSubarrayTest() {
//        int[] nums = {4, 2, 4, 5, 6};
//        int[] nums = {5,2,1,2,5,2,1,2,5};
        int[] nums = {1, 1, 1};
        System.out.println(maximumUniqueSubarray(nums));
    }

    public int maximumUniqueSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int L = 0;
        int R = 0;
        int win = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (; R < nums.length; R++) {
            if (map.containsKey(nums[R])) {
                Integer left = map.get(nums[R]);
                while (L <= left) {
                    win -= nums[L];
                    L++;
                }
            }
            map.put(nums[R], R);
            win += nums[R];
            max = Math.max(max, win);
        }
        return max;
    }

    @Test
    public void minimumDifferenceTest() {
        int[] nums = {87063,61094,44530,21297,95857,93551,9918};
        int k = 6;
        System.out.println(minimumDifference(nums, k));
    }

    public int minimumDifference(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (k == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - k + 1 ; i++) {
            res = Math.min(res, nums[i + k - 1] - nums[i]);
        }
        return res;
    }

    @Test
    public void maxAreaTest() {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(arr));
    }

    public int maxArea(int[] height) {
        int left= 0;
        int max = 0;
        int right = height.length-1;
        while (left < right) {
            int res = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, res);
            if (height[left] < height[right]) {
                left++;
            } else{
                right--;
            }
        }
        return max;
    }

}

