package com.huawei.od;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author king
 * @date 2022/8/29-22:48
 * @Desc
 */
public class Memory {

    /**
     * 请实现一个简易内存池,根据请求命令完成内存分配和释放。
     * 内存池支持两种操作命令，REQUEST和RELEASE，其格式为：
     * REQUEST=请求的内存大小 表示请求分配指定大小内存，如果分配成功，返回分配到的内存首地址；如果内存不足，或指定的大小为0，则输出error。
     * RELEASE=释放的内存首地址 表示释放掉之前分配的内存，释放成功无需输出，如果释放不存在的首地址则输出error。
     * <p>
     * 内存池总大小为100字节。
     * 内存池地址分配必须是连续内存，并优先从低地址分配。
     * 内存释放后可被再次分配，已释放的内存在空闲时不能被二次释放。
     * 不会释放已申请的内存块的中间地址。
     * 释放操作只是针对首地址所对应的单个内存块进行操作，不会影响其它内存块。
     * <p>
     * 首行为整数 N , 表示操作命令的个数，取值范围：0 < N <= 100。
     * <p>
     * 接下来的N行, 每行将给出一个操作命令，操作命令和参数之间用 “=”分割。
     */
    static class AllocatedMemory {
        private TreeMap<Integer, Integer> allocatedMap = new TreeMap<>();
        private Integer allocatedHead = 0;
        private Integer allocatedEnd = 100;

        public String request(int size) {
            int address_head = allocatedHead;
            if (size <= 0 || size > 100) {
                return "error";
            }
            if (allocatedMap.isEmpty()) {
                // 内存池中未占用内存， 直接存放
                allocatedMap.put(address_head, size);
            } else {
                // 获取内存池中，占用的地址，与长度
                ArrayList<Integer> list = new ArrayList<>(allocatedMap.keySet());
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) - address_head >= size) {
                        // 如果空闲内存空间大于等于需要分配的内存，直接分配
                        allocatedMap.put(address_head, address_head + size);
                    } else {
                        address_head = allocatedMap.get(list.get(i));
                    }
                }
                if (allocatedEnd - address_head >= size) {
                    allocatedMap.put(address_head, address_head+size);
                } else {
                    return "error";
                }
            }
            return String.valueOf(address_head);
        }

        public boolean release(int head) {
            if (allocatedMap.containsKey(head)) {
                allocatedMap.remove(head);
                return true;
            }
            return false;
        }

        public static void main(String[] args) {
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
            AllocatedMemory memory = new AllocatedMemory();
            for (int i = 0; i < arrs.length; i++) {
                int sign = arrs[i][0];
                int size = arrs[i][1];
                if (sign == -1) {
                    boolean flag = memory.release(size);
                    if (!flag) {
                        System.out.println("error");
                    }
                } else {
                    String s = memory.request(size);
                    System.out.println(s);
                }
            }
        }
    }
}
