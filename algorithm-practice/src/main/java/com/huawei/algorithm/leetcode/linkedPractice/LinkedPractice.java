package com.huawei.algorithm.leetcode.linkedPractice;

import com.huawei.algorithm.Node;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public ListNode getMidNode1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode getMidPreNode(ListNode head) {
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }


    @Test
    public void getMidNodeTest() {
        ListNode head = NodeUtils.constructNode(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(getMidPreNode(head).val);
    }

    @Test
    public void palindromeNodeTest() {
        ListNode head = NodeUtils.constructNode(new int[]{1, 2, 3, 2, 1});
        System.out.println(palindromeNode(head));
        NodeUtils.printListNode(head);
    }

    public boolean palindromeNode(ListNode head) {
        // 判断回文链表 先找到链表的中点后翻转后半部分的链表，比较是否为回文链表
        if (head == null) {
            return false;
        }
        ListNode mid = getMidNode(head);
        ListNode node = mid.next;
        mid.next = null;
        ListNode pre = null;
        ListNode cur = null;
        while (node != null) {
            cur = node.next;
            node.next = pre;
            pre = node;
            node = cur;
        }
        boolean ans = true;
        while (pre != null && head != null) {
            if (pre.val != head.val) {
                ans = false;
            }
            cur = pre.next;
            pre.next = node;
            node = pre;
            pre = cur;
            head = head.next;
        }
        mid.next = node;
        return ans;
    }

    @Test
    public void threePartitionTest() {
        ListNode head = NodeUtils.constructNode(new int[]{1, 2, 3, 4, 2, 3, 5, 2, 4, 5, 6});
        NodeUtils.printListNode(head);
        ListNode node = threePartition2(head, 4);
        NodeUtils.printListNode(node);
    }

    /**
     * 将链表中按小，中，大三个部分形式存放
     *
     * @param head
     * @return
     */
    public ListNode threePartition(ListNode head, int val) {
        ListNode less = null;
        ListNode mid = null;
        ListNode greater = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            ListNode node = cur;
            if (cur.val > val) {
                node.next = greater;
                greater = node;
            } else if (cur.val < val) {
                node.next = less;
                less = node;
            } else {
                node.next = mid;
                mid = node;
            }
            cur = tmp;
        }
        cur = less;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = mid;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = greater;
        return less;
    }

    public ListNode threePartition2(ListNode head, int target) {
        ListNode lessHead = null;
        ListNode lessEnd = null;
        ListNode midHead = null;
        ListNode midEnd = null;
        ListNode greaterHead = null;
        ListNode greaterEnd = null;
        while (head != null) {
            if (head.val > target) {
                if (greaterEnd != null) {
                    greaterEnd.next = head;
                    greaterEnd = greaterEnd.next;
                } else {
                    greaterHead = head;
                    greaterEnd = head;
                }
            } else if (head.val < target) {
                if (lessEnd != null) {
                    lessEnd.next = head;
                    lessEnd = lessEnd.next;
                } else {
                    lessHead = head;
                    lessEnd = head;
                }
            } else {
                if (midEnd != null) {
                    midEnd.next = head;
                    midEnd = midEnd.next;
                } else {
                    midHead = head;
                    midEnd = head;
                }
            }
            head = head.next;
        }
        lessEnd.next = midHead;
        midEnd.next = greaterHead;
        return lessHead;
    }

    static class Node {
        int val;
        Node rand;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    @Test
    public void copyRandNodeTest() {
        Node a1 = new Node(1);
        Node a2 = new Node(2);
        Node a3 = new Node(3);
        a1.next = a2;
        a2.next = a3;
        a1.rand = a3;
        a2.rand = a1;
        a3.rand = null;
        Node node = copyRandNode(a1);
        System.out.println(node.val);
    }

    public Node copyRandNode1(Node src) {
        Map<Node, Node> map = new HashMap<>();
        Node head = src;
        while (src != null) {
            map.put(src, new Node(src.val));
            src = src.next;
        }
        Node cur = head;
        while (cur != null) {
            Node node = map.get(cur);
            node.next = map.get(cur.next);
            node.rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 拷贝链表
     *
     * @param head
     * @return
     */
    public Node copyRandNode(Node head) {
        Node node = head;
        while (node != null) {
            Node next = node.next;
            Node cur = new Node(node.val);
            node.next = cur;
            cur.next = next;
            node = next;
        }
        node = head;
        while (node != null) {
            Node next = node.next.next;
            node.next.rand = node.rand == null ? null : node.rand.next;
            node = next;
        }
        node = head;
        Node ans = head.next;
        while (node != null) {
            Node cur = node.next;
            Node next = node.next.next;
            node.next = next == null ? next : cur.next;
            cur.next = next == null ? next : next.next;
            node = next;
        }
        return ans;
    }

    public ListNode getLoopNode(ListNode head) {
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
            return null;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    @Test
    public void getNoLoopNodeTest() {
        ListNode node = NodeUtils.constructNode(new int[]{8, 4, 5});
        ListNode head1 = new ListNode(4);
        ListNode n2 = new ListNode(1);
        /*ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(29);*/
        ListNode head2 = new ListNode(5);
        ListNode m2 = new ListNode(6);
        ListNode m3 = new ListNode(1);
        n2.next = node;
//        n3.next = n4;
//        n4.next = node;
        m2.next = m3;
        m3.next = node;
        head1.next = n2;
        head2.next = m2;
        ListNode noLoopNode = getNoLoopNode(head1, head2);
        System.out.println(noLoopNode.val);
    }

    /**
     * 获取没有环的链表的相交节点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getNoLoopNode(ListNode headA, ListNode headB) {
        ListNode end1 = headA;
        ListNode end2 = headB;
        int n = 0;
        while (end1.next != null) {
            n++;
            end1 = end1.next;
        }
        while (end2.next != null) {
            n--;
            end2 = end2.next;
        }
        // 两条无环链表无相交节点
        if (end1 != end2) {
            return null;
        }
        end1 = n >= 0 ? headA : headB;
        end2 = end1 == headA ? headB : headA;
        n = Math.abs(n);
        // 长的链表先走n步
        while (n > 0) {
            n--;
            end1 = end1.next;
        }
        // 再一起走，若两个节点相同，表示为相交节点
        while (end1 != end2) {
            end1 = end1.next;
            end2 = end2.next;
        }
        return end1;
    }

    /**
     * 获取两个有环链表的相交节点
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode getBothLoopNode(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        if (loop1 == loop2) {
            ListNode node1 = head1;
            ListNode node2 = head2;
            int n = 0;
            while (node1 != loop1) {
                node1 = node1.next;
                n++;
            }
            while (node2 != loop2) {
                node2 = node2.next;
                n--;
            }
            node1 = n >= 0 ? head1 : head2;
            node2 = node1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                node1 = node1.next;
                n--;
            }
            while (node1 != node2) {
                node1 = node1.next;
                node2 = node2.next;
            }
            return node1;
        } else {
            ListNode cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return cur1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    @Test
    public void getIntersectionNodeTest() {
        ListNode head1 = NodeUtils.constructNode(new int[]{1, 2, 3, 4, 5});
        ListNode m2 = new ListNode(10);
        ListNode m3 = new ListNode(11);
        ListNode m4 = new ListNode(12);
        ListNode m5 = new ListNode(13);
        ListNode head2 = new ListNode(2);
        ListNode n2 = new ListNode(10);
        ListNode n3 = new ListNode(11);
        ListNode n4 = new ListNode(12);
        ListNode n5 = new ListNode(13);
        head2.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n3;
        ListNode intersectionNode = getIntersectionNode(head1, head2);
        System.out.println(intersectionNode == null);
    }

    public ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return getNoLoopNode(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return getBothLoopNode(head1, loop1, head2, loop2);
        }
        return null;
    }

    public BinaryTreeNode constructBinaryTreeNodeBypreMid(String pre, String mid) {
        char[] pres = pre.toCharArray();
        char[] mids = mid.toCharArray();
        int n = pres.length;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < mids.length; i++) {
            map.put(mids[i], i);
        }
        return constructBinary(pres, mids, 0, n-1, 0, n-1, map);
    }

    @Test
    public void constructBinaryNode() {
        NodeUtils.constructBinaryTreeNode(new int[]{-1, 5, 4, 6, 3 - 1, 2, 9, -1, -1, -1, -1, 7, 8});
        String pre = "54362789";
        String mid = "34572869";
        BinaryTreeNode binaryTreeNode = constructBinaryTreeNodeBypreMid(pre, mid);
        NodeUtils.preOrderBinaryTreeNode(binaryTreeNode);
    }

    private BinaryTreeNode constructBinary(char[] pres, char[] mids, int preLeft, int preRight, int inLeft, int inRight, Map<Character, Integer> map) {
        if (preLeft > preRight || inLeft > inRight || preLeft >= pres.length || preRight < 0 || inLeft >= pres.length || inRight < 0 ) {
            return null;
        }
        int pindex = map.get(pres[preLeft]);
        BinaryTreeNode root = new BinaryTreeNode(mids[pindex]);
        root.left = constructBinary(pres, mids, preLeft + 1, pindex - inLeft + preLeft, inLeft, pindex - 1, map);
        root.right = constructBinary(pres, mids, pindex - inLeft + preLeft + 1, preRight, pindex + 1, inRight, map);
        return root;
    }

    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。如果链表无环，则返回 null。
     * leetCode 142  判断链表是否有环， 若有环，返回入环节点
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        boolean flag = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                flag = true;
                break;
            }
        }
        if (!flag) {
            return null;
        }
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    static class MyLinkedList{
        int val;
        MyLinkedList prev;
        MyLinkedList next;
        MyLinkedList tail;
        int size;
        public MyLinkedList() {

        }

        public int get(int index) {

            return 0;
        }

        public void addAtHead(int val) {

        }

        public void addAtTail(int val) {

        }

        public void addAtIndex(int index, int val) {

        }

        public void deleteAtIndex(int index) {

        }
    }

}
