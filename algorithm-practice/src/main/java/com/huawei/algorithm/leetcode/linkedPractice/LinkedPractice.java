package com.huawei.algorithm.leetcode.linkedPractice;

import com.huawei.algorithm.Node;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

/**
 * @author king
 * @date 2023/4/4-0:05
 * @Desc
 */
public class LinkedPractice {
    @Test
    public void deleteDuplicatesTest() {
        int[] arr = {1, 1, 2, 3, 3};
        ListNode node = ListNode.constructNode(arr);
//        ListNode node = new ListNode();
        printNode(node);
        ListNode head = deleteDuplicates(node);
        printNode(head);
    }

    /**
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        if (node == null) {
            return head;
        }
        ListNode next = node.next;
        while (next != null) {
            while (next != null && next.val == node.val) {
                next = next.next;
            }
            node.next = next;
            node = next;
            if (next != null) {
                next = next.next;
            }
        }
        return head;
    }


    public void printNode(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    @Test
    public void isSameTreeTest() {
    }

    /**
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * @param p
     * @param q
     * @return booean
     */
    public boolean isSameTree(BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            boolean b1 = isSameTree(p.left, q.left);
            boolean b2 = isSameTree(p.right, q.right);
            return b1 && b2;
        }
        return false;
    }

    @Test
    public void buildBinaryTreeNodeTest() {
        Integer[] arr = {null, 10, 5, null, null, 15};
        BinaryTreeNode<Integer> head = new BinaryTreeNode<>();
        head = head.buildBinaryTreeNode( arr, 1);
        head.printBinaryTreeNode();
    }

}
