package com.huawei.algorithm.leetcode.linkedPractice;

import com.huawei.algorithm.leetcode.linkedPractice.BinaryTreeNode;
import com.huawei.algorithm.leetcode.linkedPractice.ListNode;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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

    public static void preOrderNoRecursion(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        List<T> list = new ArrayList<>();
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.push(head);
                list.add(head.val);
                head = head.left;
            }
            if (!stack.isEmpty()) {
                head = stack.pop();
                head = head.right;
            }
        }
        System.out.println(list);
    }

    public static void preOrderNoRecursion1(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        List<T> list = new ArrayList<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            BinaryTreeNode<T> cur = stack.pop();
            list.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println(list);
    }

    public static void midOrderBinaryTreeNode(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        preOrderBinaryTreeNode(head.left);
        System.out.print(head.val+"->");
        preOrderBinaryTreeNode(head.right);
    }

    public static void midOrderPrint(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        List<T> list = new ArrayList<>();
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            if (!stack.isEmpty()) {
                head = stack.pop();
                list.add(head.val);
                head = head.right;
            }
        }
        System.out.println(list);
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
        if (head == null) {
            return;
        }
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> pre = null;
        List<T> list = new ArrayList<>();
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            head = stack.peek();
            if (head.right == null || head.right == pre) {
                stack.pop();
                list.add(head.val);
                pre = head;
                head = null;
            } else {
                head = head.right;
            }
        }
        System.out.println(list);
    }

    public static void postOrderBinaryTreeNodeByRecursion1(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        Stack<BinaryTreeNode<T>> ans = new Stack<>();
        stack.push(head);
        List<T> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            BinaryTreeNode<T> cur = stack.pop();
            ans.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!ans.isEmpty()) {
            list.add(ans.pop().val);
        }
        System.out.println(list);
    }

    public static void levelPrint(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(head);
        List<T> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> cur = queue.poll();
            list.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        System.out.println(list);
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
