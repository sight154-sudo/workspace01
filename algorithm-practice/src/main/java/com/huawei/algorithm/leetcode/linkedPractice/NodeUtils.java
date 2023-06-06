package com.huawei.algorithm.leetcode.linkedPractice;

import com.huawei.algorithm.leetcode.linkedPractice.BinaryTreeNode;
import com.huawei.algorithm.leetcode.linkedPractice.ListNode;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author king
 * @date 2023/6/7-0:15
 * @Desc
 */
public class NodeUtils {

    /**
     * 打印链表
     * @param head
     */
    public static void printListNode(ListNode head) {

    }

    /**
     * 构建链表
     * @param arr
     * @return
     */
    public static ListNode constructListNode(int[] arr) {

        return null;
    }

    public static void preOrderBinaryTreeNode(BinaryTreeNode<T> head) {
        if (head != null) {
            return;
        }
        System.out.print(head.val+"->");
        preOrderBinaryTreeNode(head.left);
        preOrderBinaryTreeNode(head.right);
    }

    public static void postOrderBinaryTreeNode(BinaryTreeNode<T> head) {
        if (head != null) {
            return;
        }
        preOrderBinaryTreeNode(head.left);
        preOrderBinaryTreeNode(head.right);
        System.out.print(head.val+"->");
    }

    public static void postOrderBinaryTreeNodeByRecursion(BinaryTreeNode<T> head) {

    }

    public static <T> BinaryTreeNode<T> constructBinaryTreeNode(int[] arr) {

        return null;
    }

}
