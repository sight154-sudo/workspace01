package com.huawei;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * @author king
 * @date 2022/6/18-0:45
 * @Desc
 */
public class LastWordLength {
    public static void main01(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] word = line.split("\\s+");
        System.out.println(word[word.length-1].length());
    }

    public static void main02(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        HashMap<String, Integer> map = new HashMap<>();
        map.getOrDefault("aa",0);
        int count = 0;
        int index = line.length();
        while(line.charAt(index-count-1) != ' ') {
            count++;
        }
        System.out.println(count);
    }

    public static void main03(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String source = in.nextLine();
        String target = in.nextLine();
        Map<String,Integer> map = new HashMap<String,Integer>();
        for(int i = 0;i<source.length();i++){
            char cur = source.charAt(i);
            if ( cur >=65 && cur <=90) {
                cur+=32;
                map.put(cur+"", map.getOrDefault(cur+"",0)+1);
            }else{
                map.put(source.charAt(i)+"", map.getOrDefault(source.charAt(i)+"",0)+1);
            }
        }
        String s = target.toLowerCase();


        Integer count = map.get(s);
        System.out.println(count);
    }

    public static void main04(String[] args) {
        Scanner in = new Scanner(System.in);
        String source = in.nextLine();
        String target = in.nextLine();
        source = source.toLowerCase();
        target = target.toLowerCase();
        System.out.println(source.length()-source.replaceAll(target,"").length());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String source = in.nextLine();
        source = source.toLowerCase();
        Character target = in.nextLine().toLowerCase().charAt(0);
        Integer count = 0;
        for (int i = 0; i < source.length(); i++) {
            if (target == source.charAt(i)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
