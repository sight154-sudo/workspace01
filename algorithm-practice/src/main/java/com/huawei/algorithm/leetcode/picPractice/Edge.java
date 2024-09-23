package com.huawei.algorithm.leetcode.picPractice;

/**
 * @author king
 * @date 2024/5/25-22:22
 * @Desc
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;
    public Edge(int w, Node from ,Node to) {
        weight = w;
        this.from = from;
        this.to = to;
    }

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }
}
