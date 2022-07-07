package com.huawei.algorithm.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
            if( split[0].equals("0") && split[1].equals("0")) {
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
                sum+=Integer.valueOf(split[i]);
            }
            System.out.println(sum);
        }
    }
    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.valueOf(sc.nextLine());
        while (num>0) {
            String s = sc.nextLine();
            String[] split = s.split("\\s+");
            if (split[0].equals("0")) {
                break;
            }
            int sum = 0;
            for (int i = 1; i <= Integer.valueOf(split[0]); i++) {
                sum+=Integer.valueOf(split[i]);
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
                sum+=Integer.valueOf(split[i]);
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
                System.out.print(split[i]+" ");
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
                System.out.print(split[i]+" ");
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split(",");
            Arrays.sort(split);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < split.length; i++) {
                if (i == split.length-1) {
                    sb.append(split[i]);
                }else{
                    sb.append(split[i]+",");
                }
            }
            System.out.println(sb.toString());
        }
    }
}
