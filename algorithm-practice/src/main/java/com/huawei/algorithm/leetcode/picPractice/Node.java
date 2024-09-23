package com.huawei.algorithm.leetcode.picPractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author king
 * @date 2024/5/25-22:21
 * @Desc
 */
public class Node<T> {
    public T val; // 数据
    public int in; // 入席
    public int out; // 出席
    public List<Node> next;
    public List<Edge> edge;

    public Node(T t) {
        this.val = t;
        this.in = 0;
        this.out = 0;
        this.next = new ArrayList<>();
        this.edge = new ArrayList<>();
    }

    public static void main(String[] args) {
        Map<int[], Integer> map = new HashMap<>();
        int[] a1 = {0,1};
        map.put(a1, 1);
        int[] a2 = {0,1};
        map.put(a2, 2);
        System.out.println(map);
    }



}
