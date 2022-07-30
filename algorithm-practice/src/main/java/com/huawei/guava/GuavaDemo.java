package com.huawei.guava;

import com.google.common.cache.CacheBuilder;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author king
 * @date 2022/7/13-19:06
 * @Desc
 */
public class GuavaDemo {
    @Test
    public void fun01() {
        CacheBuilder<Object, Object> objectObjectCacheBuilder = CacheBuilder.newBuilder();
    }


    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(y));
    }


    /**
     * We are a team
     *
     * @param args
     */
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 队员
        int m = sc.nextInt(); // 消息
        if (n < 1 || m < 1 || n > 100000 || m > 100000) {
            System.out.println("Null");
        }
        List<Set<Integer>> team = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            List<Integer> tmp = new ArrayList<>();
            tmp.add(x);
            tmp.add(y);
            tmp.add(z);
            if (z == 0) {
                boolean flag = false;
                for (int j = 0; j < team.size(); j++) {
                    if (team.get(j).contains(x) || team.get(j).contains(y)) {
                        flag = true;
                        team.get(j).add(x);
                        team.get(j).add(y);
                        break;
                    }
                }
                if (!flag) {
                    Set<Integer> set = new HashSet<>();
                    set.add(x);
                    set.add(y);
                    team.add(set);
                }
                continue;
            }
            if (z == 1) {
                boolean flag = false;
                for (int j = 0; j < team.size(); j++) {
                    if (team.get(j).contains(x) && team.get(j).contains(y)) {
                        flag = true;
                        System.out.println("we are a team");
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("we are not a team");
                }
            } else {
                System.out.println("da pian zi");
            }
        }
    }

    /**
     * 计算面积
     *
     * @param args
     */
    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 指令
        int m = sc.nextInt(); // 终点
        int dx = 0;
        int dy = 0;
        int area = 0;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            dx = x - pre;
            area += dx * dy;
            pre = x;
            dy = Math.abs(y + dy);
        }
        if (pre < m) {
            area += (m - pre) * dy;
        }
        System.out.println(area);
    }

    /**
     * 服务失效判断
     *
     * @param args
     */
    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] serves = sc.nextLine().split(",");
        String[] error = sc.nextLine().split(",");
        // a1-a2,a5-a6
        Deque deque = new LinkedList<>();

    }

    /**
     * 数据分类
     *
     * @param args
     */
//    import java.util.Scanner;
//    public class Main;
    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String[] arr = sc.nextLine().split(" ");
            long a = Long.parseLong(arr[0]);
            long b = Long.parseLong(arr[1]);
            System.out.println(a + b);
        }
        /*while (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(a+b);;
        }*/
    }

    public static void main6(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
    }

    /**
     * 输出字符串
     *
     * @param args
     */
    public static void main7(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        List<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("break")) {
                break;
            }
            if (s.length() < 8) {
                list.add(handleStr(s, 0));
            } else {
                for (int j = 0; j < s.length(); j += 8) {
                    if (s.length() - j < 8) {
                        list.add(handleStr(s, j));
                    } else {
                        list.add(s.substring(j, j + 8));
                    }
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private static String handleStr(String s, int start) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(start));
        int count = s.length() < 8 ? 8 - s.length() : 9 - (s.length() - start);
        for (int i = count; i > 0; i--) {
            sb.append("0");
        }
        return sb.toString();
    }

    /**
     * n阶方阵所有数的和
     *
     * @param args
     */
    public static void main8(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += sc.nextInt();
            }
        }
        System.out.println(sum);
    }

    public static void main9(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        String[] s = sc.nextLine().split(" ");
        int sum = 0;
        // 暴力破解
        /*for (int i = 0; i < s.length; i++) {
            for (int j = i+1; j < s.length; j++) {
                for (int k = j+1; k < s.length; k++) {
                    int a = Integer.valueOf(s[i]);
                    int b = Integer.valueOf(s[j]);
                    int c = Integer.valueOf(s[k]);
                    if (canSanjia(a,b,c)) {
                        sum++;
                    }
                }
            }
        }*/
        // 使用滑动窗口
        for (int i = 0; i < s.length; i++) {
            int left = i + 1;
            int right = i + 2;
            for (int j = left; j < right; j++) {
                for (int k = right; k < s.length; k++) {
                    int a = Integer.valueOf(s[i]);
                    int b = Integer.valueOf(s[j]);
                    int c = Integer.valueOf(s[k]);
                    if (canSanjia(a, b, c)) {
                        sum++;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    private static boolean canSanjia(int a, int b, int c) {
        if (a + b > c) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
