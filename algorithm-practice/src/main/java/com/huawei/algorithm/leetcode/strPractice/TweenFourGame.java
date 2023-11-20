package com.huawei.algorithm.leetcode.strPractice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author king
 * @date 2023/9/5-22:53
 * @Desc
 */
public class TweenFourGame {

    public static String[] oper = {"+", "-", "*", "/"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    }

    @Test
    public void tweenFourTest() {
        double[] arr = {10, 2, 4, 4};
        Arrays.sort(arr);
        List<List<Double>> list = new ArrayList<>();
        List<Double> tmp = new ArrayList<>();
        boolean[] visited = new boolean[arr.length];
        fullSortForce(arr, tmp, list, visited);
        for (List<Double> doubles : list) {
//            System.out.println(doubles);
        }
//        fullSort(arr);
        for (List<Double> doubles : list) {
            calc(doubles.get(0), doubles.get(1), doubles.get(2), doubles.get(3));
        }
    }

    public void fullSortForce(double[] arr, List<Double> list, List<List<Double>> result, boolean[] visited) {
        if (list.size() == arr.length) {
            List<Double> tmp = new ArrayList<>(list);
            result.add(tmp);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (visited[i] || (i > 0 && arr[i-1] == arr[i] && !visited[i-1])) {
                continue;
            }
            list.add(arr[i]);
            visited[i] = true;
            fullSortForce(arr, list, result, visited);
            list.remove(list.size()-1);
            visited[i] = false;
        }
    }

    public boolean fullSort(double[] arr) {
        int n = arr.length;
        List<List<Double>> result = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (j == k || i == k) {
                        continue;
                    }
                    for (int l = 0; l < n; l++) {
                        if (k == l || i == l || j == l) {
                            continue;
                        }
                        List<Double> list = new ArrayList<>();
                        list.add(arr[i]);
                        list.add(arr[j]);
                        list.add(arr[k]);
                        list.add(arr[l]);
                        result.add(list);
                        flag |= calc(arr[i], arr[j], arr[k], arr[l]);
                    }
                }
            }
        }
        System.out.println(result);
        return flag;
    }

    public boolean calc(double a, double b, double c, double d) {
        // 四个数字与四个运算符 相互运算
        boolean flag = false;
        for (String op1 : oper) {
            double r1 = getResult(a, b, op1);
            for (String op2 : oper) {
                double r2 = getResult(r1, c, op2);
                for (String op3 : oper) {
                    double r3 = getResult(r2, d, op3);
                    if (r3 == 24) {
                        flag = true;
                        System.out.printf("%s %s %s %s %s %s %s %n", a, op1, b, op2, c, op3, d);
                    }
                }
            }
        }
        return flag;
    }

    public double getResult(double n1, double n2, String operation) {
        switch (operation) {
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
            case "/":
                return n1 / n2;
            default:
                return -1;
        }
    }


    public double getNum(String s) {
        switch (s) {
            case "A":
                return 1d;
            case "J":
                return 11d;
            case "Q":
                return 12d;
            case "K":
                return 13d;
            case "T":
                return 10d;
            default:
                return 0d;
        }
    }

}
