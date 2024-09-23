package com.huawei.algorithm.leetcode.picPractice;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @date 2024/5/25-22:11
 * @Desc
 */
public class Graphy<T> {

    public HashMap<T, Node<T>> nodes;
    public HashSet<Edge> edges;

    public Graphy() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    /**
     * 有权重
     *
     * @param matrix
     * @return
     */
    public Graphy<Integer> createGraphy(int[][] matrix) {
        Graphy<Integer> graphy = new Graphy<>();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (!graphy.nodes.containsKey(from)) {
                graphy.nodes.put(from, new Node<>(from));
            }
            if (!graphy.nodes.containsKey(to)) {
                graphy.nodes.put(to, new Node<>(to));
            }
            Node<Integer> fromNode = graphy.nodes.get(from);
            Node<Integer> toNode = graphy.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.next.add(toNode);
            fromNode.edge.add(edge);
            toNode.edge.add(edge);
        }
        return graphy;
    }

    public static <T> Graphy<T> createGraphyT(T[][] matrix) {
        Graphy<T> graphy = new Graphy<>();
        for (int i = 0; i < matrix.length; i++) {
            T from = matrix[i][0];
            T to = matrix[i][1];
            if (!graphy.nodes.containsKey(from)) {
                graphy.nodes.put(from, new Node<T>(from));
            }
            if (!graphy.nodes.containsKey(to)) {
                graphy.nodes.put(to, new Node<T>(to));
            }
            Node<T> fromNode = graphy.nodes.get(from);
            Node<T> toNode = graphy.nodes.get(to);
            Edge edge = new Edge(fromNode, toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.next.add(toNode);
            fromNode.edge.add(edge);
            toNode.edge.add(edge);
        }
        return graphy;
    }

    public static <T> void bfs(Node<T> node) {
        Set<Node> set = new HashSet<>();
        Deque<Node> deque = new ArrayDeque<>();
        deque.addLast(node);
        set.add(node);
        while (!deque.isEmpty()) {
            Node cur = deque.removeLast();
            System.out.print(cur.val + "->");
            List<Node> next = cur.next;
            for (Node n : next) {
                if (!set.contains(n)) {
                    deque.addLast(n);
                    set.add(n);
                }
            }
        }
    }

    public static <T> void dfs(Node<T> node) {
        Set<Node> set = new HashSet<>();
        dfsRecursion(node, set);
    }

    public static <T> void dfsRecursion(Node<T> node, Set<Node> set) {
        if (set.contains(node)) {
            return;
        }
        System.out.print(node.val + "->");
        set.add(node);
        for (Node n : node.next) {
            if (!set.contains(n)) {
                dfsRecursion(n, set);
            }
        }
    }

    public static <T> void dfsStack(Node<T> node) {
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.print(node.val + "->");
        while (!stack.isEmpty()) {
            Node<T> cur = stack.pop();
            for (Node next : cur.next) {
                if (!set.contains(next)) {
                    stack.add(cur);
                    stack.add(next);
                    set.add(next);
                    System.out.print(next.val + "->");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        /*Character[][] chars = {
                {'A', 'B'},
                {'B', 'C'},
                {'B', 'D'},
                {'C', 'E'},
                {'A', 'E'},
                {'D', 'C'},
                {'E', 'F'},
                {'F', 'G'}
        };*/
        Character[][] chars = {
                {'A', 'B'},
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'K'},
                {'B', 'E'},
                {'E', 'C'},
                {'K', 'D'}
        };
        Graphy<Character> graphy = Graphy.createGraphyT(chars);
        Node<Character> first = graphy.nodes.get('A');
//        bfs(first);
        dfs(first);
        System.out.println();
        dfsStack(first);
    }

}
