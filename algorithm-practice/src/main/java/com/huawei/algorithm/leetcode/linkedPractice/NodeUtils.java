package com.huawei.algorithm.leetcode.linkedPractice;

import com.huawei.algorithm.leetcode.linkedPractice.BinaryTreeNode;
import com.huawei.algorithm.leetcode.linkedPractice.ListNode;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author king
 * @date 2023/6/7-0:15
 * @Desc
 */
public class NodeUtils<T> {

    /**
     * 打印链表
     *
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
            sb.append(cur.val + "->");
            cur = cur.next;
        }
        System.out.println(sb);
    }

    /**
     * 构建链表
     *
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

    public static <T> void preOrderBinaryTreeNode(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + "->");
        preOrderBinaryTreeNode(head.left);
        preOrderBinaryTreeNode(head.right);
    }

    public static <T> void preOrderNoRecursion(BinaryTreeNode<T> head) {
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

    public static <T> void preOrderNoRecursion1(BinaryTreeNode<T> head) {
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

    public static <T> void midOrderBinaryTreeNode(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        preOrderBinaryTreeNode(head.left);
        System.out.print(head.val + "->");
        preOrderBinaryTreeNode(head.right);
    }

    public static <T> void midOrderPrint(BinaryTreeNode<T> head) {
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
    public static <T> void postOrderBinaryTreeNode(BinaryTreeNode<T> head) {
        if (head == null) {
            return;
        }
        preOrderBinaryTreeNode(head.left);
        preOrderBinaryTreeNode(head.right);
        System.out.print(head.val + "->");
    }

    public static <T> void postOrderBinaryTreeNodeByRecursion(BinaryTreeNode<T> head) {
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

    public static <T> void postOrderBinaryTreeNodeByRecursion1(BinaryTreeNode<T> head) {
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

    public static <T> void levelPrint(BinaryTreeNode<T> head) {
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

    public static <T> BinaryTreeNode<T> constructBinaryTreeNode(String str) {
        char[] chs = str.toCharArray();
        // {3{4,{7{,9}}
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode head = null;
        BinaryTreeNode node = null;
        // 当遇到{,
        int i = 0;
        int k = 1;
        while (i < chs.length) {
            switch (chs[i]){
                case '{':
                    k = 1;
                    stack.push(node);
                    break;
                case ',' :
                    k = 2;
                    break;
                case '}':
                    stack.pop();
                    break;
                default:
                    node = new BinaryTreeNode(chs[i]);
                    if (head == null) {
                        head = node;
                    } else {
                        BinaryTreeNode peek = stack.peek();
                        if (k == 1) {
                            peek.left = node;
                        } else {
                            peek.right = node;
                        }
                    }
            }
            i++;
        }
        return head;
    }

    private static BinaryTreeNode constructBinaryTreeNode(int[] arr, int index) {
        if (index >= arr.length || arr[index] == -1) {
            return null;
        }
        BinaryTreeNode<Integer> head = new BinaryTreeNode(arr[index]);
        head.left = constructBinaryTreeNode(arr, 2 * index);
        head.right = constructBinaryTreeNode(arr, 2 * index + 1);
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 1, 2, 2, -1, 3, 3, -1};
        BinaryTreeNode head = NodeUtils.constructBinaryTreeNode(arr);
        NodeUtils.preOrderBinaryTreeNode(head);
        System.out.println();
        BinaryTreeNode root = NodeUtils.generateRandomTreeNode(NodeUtils.generateRandomArray(10, 100, false));
        Queue queue = NodeUtils.levelNodeSerialize(root);
        System.out.println(queue);
        BinaryTreeNode root1 = NodeUtils.generateRandomBinaryTree(3, 100, new HashSet<Integer>());
        Queue queue1 = NodeUtils.levelNodeSerialize(root1);
        System.out.println(queue1);

        String str ="{3{4{6,5},7{,9}}";
        BinaryTreeNode<Integer> head2
                = NodeUtils.constructBinaryTreeNode(str);
        NodeUtils.levelPrint(head2);
    }

    public static Queue<String> levelNodeSerialize(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        Queue<String> ans = new LinkedList<>();
        queue.add(root);
        ans.add(String.valueOf(root.val));
        while (!queue.isEmpty()) {
            BinaryTreeNode<Integer> cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                ans.add(String.valueOf(cur.left.val));
            } else {
                ans.add(null);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                ans.add(String.valueOf(cur.right.val));
            } else {
                ans.add(null);
            }
        }
        return ans;
    }

    public static int[] generateRandomArray(int size, int maxVal, boolean repeat) {
        int[] arr = new int[size];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            int val = generateRandomNum(maxVal);
            while (!repeat && set.contains(val)) {
                val = generateRandomNum(maxVal);
                set.add(val);
            }
            arr[i] = val;
        }
        return arr;
    }

    public static BinaryTreeNode generateRandomTreeNode(int[] arr) {
        BinaryTreeNode root = null;
        for (int i = 0; i < arr.length; i++) {
            root = generateRandomNode(root, arr[i]);
        }
        return root;
    }

    public static BinaryTreeNode copyTreeNode(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        BinaryTreeNode head = new BinaryTreeNode(root.val);
        head.left = copyTreeNode(root.left);
        head.right = copyTreeNode(root.right);
        return head;
    }

    private static BinaryTreeNode generateRandomNode(BinaryTreeNode root, int val) {
        if (root == null) {
            return new BinaryTreeNode(val);
        }
        if (Math.random() > 0.5) {
            root.left = generateRandomNode(root.left, val);
        } else {
            root.right = generateRandomNode(root.right, val);
        }
        return root;
    }

    public static int generateRandomNum(int maxVal) {
        return (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * maxVal);
    }

    public static BinaryTreeNode generateRandomBinaryTree(int deep, int maxVal, Set<Integer> set) {
        if (deep <= 0) {
            return null;
        }
        int val = generateRandomNum(maxVal);
        while (set.contains(val)) {
            val = generateRandomNum(maxVal);
            set.add(val);
        }
        BinaryTreeNode head = new BinaryTreeNode<>(val);
        head.left = generateRandomBinaryTree(deep - 1, maxVal, set);
        head.right = generateRandomBinaryTree(deep - 1, maxVal, set);
        return head;
    }

    public static BinaryTreeNode generateRandomBinaryTree1(int deep, int maxVal, Set<Integer> set) {
        if (deep <= 0) {
            return null;
        }
        int val = generateRandomNum(maxVal);
        while (set.contains(val)) {
            val = generateRandomNum(maxVal);
            set.add(val);
        }
        BinaryTreeNode head = new BinaryTreeNode<>(val);
        head.left = generateRandomBinaryTree1(deep - 1, maxVal, set);
        head.right = generateRandomBinaryTree1(deep - 1, maxVal, set);
        return head;
    }

}
