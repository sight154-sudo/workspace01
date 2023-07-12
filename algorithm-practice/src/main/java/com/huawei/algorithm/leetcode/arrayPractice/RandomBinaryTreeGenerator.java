package com.huawei.algorithm.leetcode.arrayPractice;

import java.util.Random;

public class RandomBinaryTreeGenerator {
    private static Random random = new Random();

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static Node generateRandomBinaryTree(int depth) {
        if (depth <= 0) {
            if (random.nextBoolean()) {
                return null; // 50% 的概率返回空节点
            }
            return new Node(random.nextInt(100) + 1); // 50% 的概率返回非空节点
        }

        Node root = new Node(random.nextInt(100) + 1);
        root.left = generateRandomBinaryTree(depth - 1);
        root.right = generateRandomBinaryTree(depth - 1);

        return root;
    }

    public static void printBinaryTree(Node root) {
        if (root == null) {
            System.out.println("null");
            return;
        }

        System.out.println(root.value);
        printBinaryTree(root.left);
        printBinaryTree(root.right);
    }

    public static void main(String[] args) {
        // 生成一个深度为3的随机二叉树
        Node randomTree = generateRandomBinaryTree(3);

        // 打印随机二叉树的值
        printBinaryTree(randomTree);
    }
}
