package com.huawei.algorithm.leetcode.linkedPractice;

import java.util.Objects;

public class BinaryTreeNode<T> {
    T val;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode() {
        this.left = null;
        this.right = null;
    }

    BinaryTreeNode(T val) {
        this.val = val;
    }

    BinaryTreeNode(T val, BinaryTreeNode left, BinaryTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public <T> BinaryTreeNode<T> buildBinaryTreeNode(T[] arr, int index) {
        if (arr.length == 0) {
            return new BinaryTreeNode<T>();
        }
        if (index >= arr.length) {
            return null;
        }
        if (arr[index] == null) {
            return null;
        }
        BinaryTreeNode<T> cur = new BinaryTreeNode<>(arr[index]);
        cur.left = buildBinaryTreeNode(arr, 2 * index);
        cur.right = buildBinaryTreeNode(arr, 2 * index + 1);
        return cur;
    }

    public void printBinaryTreeNode() {
        if (Objects.isNull(this)) {
            return;
        }
        System.out.print(this.val+"->");
        if (this.left != null) {
            this.left.printBinaryTreeNode();
        }
        if (this.right != null)
        this.right.printBinaryTreeNode();
    }

}