package com.huawei.algorithm.leetcode.strPractice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

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
}
