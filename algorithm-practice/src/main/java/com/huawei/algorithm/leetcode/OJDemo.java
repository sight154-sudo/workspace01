package com.huawei.algorithm.leetcode;

import com.google.common.collect.Lists;
import com.huawei.queue.ArrayQueue;
import javafx.geometry.Pos;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.Arrays.binarySearch;

/**
 * @author king
 * @date 2024/2/22-22:26
 * @Desc
 */
public class OJDemo {


    @Test
    public void getSubStringTest() {
        String s = "1-5";
        int num = 1;
        System.out.println(getSubString(s, num));
    }


    public String getSubString(String s, int num) {
        String[] split = s.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s1 : split) {
            if (s1.contains("-")) {
                Integer before = Integer.valueOf(s1.split("-")[0]);
                Integer after = Integer.valueOf(s1.split("-")[1]);
                while (before <= after) {
                    list.add(before++);
                }
            } else {
                list.add(Integer.valueOf(s1));
            }
        }
        list.remove(Integer.valueOf(num));
        Collections.sort(list);
        int start = list.get(0);
        int end = list.get(0);
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == end + 1) {
                // 表示连续
                end = list.get(i);
            } else {
                if (start == end) {
                    // 表示只有单个
                    sb.append(start).append(",");
                } else {
                    sb.append(start).append("-").append(",");
                }
                start = list.get(i);
                end = list.get(i);
            }
        }
        return sb.substring(0, sb.length() - 1);
    }


    public static void main13(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            List<String> list = Arrays.stream(sc.nextLine().split(" ")).collect(Collectors.toList());
            boolean res = getResult(list);
            sb.append(res).append(" ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    /**
     * 缺勤不超过一次；
     * 没有连续的迟到/早退；
     * 任意连续7次考勤，缺勤/迟到/早退不超过3次。
     *
     * @param list
     * @return
     */
    public static boolean getResult(List<String> list) {
        // absent：缺勤    late：迟到    leaveearly：早退    present：正常上班
        for (int i = 0; i < list.size(); i++) {
            int absentCount = 0;
            int inCount = 0;
            String pre = "";
            for (int j = i; j < i + 7 && j < list.size(); j++) {
                String str = list.get(j);
                if ("absent".equals(str)) {
                    inCount++;
                    absentCount++;
                } else if ("late".equals(str) || "leaveearly".equals(str)) {
                    inCount++;
                    if ("late".equals(pre) || "leaveearly".equals(pre)) {
                        return false;
                    }
                }
                if (inCount > 3) {
                    return false;
                }
                pre = str;
            }
            if (absentCount > 1) {
                return false;
            }
        }
        return true;
    }


    public static void main14(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split1 = sc.nextLine().split(" ");
        String[] colors = sc.nextLine().split(" ");
        List<PuKe> puKeList = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            PuKe puKe = new PuKe(Integer.valueOf(split1[i]), colors[i]);
            puKeList.add(puKe);
        }
        Collections.sort(puKeList, Comparator.comparingInt(o -> o.num));
        Set<PuKe> set = new HashSet<>();
        int max = 0;
        /*for (int i = 0; i < puKeList.size(); i++) {
            PuKe puKe = puKeList.get(i);
            if (set.contains(puKe)){
                continue;
            }
            int count = 0;
            Set<PuKe> puKeSet = new HashSet<>();
            puKeSet.add(puKe);
            for (int j = 0; j < puKeList.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (pukes)
            }
            max = Math.max(max, count);
            set.add(puKe);
        }*/
        System.out.println(max);
    }


    static class PuKe {
        int num;
        String color;

        public PuKe(int num, String color) {
            this.num = num;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PuKe puKe = (PuKe) o;
            return num == puKe.num || Objects.equals(color, puKe.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, color);
        }

    }

    public static void main15(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());
        sc.close();
        int oneSum = 0;
        int[] src = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < k) {
                src[i] = 1;
                oneSum++;
            }
        }
        if (oneSum == 0 || oneSum == arr.length) {
            System.out.println(0);
            return;
        }
        System.out.println(getMinSwap2(src, oneSum));
    }

    public static int getMinSwap2(int[] arr, int oneSum) {
        int zeroNum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (i >= oneSum) {
                min = Math.min(min, zeroNum);
                if (arr[i - oneSum] == 0) {
                    zeroNum--;
                }
            }
            if (arr[i] == 0) {
                zeroNum++;
            }
        }
        return min;
    }

    public static int getMinSwap(int[] arr, int start, int end, int oneSum) {
        int zeroNum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (i >= oneSum) {
                min = Math.min(min, zeroNum);
                if (arr[i - oneSum] == 0) {
                    zeroNum--;
                }
            }
            if (arr[i] == 0) {
                zeroNum++;
            }
        }
        return min;
    }

    public static void main16(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int M = sc.nextInt();
        int max = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 0; i < N; i++) {
            if (i >= M) {
                max = Math.max(max, res);
                res -= arr[i - M];
            }
            res += arr[i];
        }
        System.out.println(max);
    }

    public static void main17(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = new String[5];
        String[] pei = new String[5];
        List<DeZhou> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arr[i] = sc.next();
            pei[i] = sc.next();
            DeZhou deZhou = new DeZhou(arr[i], pei[i]);
            list.add(deZhou);
        }
        sc.close();
        /*String[] other = Arrays.copyOf(arr, arr.length);
        int[] nums = new int[5];
        for (int i = 0; i < other.length; i++) {
            nums[i] = getTarget(other[i]);
        }
        Arrays.sort(nums);
        boolean flag = false;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i]+1 == nums[i+1]) {
                flag = true;
            } else {
                flag = false;
            }
        }*/
        System.out.println(judge(list));
    }

    public static String getSrc(Integer num) {
        if (num == 1) {
            return "A";
        } else if (num == 11) {
            return "J";
        } else if (num == 12) {
            return "Q";
        } else if (num == 13) {
            return "K";
        } else {
            return num + "";
        }
    }

    public static int getColor(String str) {
        switch (str) {
            case "H":
                return 0;
            case "S":
                return 1;
            case "C":
                return 2;
            default:
                return 3;
        }
    }

    public static int getTarget(String str) {
        if (str.equals("A")) {
            return 1;
        } else if (str.equals("J")) {
            return 11;
        } else if (str.equals("Q")) {
            return 12;
        } else if (str.equals("K")) {
            return 13;
        } else {
            return Integer.valueOf(str);
        }
    }


    public static int judge(List<DeZhou> list) {
        comparetorDeZhou(list);
        boolean flag = isShunZi(list);
        int huaCount = sameHuaCount(list);
        if (flag && huaCount == 1) {
            // 同花顺
            return 1;
        } else if (flag && huaCount > 1) {
            // 顺子
            return 5;
        } else if (!flag && huaCount == 1) {
            // 同花
            return 4;
        }
        Map<String, Integer> map = savePointCount(list);
        if (map.size() == 2) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 4) {
                    return 2;
                }
                if (entry.getValue() == 3) {
                    return 3;
                }
            }
            return 2;
        } else if (map.size() == 3) {
            return 6;
        }

        return 0;
    }

    public static Map<String, Integer> savePointCount(List<DeZhou> list) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String num = list.get(i).num;
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map;
    }

    public static int sameHuaCount(List<DeZhou> list) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i).hua);
        }
        return set.size();
    }

    public static void comparetorDeZhou(List<DeZhou> list) {
        Collections.sort(list, Comparator.comparingInt(o -> getTarget(o.num)));
    }

    public static boolean isShunZi(List<DeZhou> list) {
        String[][] shun = {
                {"2", "3", "4", "5", "6"},
                {"3", "4", "5", "6", "7"},
                {"4", "5", "6", "7", "8"},
                {"5", "6", "7", "8", "9"},
                {"6", "7", "8", "9", "10"},
                {"7", "8", "9", "10", "J"},
                {"8", "9", "10", "J", "Q"},
                {"9", "10", "J", "Q", "K"},
                {"A", "10", "J", "Q", "K"},
                {"A", "2", "3", "4", "5"},
        };
        String[] strings = new String[5];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = list.get(i).num;
        }
        for (int i = 0; i < shun.length; i++) {
            if (Arrays.equals(shun[i], strings)) {
                return true;
            }
        }
        return false;
    }


    static class DeZhou {
        String num;
        String hua;

        DeZhou(String num, String hua) {
            this.num = num;
            this.hua = hua;
        }


    }

    public static void main18(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>(); // 存放数字
        Map<String, Integer> map = new HashMap<>();
        int[] color = new int[4];
        for (int i = 0; i < 5; i++) {
            String num = sc.next();
            list.add(getTarget(num));
            color[getColor(sc.next())]++;
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Collections.sort(list);
        boolean isTongHua = color[0] == 5 || color[1] == 5 || color[2] == 5 || color[3] == 5;
        int res = 7;
        if (map.size() == 5) {
            boolean isShun = isShunZi2(list);
            if (isShun) {
                res = isTongHua ? 1 : 5;
            } else {
                res = isTongHua ? 4 : 7;
            }
        } else {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 4) {
                    res = 2;
                    break;
                } else if (entry.getValue() == 3) {
                    res = map.size() == 2 ? 3 : 6;
                    break;
                }
            }
        }
        System.out.println(res);
    }

    public static boolean isShunZi2(List<Integer> list) {
        int index = 1;
        if (list.get(0) == 1 && list.get(1) == 10) {
            index = 2;
        }
        for (int i = index; i < list.size(); i++) {
            if (list.get(i) != list.get(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }

    public static void main19(String[] args) {
        Scanner sc = new Scanner(System.in);
        String target = sc.next();
        String source = sc.next();
        sc.close();
        int l1 = target.length() - 1;
        int l2 = source.length() - 1;
        int res = -1;
        while (l2 >= 0) {
            if (source.charAt(l2) == target.charAt(l1)) {
                l1--;
            }
            if (l1 == -1) {
                res = l2;
                break;
            }
            l2--;
        }
        System.out.println(res);
    }


    public static void main20(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = sc.nextInt();
        }
        int aSum = arr[0];
        int sum = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            aSum ^= arr[i];
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        int res = -1;
        if (aSum != 0) {
            res = -1;
        } else {
            res = sum - min;
        }
        System.out.println(res);
    }


    public static void main21(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int pre = 0;
        int max = Integer.MIN_VALUE;
        int i = 0;
        Arrays.sort(arr);
        for (; i < arr.length; i++) {
            max = Math.max(max, arr[i] - pre);
            pre = arr[i];
        }
        max = Math.max(max, l - pre);
        System.out.printf("%.2f", max / 2.0);
    }

    /**
     * @param args
     */
    public static void main22(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, List<Integer>> ziMap = new HashMap<>();
        int[] parentArr = new int[1000];
        int[] ziArr = new int[1000];
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if ("#".equals(str)) {
                break;
            }
            String[] s = str.split(" ");
            int parent = Integer.parseInt(s[0]);
            int zi = Integer.parseInt(s[1]);
            parentMap.put(zi, parent);
            List<Integer> list = ziMap.get(parent);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(zi);
            ziMap.put(parent, list);
        }
        for (int i = 1; i <= n; i++) {
            int parent = 0;
            int zi = getAllZi(ziMap, i);
            int cur = i;
            while (parentMap.get(cur) != null) {
                parent++;
                cur = parentMap.get(cur);
            }
            parentArr[i - 1] = parent;
            ziArr[i - 1] = zi;
        }
        int min = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int ans = Math.max(parentArr[i], ziArr[i]);
            if (ans < min) {
                res.clear();
                res.add(i + 1);
                min = ans;
            } else if (ans == min) {
                res.add(i + 1);
            }
        }
        Collections.sort(res);
        StringBuffer sb = new StringBuffer();
        for (Integer re : res) {
            sb.append(re).append(" ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    public static int getAllZi(Map<Integer, List<Integer>> map, int cur) {
        if (map.get(cur) == null) {
            return 0;
        }
        List<Integer> next = map.get(cur);
        int res = Integer.MIN_VALUE;
        for (Integer num : next) {
            res += getAllZi(map, num);
        }
        return res;
    }

    class NTree {
        int n;
        List<NTree> children;
    }

    public static void main23(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String input = sc.nextLine();
        List<List<String>> list = new ArrayList<>();
        String[] split = s.split(",");
        for (String s1 : split) {
            String[] s2 = s1.split(" ");
            List<String> subList = new ArrayList<>();
            for (String s3 : s2) {
                subList.add(s3);
            }
            list.add(subList);
        }
        StringBuffer sb = new StringBuffer();
        for (List<String> strings : list) {
            if (findName(strings, input, 0, 0)) {
                int index = 0;
                for (; index < strings.size() - 1; index++) {
                    sb.append(strings.get(index)).append(" ");
                }
                sb.append(strings.get(index)).append(",");
            }
        }
        if (sb.length() == 0) {
            System.out.println(sb);
            return;
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    @Test
    public void findNameTest() {
        List<String> list = Lists.newArrayList("zhang", "hang");
        String input = "zha";
        System.out.println(findName(list, input, 0, 0));
    }


    public static boolean findName(List<String> srcList, String input, int index, int findIndex) {

        if (index == srcList.size()) {
            return true;
        }
        if (findIndex == input.length()) {
            return false;
        }
        String s1 = srcList.get(index);
        String subStr = input.substring(findIndex);
        if (index == srcList.size() - 1) {
            return s1.startsWith(subStr);
        }
        int idx = 0;
        boolean res = false;
        while (idx < s1.length() && idx < subStr.length() && s1.charAt(idx) == subStr.charAt(idx)) {
            index++;
            res |= findName(srcList, input, index, findIndex + idx + 1);
            if (res) {
                break;
            }
            index--;
            idx++;
        }
        return res;
    }

    public static void main24(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int k = Integer.parseInt(sc.nextLine());
        // 在前k+1位中找出最小的数字 不包含0
        // 贪心+单调栈

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N + 1][2];
        for (int i = 0; i < N - 1; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        int min = Integer.MAX_VALUE;
        int[] res = new int[N];
        for (int i = 1; i <= N; i++) {
            UF uf = new UF(N);
            for (int j = 0; j < N - 1; j++) {
                if (arr[j][0] == i || arr[j][1] == i) {
                    continue;
                } else {
                    uf.union(arr[j][0], arr[j][1]);
                }
            }
            int max = uf.getMax();
            min = Math.min(max, min);
            res[i - 1] = max;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < res.length; i++) {
            if (res[i] == min) {
                sb.append(i + 1).append(" ");
            }
        }
        System.out.println(sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1));
    }

    @Test
    public void getDpiTest() {
        int[][] arr = {{1, 2}, {2, 3}, {2, 4}, {3, 5}, {3, 6}};
//        int[][] arr = {{1,2},{1,3},{1,4},{1,5}};
        getDpi(arr);
    }

    public void getDpi(int[][] arr) {
        int N = arr.length + 1;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i][0];
            int y = arr[i][1];
            list.get(x).add(y);
            list.get(y).add(x);
        }
        int[] nums = new int[N + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            List<Integer> list1 = list.get(i);
            visited[i] = true;
            int max = 1;
            for (Integer integer : list1) {
                visited[integer] = true;
                max = Math.max(max, getMax(list, list.get(integer), visited));
            }
            nums[i] = max;
            min = Math.min(min, max);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == min) {
                System.out.println(i);
            }
        }
    }


    public static int getMax(List<List<Integer>> list, List<Integer> subList, boolean[] visited) {
        if (subList.isEmpty()) {
            return 0;
        }
        int size = 1;
        for (Integer num : subList) {
            if (!visited[num]) {
                visited[num] = true;
                size += getMax(list, list.get(num), visited);
            }
        }
        return size;
    }


    static class UF {
        int[] parent;
        int[] size;
        int max;

        public UF(int N) {
            parent = new int[N + 1];
            size = new int[N + 1];
            for (int i = 1; i < N + 1; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int findP(int n) {
            if (n == parent[n]) {
                return n;
            }
            n = parent[n];
            findP(n);
            return n;
        }

        public int getMax() {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size.length; i++) {
                max = Math.max(max, size[i]);
            }
            return max;
        }

        public void union(int n1, int n2) {
            int p1 = findP(n1);
            int p2 = findP(n2);
            if (p1 != p2) {
                if (size[p1] < size[p2]) {
                    parent[p1] = p2;
                    size[p2] += size[p1];
                } else {
                    parent[p2] = p1;
                    size[p1] += size[p2];
                }
            }
        }
    }


    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') {
                    visited[i][j] = true;
                } else {
                    if (!visited[i][j]) {
                        dfs(grid, visited, i, j);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Test
    public void numIslandsTest() {
        /*char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };*/
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
//        System.out.println(numIslands(grid));
        System.out.println(Long.MAX_VALUE);
    }

    public void dfs(char[][] grid, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        if (grid[i][j] == '0') {
            return;
        }
        if (i + 1 < grid.length && !visited[i + 1][j]) {
            dfs(grid, visited, i + 1, j);
        }
        if (i - 1 >= 0 && !visited[i - 1][j]) {
            dfs(grid, visited, i - 1, j);
        }
        if (j + 1 < grid[i].length && !visited[i][j + 1]) {
            dfs(grid, visited, i, j + 1);
        }
        if (j - 1 >= 0 && !visited[i][j - 1]) {
            dfs(grid, visited, i, j - 1);
        }
    }

    /**
     * 4
     * A,C,C,F
     * C,D,E,D
     * B,E,S,S
     * F,E,C,A
     * ACCESS
     */
    public String findStr(char[][] chars, String target) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if (chars[i][j] == target.charAt(0)) {
                    List<int[]> path = new ArrayList<>();
                    if (dfs(chars, target, 0, i, j, path)) {
                        StringBuffer sb = new StringBuffer();
                        for (int[] ints : path) {
                            sb.append(ints[0]).append(",").append(ints[1]).append(",");
                        }
                        return sb.substring(0, sb.length() - 1);
                    }
                }
            }
        }
        return "N";
    }

    @Test
    public void findStrTest() {
        char[][] chars = {
                {'G', 'C', 'C', 'F'},
                {'C', 'D', 'E', 'D'},
                {'S', 'F', 'S', 'S'},
                {'F', 'C', 'C', 'A'}
        };
        String target = "ACCFSS";
        System.out.println(findStr(chars, target));
    }

    public boolean dfs(char[][] chars, String target, int index, int i, int j, List<int[]> path) {
        if (index == target.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= chars.length || j >= chars[i].length || chars[i][j] != target.charAt(index)) {
            return false;
        }

        char tmp = chars[i][j];
        chars[i][j] = '#';
        path.add(new int[]{i, j});
        int[][] arr = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int m = 0; m < arr.length; m++) {
            if (dfs(chars, target, index + 1, i + arr[m][0], j + arr[m][1], path)) {
                return true;
            }
        }
        chars[i][j] = tmp;
        path.remove(path.size() - 1);
        return false;
    }

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public String jumpGrid(int[][] grid, int N) {
        boolean[] flag = new boolean[N];
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            set.add(grid[i][1]);
            List<Integer> list = map.computeIfAbsent(grid[i][0], k -> new ArrayList<>());
            list.add(grid[i][1]);
            flag[grid[i][1]] = true;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < flag.length; i++) {
            if (!flag[i]) {
                list.add(i);

            }
        }
        int count = 0;
        Arrays.fill(flag, false);
        for (Integer num : list) {
            count = getCanJumpCount(map, num, flag);
        }
        return count == N ? "yes" : "no";
    }

    @Test
    public void jumpGridTest() {
        int[][] grid = {
                {1, 2}, {1, 0}
        };
        int N = 3;
        System.out.println(jumpGrid(grid, N));
    }

    public int getCanJumpCount(Map<Integer, List<Integer>> map, Integer num, boolean[] flag) {
        if (flag[num]) {
            return 0;
        }
        flag[num] = true;
        List<Integer> list = map.get(num);
        int res = 1;
        if (list == null) {
            return res;
        }
        for (Integer other : list) {
            res += getCanJumpCount(map, other, flag);
        }
        return res;
    }

}

