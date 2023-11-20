package com.huawei.algorithm.leetcode.linkedPractice;

import org.junit.Test;

import java.util.Stack;

public class MyLinkedList {

    private Node head;
    private int size;

    public MyLinkedList() {
        head = null;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node node = head;
        while (index-- > 0) {
            node = node.next;
        }
        return node.val;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        size++;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        size++;
        if (head == null) {
            head = node;
            return;
        }
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        size++;
        Node node = new Node(val);
        if (index == 0) {
            node.next = head;
            head = node;

            return;
        }
        Node cur = head;
        while (--index > 0) {
            cur = cur.next;
        }
        Node tmp = cur.next;
        cur.next = node;
        node.next = tmp;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        if (index == 0) {
            head = head.next;
            return;
        }
        Node pre = null;
        Node cur = head;
        while (index-- > 0) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
    }

    static class Node {
        int val;
        private Node next;

        public Node() {

        }

        public Node(int val) {
            this.val = val;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        head = stack.pop();
        cur = head;
        while (!stack.isEmpty()) {
            ListNode tmp = stack.pop();
            cur.next = tmp;
            cur = tmp;
        }
        cur.next = null;
        return head;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode node = cur.next;
            cur.next = pre;
            pre = cur;
            cur = node;
        }
        return pre;
    }

    public ListNode reverseRecursion(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode node = reverseRecursion(null, head);
        return node;
    }

    public ListNode reverseRecursion(ListNode pre, ListNode cur) {
        if (cur == null ) {
            return pre;
        }
        ListNode node = reverseRecursion(cur, cur.next);
        cur.next = pre;
        return node;
    }

    @Test
    public void reverseRecursionTest() {
        ListNode node = construct(new int[]{1, 2,3,4,5});
        printNode(node);
        ListNode head = reverseRecursion(node);
        printNode(head);
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode next = head.next;
        ListNode res = head.next;
        while (cur != null && next != null) {
            if (next.next == null) {
                next.next = cur;
                cur.next = null;
                break;
            }
            ListNode node = next.next.next;
            ListNode node1 = next.next;
            cur.next = node == null ? next.next : node;
            next.next = cur;
            cur = node1;
            next = node;
        }
        return res;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        slow.next = slow.next == null ? null : slow.next.next;
        return head;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 思路： 使用n记录两个链表长度的差, 两个链表同时走到结尾处， 若尾节点相同，则两个链表存在相交（保存两个链表都没环）
        // 根据n的正负值，决定哪个链表走
        int n = 0;
        ListNode end1 = headA;
        ListNode end2 = headB;
        while (end1.next != null) {
            n++;
            end1 = end1.next;
        }
        while (end2.next != null) {
            n--;
            end2 = end2.next;
        }
        if (end1 != end2) {
            return null;
        }
        end1 = n > 0?headA:headB;
        end2 = n > 0 ? headB:headA;
        n = Math.abs(n);
        while (n > 0) {
            end1 = end1.next;
            n--;
        }
        while (end1 != end2) {
            end1 = end1.next;
            end2 = end2.next;
        }
        return end1;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        boolean loop = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                loop = true;
                break;
            }
        }
        if (!loop) {
            // 表示链表无环
            return null;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }



    @Test
    public void removeNthFromEndTest() {
        ListNode node = construct(new int[]{1});
        printNode(node);
        ListNode head = removeNthFromEnd(node, 1);
        printNode(head);
    }

    @Test
    public void swapPairsTest() {
        ListNode node = construct(new int[]{1, 2, 3, 4, 5});
        printNode(node);
        ListNode head = swapPairs(node);
        printNode(head);
    }

    private void printNode(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            if (head.next == null) {
                sb.append(head.val).append("->null");
                break;
            }
            sb.append(head.val).append("->");
            head = head.next;
        }
        System.out.println(sb);
    }

    private ListNode construct(int[] arr) {
        ListNode head = new ListNode();
        ListNode cur = head;
        for (int i : arr) {
            ListNode tmp = new ListNode(i, null);
            cur.next = tmp;
            cur = tmp;
        }
        return head.next;
    }


    @Test
    public void reverseListTest() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode head = reverseList1(listNode1);
        System.out.println(listNode1);
    }

    public static void main1(String[] args) {
        MyLinkedList list = new MyLinkedList();
        System.out.println("list.get(0) = " + list.get(0));
        list.addAtHead(1);
        ;
        list.addAtIndex(0, 0);
        System.out.println("list.get(0) = " + list.get(0));
        list.addAtIndex(2, 2);
        System.out.println("list.get(2) = " + list.get(2));
        System.out.println("list.size = " + list.size);
        list.addAtTail(3);
        list.deleteAtIndex(1);
        list.deleteAtIndex(5);
    }
}