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
        if (head == null) {
            return;
        }
        ListNode cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            if (cur.next == null) {
                sb.append(cur.val);
                break;
            }
            sb.append(cur.val+"->");
            cur = cur.next;
        }
        System.out.println(sb);
    }

    /**
     * 构建链表
     * @param arr
     * @return
     */
    public static ListNode constructNode(int[] arr) {
        ListNode head = new ListNode();
        ListNode cur = head;
        for (int i : arr) {
            ListNode tmp = new ListNode(i, null);
            cur.next = tmp;
            cur = tmp;
        }
        return head.next;
    }

    public static void preOrderBinaryTreeNode(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val+"->");
        preOrderBinaryTreeNode(head.left);
        preOrderBinaryTreeNode(head.right);
    }

    public static void midOrderBinaryTreeNode(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        preOrderBinaryTreeNode(head.left);
        System.out.print(head.val+"->");
        preOrderBinaryTreeNode(head.right);
    }

    public static void postOrderBinaryTreeNode(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        preOrderBinaryTreeNode(head.left);
        preOrderBinaryTreeNode(head.right);
        System.out.print(head.val+"->");
    }

    public static void postOrderBinaryTreeNodeByRecursion(BinaryTreeNode<T> head) {

    }

    public static BinaryTreeNode constructBinaryTreeNode(int[] arr) {
        if (arr == null || arr.length < 2) {
            return null;
        }
        return constructBinaryTreeNode(arr, 1);
    }

    private static BinaryTreeNode constructBinaryTreeNode(int[] arr, int index) {
        if (index >= arr.length || arr[index] == -1 ) {
            return null;
        }
        BinaryTreeNode<Integer> head = new BinaryTreeNode(arr[index]);
        head.left  = constructBinaryTreeNode(arr, 2*index);
        head.right = constructBinaryTreeNode(arr, 2*index+1);
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 1,2,2,-1,3,3,-1};
        BinaryTreeNode head = NodeUtils.constructBinaryTreeNode(arr);
        NodeUtils.preOrderBinaryTreeNode(head);
    }

}
