package com.huawei.algorithm.od;

import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @author king
 * @date 2024/6/2-19:31
 * @Desc
 */
public class Main {

    public static void main01(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        String s = sc.nextLine();
        String[] s1 = s.split(" ");
        for (String s2 : s1) {
            list.add(Integer.valueOf(s2));
        }
        Map<Integer, Integer> map = new TreeMap<>();
        List<Integer> result = new ArrayList<>();
        int max = -1;
        for (int i = 0; i < list.size(); i++) {
            Integer num = list.get(i);
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            Integer count = map.get(num);
            if (count + 1 > max) {
                result.clear();
                result.add(num);
            } else if (count + 1 == max) {
                result.add(num);
            }
            max = Math.max(max, count + 1);
            map.put(num, count + 1);
        }
        int[] arr = result.stream().mapToInt(a -> Integer.valueOf(a)).toArray();
        Arrays.sort(arr);
        int len = arr.length;
        if (len % 2 == 0) {
            System.out.println((arr[len / 2] + arr[len / 2 - 1]) / 2);
        } else {
            System.out.println(arr[len / 2]);
        }
    }

    public static void main03(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        String L = sc.nextLine();
        if (S.length() > L.length()) {
            System.out.println(-1);
            return;
        }
        int i = 0;
        int j = 0;
        boolean flag = false;
        while (i < S.length() && j < L.length()) {
            if (S.charAt(i) == L.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
            if (i == S.length() && S.charAt(i - 1) == L.charAt(j - 1)) {
                flag = true;
            }
        }
        if (flag) {
            System.out.println(j - 1);
        } else {
            System.out.println(-1);
        }
    }

    // abb
    // abcdbde

    public int findSubStr(String s, String l) {
        return 0;
    }

    @Test
    public void test11() {
        String s = "a  we 324 werfewe";
        System.out.println(s.replaceAll("\\s+", ""));
        BigDecimal b1 = new BigDecimal("35");
        BigDecimal b2 = new BigDecimal("8");
        System.out.println(b1.divide(b2));
        System.out.println(35 / 8);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s = s.replaceAll("\\s+", "");
        char[] chs = s.toCharArray();
        int i = 0;
        Stack<String> stack = new Stack<>();
        Stack<String> fuhao = new Stack<>();
        int num = 0;
        boolean isNum = true;
        while (i < chs.length) {
            if (chs[i] >= '0' && chs[i] <= '9') {
                isNum = true;
                num = num * 10 + chs[i] - 48;
            } else if (chs[i] == '+' || chs[i] == '-' || chs[i] == '*' || chs[i] == '/') {
                if (isNum) {
                    stack.add(String.valueOf(num));
                    isNum = false;
                    num = 0;
                }
                String expr1 = chs[i] + "";
                if (fuhao.isEmpty()) {
                    fuhao.add(expr1);
                } else {
                    String expr = fuhao.peek();
                    if (expr.equals("(") || getInt(expr1) >= getInt(expr)) {
                        fuhao.add(expr1);
                    } else {
                        String s1 = stack.pop();
                        String s2 = stack.pop();
                        String s3 = compute(s2, s1, expr);
                        if (s3.equals("ERROR")) {
                            System.out.println("ERROR");
                            return;
                        }
                        stack.add(s3);
                    }
                }
            } else if (chs[i] == '(') {
                fuhao.add(chs[i] + "");
            } else if (chs[i] == ')') {
                if (isNum) {
                    stack.add(String.valueOf(num));
                    isNum = false;
                    num = 0;
                }
                while (!fuhao.peek().equals("(")) {
                    String expr = fuhao.pop();
                    String s1 = stack.pop();
                    String s2 = stack.pop();
                    String s3 = compute(s2, s1, expr);
                    if (s3.equals("ERROR")) {
                        System.out.println("ERROR");
                        return;
                    }
                    stack.add(s3);
                }
                fuhao.pop();
            }
            i++;
        }
        if (isNum) {
            stack.add(String.valueOf(num));
        }
        while (!stack.isEmpty() && !fuhao.isEmpty()) {
            String s1 = stack.pop();
            String s2 = stack.pop();
            String expr = fuhao.pop();
            String s3 = compute(s2, s1, expr);
            if (s3.equals("ERROR")) {
                System.out.println("ERROR");
                return;
            }
            stack.add(s3);
        }
        String pop = stack.pop();
        if (pop.contains("/")) {
            String[] split = pop.split("/");
            Integer feiZi = Integer.valueOf(split[0]);
            Integer feimu = Integer.valueOf(split[1]);
            if ((feimu > 0 && feiZi > 0)) {
                System.out.println(pop);
            } else if (feimu < 0 && feiZi > 0) {
                System.out.println((feiZi*-1) + "/" + (feimu * -1));
            } else if (feimu < 0 && feiZi < 0) {
                System.out.println((feiZi * -1) + "/" + (feimu) * -1);
            } else {
                System.out.println(pop);
            }
        } else {
            System.out.println(pop);
        }
    }

    public static String compute(String s1, String s2, String expr) {
        Integer a1 = Integer.valueOf(s1);
        if (s2.contains("/")) {
            String[] split = s2.split("/");
            Integer feizi = Integer.valueOf(split[0]);
            Integer feimu = Integer.valueOf(split[1]);
            if (expr.equals("+")) {
                return ((a1) * feimu + feizi) + "/" + feimu;
            } else if (expr.equals("-")) {
                return (feizi - a1 * feimu) + "/" + feimu;
            } else if (expr.equals("*")) {
                if (a1 == 0) {
                    return "0";
                }
                feizi *= a1;
                if (feizi % feimu == 0) {
                    return String.valueOf(feizi / feimu);
                }
                return feizi + "/" + feimu;
            } else {
                if (a1 == 0) {
                    return "ERROR";
                }
                feimu *= a1;
                if (feizi % feimu == 0) {
                    return String.valueOf(feizi / feimu);
                }
                return feizi + "/" + feimu;
            }
        } else {
            Integer a2 = Integer.valueOf(s2);
            if (expr.equals("+")) {
                return String.valueOf(a1 + a2);
            } else if (expr.equals("-")) {
                return String.valueOf(a1 - a2);
            } else if (expr.equals("*")) {
                return String.valueOf(a1 * a2);
            } else {
                if (a1 == 0) {
                    return "ERROR";
                }
                if (a1 % a2 != 0) {
                    return a1 + "/" + a2;
                } else {
                    return a1 + "/" + a2;
                }
            }
        }
    }


    public static int getInt(String s) {
        if (s.equals("+") || s.equals("-")) {
            return 0;
        } else {
            return 1;
        }
    }

}
