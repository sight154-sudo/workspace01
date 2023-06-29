package com.huawei.algorithm.leetcode.linkedPractice;

import com.huawei.algorithm.Node;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author king
 * @date 2023/4/4-0:05
 * @Desc
 */
public class LinkedPractice {

    public ListNode reverseListNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public ListNode ReverseListNodeByRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = ReverseListNodeByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }


    @Test
    public void testReverseListNode() {
        ListNode listNode = NodeUtils.constructNode(new int[]{1, 2, 3});
        printNode(listNode);
        ListNode node = ReverseListNodeByRecursion(listNode);
        printNode(node);
    }

    @Test
    public void deleteDuplicatesTest() {
        int[] arr = {1, 1, 2, 3, 3};
        ListNode node = NodeUtils.constructNode(arr);
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
        head = head.buildBinaryTreeNode(arr, 1);
        head.printBinaryTreeNode();
    }


    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(BinaryTreeNode root) {
        if (root == null) {
            return false;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(BinaryTreeNode left, BinaryTreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    @Test
    public void reverseBetweenTest() {
        ListNode head = NodeUtils.constructNode(new int[]{1, 2, 3, 4, 5, 6});
        NodeUtils.printListNode(head);
        ListNode other = reverseBetween(head, 1, 6);
        NodeUtils.printListNode(other);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        ListNode pre = null;
        ListNode cur = head;
        // 记录翻转前的一个节点
        ListNode node = null;
        // 记录翻转后的一个节点;
        ListNode node2 = head;
        while (cur != null && m > 0) {
            if (m == 1) {
                node = pre;
                node2 = cur;
            }
            ListNode tmp = cur;
            cur = cur.next;
            pre = tmp;
            m--;
            n--;
        }
        while (cur != null && n > 0) {
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = pre;
            pre = tmp;
            n--;
        }
        if (node != null) {
            ListNode tmp = node.next;
            node.next = pre;
            tmp.next = cur;
            return head;
        }
        node2.next = cur;
        return pre;
    }

    @Test
    public void chooseSortNodeTest() {
        List<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(3);
        list.add(6);
        list.add(2);
        list.add(1);
        System.out.println(list);
        Collections.sort(list, Comparator.comparingInt(o -> o));
        System.out.println(list);
        ListNode head = NodeUtils.constructNode(new int[]{5, 2, 76, 2, 3, 1});
        NodeUtils.printListNode(head);
        ListNode head1 = quickNodeSort(head);
        NodeUtils.printListNode(head1);
    }

    public ListNode sortListNode(ListNode head) {
        // 使用选择排序
        if (head == null) {
            return head;
        }
        for (ListNode cur = head; cur != null; cur = cur.next) {
            for (ListNode node = cur.next; node != null; node = node.next) {
                if (cur.val > node.val) {
                    swap(cur, node);
                }
            }
        }
        return head;
    }

    public ListNode bubbleSortNode(ListNode head) {
        // 使用冒泡排序排序链表
        if (head == null) {
            return head;
        }
        for (ListNode cur = head; cur != null; cur = cur.next) {
            for (ListNode node = head; node.next != null; node = node.next) {
                if (node.val > node.next.val) {
                    swap(node, node.next);
                }
            }
        }
        return head;
    }

    public void swap(ListNode cur, ListNode node) {
        int tmp = cur.val;
        cur.val = node.val;
        node.val = tmp;
    }

    public ListNode mergeNodeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMidNode(head);
        // 拆分链表
        ListNode midNode = mid.next;
        mid.next = null;
        ListNode left = mergeNodeSort(head);
        ListNode right = mergeNodeSort(midNode);
        return mergeSort(left, right);
    }

    private ListNode mergeSort(ListNode left, ListNode right) {
        ListNode tmp = new ListNode();
        ListNode node = tmp;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                node.next = left;
                left = left.next;
            } else {
                node.next = right;
                right = right.next;
            }
            node = node.next;
        }
        while (left != null) {
            node.next = left;
            left = left.next;
            node = node.next;
        }
        while (right != null) {
            node.next = right;
            right = right.next;
            node = node.next;
        }
        return tmp.next;
    }

    private ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode quickNodeSort(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pivot = head;
        ListNode less = null;
        ListNode equal = null;
        ListNode great = null;
        while (head != null) {
            ListNode node = head.next;
            if (head.val < pivot.val) {
                head.next = less;
                less = head;
            } else if (head.val > pivot.val) {
                head.next = great;
                great = head;
            } else {
                head.next = equal;
                equal = head;
            }
            head = node;
        }
        less = quickNodeSort(less);
        great = quickNodeSort(great);
        return concatNode(less, equal, great);
    }

    private ListNode concatNode(ListNode less, ListNode equal, ListNode great) {
        ListNode node = new ListNode();
        ListNode cur = node;
        cur.next = less;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = equal;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = great;
        return node.next;
    }

}
