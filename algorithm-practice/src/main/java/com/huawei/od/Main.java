package com.huawei.od;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author king
 * @date 2022/8/28-20:10
 * @Desc
 */
public class Main {

    public static void main01(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String[] split = sc.nextLine().split(" ");
        int[] arr = new int[split.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.valueOf(split[i]);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int res = Math.abs(arr[i] + arr[j]);
                if (res < min) {
                    min = res;
                }
            }
        }
        System.out.println(min);
    }

    static boolean isArrive = false;

    public static void main02(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String[] split = sc.nextLine().split(" ");
        int[] arr = new int[split.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.valueOf(split[i]);
        }
        int len = arr.length;
        int step = len / 2;
        // 1-step中走到最后一个数组的最少步数

        int[] steps = new int[step - 1];
        for (int i = 0; i < steps.length; i++) {
            steps[i] = canArrive(arr, i + 1, 1);
        }
        if (!isArrive) {
            System.out.println("-1");
            return;
        }
        int min = steps[1];
        for (int i = 2; i < steps.length; i++) {
            if (steps[i] != -1 && steps[i] < min) {
                min = steps[i];
            }
        }
        System.out.println(min);
    }

    public static int canArrive(int[] arr, int idx, int step) {
        while (idx < arr.length - 1) {
            idx += arr[idx];
            step++;
        }
        if (idx == arr.length - 1) {
            isArrive = true;
            return step;
        }
        return -1;
    }

    public static void main03(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // -1 表示 release  1 表示 request
        int[][] arrs = new int[n][2];
        //2
        //REQUEST=10
        //REQUEST=20
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            String[] split = s.split("=");
            int[] arr = new int[2];
            arr[0] = split[0].equals("REQUEST") ? 1 : -1;
            arr[1] = Integer.valueOf(split[1]);
            arrs[i] = arr;
        }

        // 处理指令
        // 当前内存的最大使用量
        int max = 0;
        int[] memory = new int[100];
        Map<Integer, Integer> use = new HashMap<>();
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.comparingInt(o -> o));
        map.put(0, 100);
        for (int i = 0; i < arrs.length; i++) {
            if (arrs[i][0] == -1) {
                // 表示释放内存 内存未占用，首地址不存在
                int idx = arrs[i][1];
                if (max == 0) {
                    System.out.println("error");
                }
                if (!use.containsKey(idx)) {
                    System.out.println("error");
                }
                Arrays.fill(memory, idx, use.get(idx), 0);
                max-=use.get(idx);
                use.remove(idx);
                updateSpace(memory, map);
            } else {
                int need = arrs[i][1];
                if (arrs[i][1] == 0) {
                    // 申请0内存，
                    System.out.println("error");
                }
                if ((100 - max) <= need) {
                    System.out.println("error");
                }
                Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, Integer> entry = it.next();
                    Integer key = entry.getKey();
                    Integer value = entry.getValue();
                    if (value >= need) {
                        Arrays.fill(memory, key, key+need, 1);
                        max+=need;
                        use.put(key, need);
                        System.out.println(key);
                        break;
                    }
                }
                // 更新空闲内存
                updateSpace(memory, map);
            }
        }

        /*Map<Integer, Integer> use = new HashMap<>();
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < arrs.length; i++) {
            if (arrs[i][0] == -1) {
                // 表示释放内存 内存未占用，首地址不存在
                if (max == 0) {
                    System.out.println("error");
                }
                *//*if (!use.containsKey(arrs[i][1])) {
                    System.out.println("error");
                }*//*
                // 释放内存
                if (!map.containsKey(arrs[i][0])) {
                    System.out.println("error");
                }
                map.remove(arrs[i][0]);
            } else {
                int need = arrs[i][1];
                if (arrs[i][1] == 0) {
                    // 申请0内存，
                    System.out.println("error");
                }
                if ((100 - max) <= need) {
                    System.out.println("error");
                }
                // 申请内存
                // 直接申请
                if (max == 0) {
                    map.put(need, 100-need);
                    max+=need;
                    continue;
                }
                Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, Integer> entry = it.next();
                    Integer key = entry.getKey();
                    Integer value = entry.getValue();
                    if (value >= need) {
                        if (it.hasNext()) {
                            Integer first = it.next().getKey();
                            map.put(key + need, first - need);
                        } else {
                            map.put(key + need, 100- max - need);
                        }
                        map.remove(key);
                        max+=need;
                        break;
                    }
                }
            }
        }*/
        }

    private static void updateSpace(int[] memory, TreeMap<Integer, Integer> map) {
        map.clear();
        boolean flag = false;
        int space = 0;
        int idx = 0;
        for (int j = 0; j < memory.length; j++) {
            if (memory[j] == 1 && space != 0) {
                map.put(idx, space);
                space=0;
                flag = false;
                continue;
            }
            if (memory[j] == 0) {
                if (!flag) {
                    idx = j;
                    flag = true;
                }
                space++;
            }
        }
        if (space != 0) {
            map.put(idx, space);
        }
    }



}
