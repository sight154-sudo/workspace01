package com.huawei.algorithm.input;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author king
 * @date 2022/7/3-16:09
 * @Desc
 */
public class InputPractice {
    public static void main1(String[] args) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split("\\s+");
            a.add(Integer.valueOf(split[0]));
            b.add(Integer.valueOf(split[1]));
        }

        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i) + b.get(i));
        }
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        Integer num = Integer.valueOf(sc.nextLine());
        while (num > 0) {
            String s = sc.nextLine();
            String[] split = s.split("\\s+");
            a.add(Integer.valueOf(split[0]));
            b.add(Integer.valueOf(split[1]));
            num--;
        }
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i) + b.get(i));
        }
    }

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split("\\s+");
            if (split[0].equals("0") && split[1].equals("0")) {
                break;
            }
            a.add(Integer.valueOf(split[0]));
            b.add(Integer.valueOf(split[1]));
        }
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i) + b.get(i));
        }
    }

    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split("\\s+");
            if (split[0].equals("0")) {
                break;
            }
            int sum = 0;
            for (int i = 1; i < split.length; i++) {
                sum += Integer.valueOf(split[i]);
            }
            System.out.println(sum);
        }
    }

    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.valueOf(sc.nextLine());
        while (num > 0) {
            String s = sc.nextLine();
            String[] split = s.split("\\s+");
            if (split[0].equals("0")) {
                break;
            }
            int sum = 0;
            for (int i = 1; i <= Integer.valueOf(split[0]); i++) {
                sum += Integer.valueOf(split[i]);
            }
            System.out.println(sum);
            num--;
        }
    }

    public static void main6(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split("\\s+");
            int sum = 0;
            for (int i = 0; i <= split.length; i++) {
                sum += Integer.valueOf(split[i]);
            }
            System.out.println(sum);
        }
    }

    public static void main7(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer num = Integer.valueOf(sc.nextLine());
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split("\\s+");
            Arrays.sort(split);
            for (int i = 0; i < split.length; i++) {
                System.out.print(split[i] + " ");
            }
        }
    }

    public static void main8(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split("\\s+");
            Arrays.sort(split);
            for (int i = 0; i < split.length; i++) {
                System.out.print(split[i] + " ");
            }
        }
    }

    public static void main09(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split(",");
            Arrays.sort(split);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < split.length; i++) {
                if (i == split.length - 1) {
                    sb.append(split[i]);
                } else {
                    sb.append(split[i] + ",");
                }
            }
            System.out.println(sb.toString());
        }
    }

    public static void main11(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        Map<String, List<List<Integer>>> map = new HashMap<>();
//        List<List<Integer>> lists = new ArrayList<>();
//        List<List<Object>> lists = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String fruit = sc.next();
            int id = sc.nextInt();
            int w = sc.nextInt();
            List<List<Integer>> lists = map.getOrDefault(fruit, new ArrayList<>());
            List<Integer> list = new ArrayList<>();
            list.add(id);
            list.add(w);
            lists.add(list);
            map.put(fruit, lists);
        }
        long start = System.currentTimeMillis();
        map.entrySet().stream().sorted((o1, o2) -> {
            List<List<Integer>> list1 = o1.getValue().stream().sorted(Comparator.comparingInt(o12 -> o12.get(1))).collect(Collectors.toList());
            o1.setValue(list1);
            List<List<Integer>> list2 = o2.getValue().stream().sorted(Comparator.comparingInt(o12 -> o12.get(1))).collect(Collectors.toList());
            o2.setValue(list2);
            return o1.getValue().get(1).get(1) - o2.getValue().get(1).get(1);
        }).forEach(x -> x.getValue().stream().forEach(y -> System.out.println(x.getKey() + " " + y.get(0) + " " + y.get(1))));
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void main12(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String country = sc.next();
            int gold = sc.nextInt();
            int silver = sc.nextInt();
            int bronze = sc.nextInt();
            List<Integer> list = new ArrayList<>(Arrays.asList(gold, silver, bronze));
            map.put(country, list);
        }
        map.entrySet().stream().sorted((o1, o2) -> {
            if (o1.getValue().get(0) != o2.getValue().get(0)) {
                return o2.getValue().get(0) - o1.getValue().get(0);
            } else if (o1.getValue().get(1) != o2.getValue().get(1)) {
                return o2.getValue().get(1) - o1.getValue().get(1);
            } else if (o1.getValue().get(2) != o2.getValue().get(2)) {
                return o2.getValue().get(2) - o1.getValue().get(2);
            } else {
                return o1.getKey().compareTo(o2.getKey());
            }
        }).forEach(x -> System.out.println(x.getKey()));
    }

    /**
     * 火星文处理
     *
     * @param args
     */
    public static void main13(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String src = scanner.nextLine();
            System.out.println(func(src));
        }
        scanner.close();
    }

    private static String func(String src) {
        // 先处理 $ 运算
        // 7#6$5#12
        StringBuilder sb = new StringBuilder();
        char[] chars = src.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '$') {
                sb.append(chars[i]);
            } else {
                // 处理运算后添加
                sb.deleteCharAt(sb.length() - 1);
                sb.append(compute1(src.substring(i - 1, i + 2)));
            }
        }
        System.out.println(sb);
        chars = sb.toString().toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i += 3) {
            Integer num1 = Integer.valueOf(chars[i] + "");
            Integer num2 = Integer.valueOf(chars[i + 2] + "");
            res += compute2(num1, num2);
        }
        return res + "";
    }

    public static int compute1(String s) {
        //x#y = 2x+3y+4
        //x$y = 3*x+y+2
        char[] ch = s.toCharArray();
        return 2 * ch[0] + 3 * ch[2] + 4;
    }

    public static int compute2(int num1, int num2) {
        return 3 * num1 + num2 + 2;
    }

    /**
     * 数大雁
     *
     * @param args
     */
    public static void main111(String[] args) {
        Scanner sc = new Scanner(System.in);
        String quack = sc.nextLine();
    }

    /**
     * 大雁发出的完整叫声为”quack“，因为有多只大雁同一时间嘎嘎作响，所以字符串中可能会混合多个”quack”。
     * 大雁会依次完整发出”quack”，即字符串中’q’ ,‘u’, ‘a’, ‘c’, ‘k’ 这5个字母按顺序完整存在才能计数为一只大雁。
     * 如果不完整或者没有按顺序则不予计数。
     * 如果字符串不是由’q’, ‘u’, ‘a’, ‘c’, ‘k’ 字符组合而成，或者没有找到一只大雁，请返回-1。
     *
     * @param quack
     * @return
     */
    public int countQuack(String quack) {
        // quackquack  1
        // quaquckack 2
        // quaquack 1
        // qacqk  -1
        return 1;
    }

    public static void main112(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int count = sc.nextInt();
        int[][] arr = new int[count][2];
        for (int i = 0; i < count; i++) {
            int[] tmp = new int[2];
            tmp[0] = sc.nextInt();
            tmp[1] = sc.nextInt();
            arr[i] = tmp;
        }
        sc.close();
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{arr[0][1], 1});
        for (int i = 1; i < arr.length; i++) {
            int last = arr[i][0];
            boolean flag = true;
            for (int j = 0; j < list.size(); j++) {
                int[] dp = list.get(j);
                if (last <= dp[0] && dp[1] < m) {
                    flag = false;
                    dp[0] = last;
                    dp[1]++;
                    break;
                }
            }
            if (flag) {
                list.add(new int[]{arr[i][1], 1});
            }
            /*if (last <= arr[i][0]) {
                list.remove(0);
            }
            addItem(list, arr[i]);*/
        }
        System.out.println(list.size());
    }

    /**
     * 最少面试官数
     *
     * @param args
     */
    public static void main14(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int count = sc.nextInt();
        int[][] arr = new int[count][2];
        for (int i = 0; i < count; i++) {
            int[] tmp = new int[2];
            tmp[0] = sc.nextInt();
            tmp[1] = sc.nextInt();
            arr[i] = tmp;
        }
        sc.close();
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        Deque<int[]> deque = new ArrayDeque<>();
        // 存放数组，0索引为面试官结束时间  1 为当前面试官的面试的次数
        deque.addFirst(new int[]{arr[0][1], 1});
        for (int i = 1; i < arr.length; i++) {
            // 当前面试的开始时间
            int endTime = arr[i][0];
            // flag标识是否需要再加一名面试官
            boolean flag = true;
            for (int[] dp : deque) {
                if (dp[0] <= endTime && dp[1] < m) {
                    // 说明可以存放在面试官之后
                    flag = false;
                    dp[0] = endTime;
                    dp[1]++;
                    break;
                }
            }
            if (flag) {
                deque.addLast(new int[]{endTime, 1});
            }
        }
        System.out.println(deque.size());
    }

    private static void addItem(List<int[]> list, int[] arr) {
        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {
            if (arr[1] < list.get(i)[1]) {
                flag = false;
                list.add(i, arr);
                break;
            }
        }
        if (flag) {
            list.add(arr);
        }

    }

    /**
     * 水仙花数
     *
     * @param args
     */
    public static void main15(String[] args) {
        Scanner sc = new Scanner(System.in);
        String src = sc.nextLine();
        List<List<String>> lists = new ArrayList<>();
        Deque<String> deque = new ArrayDeque<>();
        int len = src.length();
        partition(src, lists, deque, 0, 0);
        System.out.println("pre : " + lists);
        // 遍历每个子串，判断是否为水仙花数
        List<String> res = new ArrayList<>();
        for (int i = lists.size() - 1; i >= 0; i--) {
            boolean isShuixianhua = true;
            for (String s : lists.get(i)) {
                if (!isshuixianhua(s)) {
                    isShuixianhua = false;
                    break;
                }
            }
            if (!isShuixianhua) {
                lists.remove(i);
            }
        }
        System.out.println(lists);
        if (lists.size() == 0) {
            System.out.println(0);
        } else if (lists.size() == 1) {
            System.out.println(lists.get(0).size());
        } else {
            System.out.println(-1);
        }
    }

    private static boolean isshuixianhua(String s) {
        char[] chars = s.toCharArray();
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            num += chars[i];
        }
        int sum = 0;
        int cur = num;
        while (cur > 0) {
            int m = cur % 10;
            sum += m * m * m;
            cur /= 10;
        }
        return sum == num;
    }

    private static void partition(String src, List<List<String>> lists, Deque<String> deque, int len, int idx) {
        if (len == src.length()) {
            lists.add(new ArrayList<>(deque));
            return;
        }
        for (int i = idx; i < src.length(); i++) {
            String s = src.substring(idx, i + 1);
            deque.addLast(s);
            partition(src, lists, deque, len + s.length(), idx + 1);
            deque.removeLast();
        }
    }

    @Test
    public void teaaa() {
        char k = 'a';
        System.out.println((int) 'M' + (int) 'L');
        System.out.println((int) 'M');
        System.out.println((int) 'L');
        String s = "aa";
        System.out.println("s.hashCode() = " + s.hashCode());
        System.out.println((char) 76);
        for (int i = 100; i < 1000; i++) {
            int sum = 0;
            int cur = i;
            while (cur > 0) {
                int m = cur % 10;
                sum += m * m * m;
                cur /= 10;
            }
            if (sum == i) {
                System.out.println(i);
            }
        }
    }

    /**
     * 统计网站访问量
     *
     * @param args
     */
    public static void main16(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();
        Comparator<WebSite> com = (o1, o2) -> {
            if (o1.count != o2.count) {
                return o1.count - o2.count;
            }
            return o2.key.compareTo(o1.key);
        };
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains(".")) {
                map.put(line, map.getOrDefault(line, 1) + 1);
            } else {
                // 排序
                Integer num = Integer.valueOf(line);
                PriorityQueue<WebSite> pq = new PriorityQueue<>(num, com);
                Iterator<Map.Entry<String, Integer>> it =
                        map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Integer> entry = it.next();
                    if (pq.size() < num) {
                        pq.offer(new WebSite(entry.getKey(), entry.getValue()));
                    } else {
                        if (pq.peek().count < entry.getValue()) {
                            pq.poll();
                            pq.offer(new WebSite(entry.getKey(), entry.getValue()));
                        }
                    }
                }
                WebSite[] webSites = new WebSite[num];

                pq.toArray(webSites);
                Arrays.stream(webSites).sorted((o1, o2) -> {
                    if (o1.count != o2.count) {
                        return o2.count - o1.count;
                    }
                    return o1.key.compareTo(o2.key);
                }).forEach(o1 -> System.out.print(o1.key + " "));
                /*while(!pq.isEmpty()) {
                    System.out.print(pq.poll().key + " ");
                }*/
                /*map.entrySet().stream().sorted((o1, o2) -> {
                    int num1 = o1.getValue();
                    int num2 = o2.getValue();
                    if (num1 != num2) {
                        return num2 - num1;
                    }
                    return o1.getKey().compareTo(o2.getKey());
                }).limit(num).map(enntry -> enntry.getKey()).collect(Collectors.toList())
                .forEach(s-> System.out.print(s+" "));*/
            }
        }
    }

    static class WebSite {
        private String key;
        private Integer count;

        public WebSite(String key, Integer count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WebSite webSite = (WebSite) o;

            if (key != null ? !key.equals(webSite.key) : webSite.key != null) return false;
            return count != null ? count.equals(webSite.count) : webSite.count == null;
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (count != null ? count.hashCode() : 0);
            return result;
        }
    }

    /**
     * 最长的完全连续交替方波信号
     *
     * @param args
     */
    public static void main17(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int max = 0;
        boolean flag = false;
        int count = 0;

        String str = "";
        for (int i = 0; i < line.length(); i++) {
            String sub = line.substring(i, i + 2 < line.length() ? i + 2 : line.length());
            if ("01".equals(sub)) {
//                flag = true;
                int index = i;
                i += 2;
                while (i < line.length()) {
                    String tmp = line.substring(i, i + 2 < line.length() ? i + 2 : line.length());
                    if ("01".equals(tmp)) {
                        count += 2;
                        i += 2;
                    } else {
                        if (("0".equals(tmp) || "00".equals(tmp))) {
                            // 0 或 00 的情况
                            count += 3;
                            if (count > max) {
                                max = count;
                                flag = true;
                            }
                        }
                        break;
                    }
                }
                if (flag) {
                    str = line.substring(index, index + count);
                    flag = false;
                }
                count = 0;
            }
            /*else {
                if (flag) {
                    if (sub.startsWith("0") && count+1 > max) {
                        max = count+1;
                        str = line.substring(index, index+count+2);
                        i--;
                        flag = false;
                        count = 0;
                    }
                }
            }*/
        }
        System.out.println(str);
    }

    public static void main19(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
//        int max = 0;
        boolean flag = false; // 是否可以开始记录
//        int count = 0;
//        boolean isFuhe = true; // 信息是否符合条件

        String str = "";
        StringBuilder sb = new StringBuilder();
        char[] chars = line.toCharArray();
        if (chars[0] == '0') {
            sb.append(chars[0]);
            flag = true;
        }
        for (int i = 1; i < chars.length; i++) {
            if (flag) {
                // 判断当前与前一个是否相同， 若相同，则需要判断信号是否符合
                if (chars[i] == chars[i - 1]) {
                    if (chars[i] == '0') {
                        if (sb.length() >= 3) {
                            // 避免为 00
                            str = sb.length() > str.length() ? sb.toString() : str;
                        }
                        sb.setLength(0);
                        sb.append(chars[i]);
                        // 重新记录
                    } else {
                        // 若相同的为1,则此信息不符合
                        sb.setLength(0);
                    }
                } else {
                    sb.append(chars[i]);
                }
            } else {
                if (chars[i] == '0') {
                    sb.append('0');
                    flag = true;
                }
            }
        }
        if (sb.length() > str.length()) {
            str = sb.toString();
        }
        System.out.println(str);
    }

    public static void main20(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        if (split.length == 0) {
            System.out.println("/");
            return;
        }
        String res = "";
        StringBuilder sb = new StringBuilder();
        if (split.length == 1) {
            res = getString(split, sb, 0);
            System.out.println(res);
            return;
        }
        if (split[0].length() == 0) {
            res = getString(split, sb, 1);
            System.out.println(res);
            return;
        }
        if (split[0].startsWith("/")) {
            res = getString(split, sb, 0);
            String end = getString(split, sb, 1).substring(1);
            System.out.println(res + "/" + end);
            return;
        }
    }

    private static String getString(String[] split, StringBuilder sb, int i) {
        String res;
        if (!split[i].startsWith("/")) {
            sb.append("/");
        }
        res = split[i].replaceAll("/+", "/").toString();
        res = res.endsWith("/") ? res.substring(0, res.length() - 1) : res;
        return res;
    }

    public int shareCandy(int candy) {
        int[] candys = new int[candy + 1];
        // 初始化
        candys[0] = 0;
        // candy == 2时，有一种
        candys[1] = 1;
        int count = shareCandy(candy, candys);
//        System.out.println(Arrays.toString(candys));
        return count;
    }

    private int shareCandy(int candy, int[] candys) {
        if (candy == 1) {
            return 0;
        }
        if (candy == 2) {
            return 1;
        }
        if (candys[candy - 1] != 0) {
            return candys[candy - 1];
        }
        if (candy % 2 == 0) {
            candys[candy - 1] = shareCandy(candy / 2, candys) + 1;
            return candys[candy - 1];
        }
        candys[candy - 1] = Math.min(shareCandy(candy + 1, candys) + 1, shareCandy(candy - 1, candys) + 1);
        return candys[candy - 1];
    }


    @Test
    public void shareCandy() {
        int candy = 100000000;
        Runtime runtime = Runtime.getRuntime();
        long start = runtime.totalMemory() - runtime.freeMemory();
        int count = shareCandy(candy);
        long end = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("count = " + count);
        System.out.println((end - start) / 1024 / 1024);
    }

    @Test
    public void shaiziGame() {
        String init = "123456";
        String s = "LL";
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            init = shaizi(init, ch);
        }
        System.out.println(init);
    }

    public String shaizi(String s, char c) {
        StringBuilder sb = new StringBuilder();
        StringBuilder reverse = new StringBuilder();
        switch (c) {
            case 'L':
                sb.append(s, 4, 6);
                reverse.append(s, 0, 2).reverse();
                sb.append(s, 2, 4).append(reverse);
                break;
            case 'R':
                reverse.append(s, 4, 6).reverse();
                sb.append(reverse).append(s, 2, 4).append(s, 0, 2);
                break;
            case 'F':
                sb.append(s, 0, 2);
                reverse.append(s, 2, 4).reverse();
                sb.append(s, 4, 6).append(reverse);
                break;
            case 'B':
                sb.append(s, 0, 2);
                reverse.append(s, 4, 6).reverse();
                sb.append(reverse).append(s, 2, 4);
                break;
            case 'A':
                reverse.append(s, 2, 4).reverse();
                sb.append(reverse).append(s, 0, 2).append(s, 4, 6);
                break;
            case 'C':
                sb.append(s, 2, 4);
                reverse.append(s, 0, 2).reverse();
                sb.append(reverse).append(s, 4, 6);
                break;
        }
        return sb.toString();
    }

    public static void main21(String[] args) {
        //6
        //3 1
        //5 1
        //4 3
        //10 5
        //11 5
        //12 4
        //5
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[][] arr = new int[len][2];
        for (int i = 0; i < len; i++) {
            int cur = sc.nextInt();
            int parent = sc.nextInt();
            arr[i] = new int[]{cur, parent};
        }
        int del = sc.nextInt();
        sc.close();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = map.getOrDefault(arr[i][1], new ArrayList<>());
            list.add(arr[i][0]);
            map.put(arr[i][1], list);
        }
    }

    public static void main(String[] args) {
        // 默认为0，表示不可达
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[][] room = new int[x][y];
        // 陷阱数
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            room[x1][y1] = -1;
        }
        /*for (int i = 0; i < x; i++) {
            System.out.println(Arrays.toString(room[i]));
        }*/
        out(0, 0, room);
        for (int i = 0; i < x; i++) {
            System.out.println(Arrays.toString(room[i]));
        }
    }

    private static void out(int x, int y, int[][] room) {
        if (x == room.length - 1 && y == room[0].length - 1) {
            room[x][y] = 1;
            return;
        }
        // 已经确定过的格子，无需再走
        if (room[x][y] != 0) {
            return;
        }
        if (x + 1 < room.length) {
            // 向下走
            out(x + 1, y, room);
        }
        if (y + 1 < room[0].length) {
            // 向右走
            out(x, y + 1, room);
        }
        if (x == room.length - 1 || y == room[0].length - 1) {
            // 到达左边界，或下边界
            if (x == room.length - 1 && room[x][y + 1] != 1) {
                // 下边走不通 则为不可达
                room[x][y] = 2;
            } else if (y == room[0].length - 1 && room[x + 1][y] != 1) {
                // 右边走不能，
                room[x][y] = 2;
            } else {
                // 其他到边界后，右下都能走通
                room[x][y] = 1;
            }
        } else if (room[x + 1][y] != 1 && room[x][y + 1] != 1) {
            // 未到达边界时，右，下都不能走通
            room[x][y] = 2;
        } else {
            // 未到达边界时，右，下有一个可以走通
            room[x][y] = 1;
        }
    }
}
