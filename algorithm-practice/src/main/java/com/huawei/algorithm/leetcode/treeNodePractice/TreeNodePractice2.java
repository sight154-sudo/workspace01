package com.huawei.algorithm.leetcode.treeNodePractice;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author king
 * @date 2023/10/31-23:13
 * @Desc
 */
public class TreeNodePractice2 {

    /**
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * leetCode 116
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node cur = root;
        Deque<Node> deque = new LinkedList<>();
        deque.addFirst(cur);
        while (!deque.isEmpty()) {
            int size = deque.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node node = deque.removeLast();
                if (pre != null) {
                    pre.next = node;
                }
                pre = node;
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
        }
        return root;
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