class MyQueue {

    Stack<Integer> push;
    Stack<Integer> pop;
    int size;

    public MyQueue() {
        push = new Stack<>();
        pop = new Stack<>();
        size = 0;
    }

    public void push(int x) {
        push.push(x);
        size++;
    }

    public int pop() {
        if (pop.isEmpty()) {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
        }
        size--;
        return pop.pop();
    }

    public int peek() {
        if (pop.isEmpty()) {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
        }
        return pop.peek();
    }

    public boolean empty() {
        return size == 0;
    }
}

class MyQueue2 {

    int[] arr;
    int size;
    int cur;
    int index;

    public MyQueue2() {
        arr = new int[200];
    }

    public void push(int x) {
        arr[index++] = x;
        size++;
    }

    public int pop() {
        size--;
        return arr[cur++];
    }

    public int peek() {
        return arr[cur];
    }

    public boolean empty() {
        return size == 0;
    }

    public static void main12(String[] args) {
        MyQueue2 myQueue2 = new MyQueue2();
        //Create a new instance of MyQueue2
        myQueue2.push(1);
        //Add the integer 1 to the end of the queue
        myQueue2.push(2);
        //Add the integer 2 to the end of the queue
        System.out.println(myQueue2.peek());
        //Print the first element in the queue without removing it
        System.out.println(myQueue2.pop());
        //Remove and return the first element in the queue
        System.out.println(myQueue2.empty());
        //Return true if the queue is empty, false otherwise
        // 二分查找
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 5;
        int result = binarySearch(arr, target);
        System.out.println(result); // 输出 4
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;

    }

}
