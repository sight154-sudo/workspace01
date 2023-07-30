package com.huawei.algorithm.leetcode.recursionPractice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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
        getSubStrRecursion(chs, list, idx+1, str);
        getSubStrRecursion(chs, list, idx+1, str+chs[idx]);
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
        reverseStackRecursion(stack);
        return stack;
    }

    private void reverseStackRecursion(Stack stack) {
        if (stack.isEmpty()) {
            return;
        }
        Object obj = stack.pop();
        reverseStackRecursion(stack);
        stack.push(obj);
    }

}
