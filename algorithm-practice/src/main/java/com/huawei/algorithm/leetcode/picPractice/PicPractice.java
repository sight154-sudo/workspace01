package com.huawei.algorithm.leetcode.picPractice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author king
 * @date 2023/7/23-23:25
 * @Desc
 */
public class PicPractice {

    public static class Node<V> {
        V v;

        public Node(V v) {
            this.v = v;
        }
    }

    public static class UnionSet<V> {
        Map<V, Node<V>> nodes = new HashMap<>();
        Map<Node<V>, Node<V>> parent = new HashMap<>();
        Map<Node<V>, Integer> nodeSize = new HashMap<>();

        public UnionSet(List<V> values) {
            for (V v : values) {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parent.put(node, node);
                nodeSize.put(node, 1);
            }
        }

        public Node<V> findParent(Node<V> v) {
            Stack<Node> stack = new Stack<>();
            while (v != parent.get(v)) {
                stack.add(v);
                v = parent.get(v);
            }
            Node<V> parentNode = v;
            while (!stack.isEmpty()) {
                parent.put(stack.pop(), parentNode);
            }
            return parentNode;
        }

        public boolean isSameSet(V v1, V v2) {
            return findParent(nodes.get(v1)) == findParent(nodes.get(v2));
        }

        public void union(V v1, V v2) {
            Node<V> p1 = findParent(nodes.get(v1));
            Node<V> p2 = findParent(nodes.get(v2));
            if (p1 != p2) {
                int i1 = nodeSize.get(p1);
                int i2 = nodeSize.get(p2);
                Node<V> big = i1 > i2 ? p1 : p2;
                Node<V> small = big == p1 ? p2 : p1;
                parent.put(small, big);
                nodeSize.put(big, i1 + i2);
                nodeSize.remove(small);
            }
        }

    }

}
