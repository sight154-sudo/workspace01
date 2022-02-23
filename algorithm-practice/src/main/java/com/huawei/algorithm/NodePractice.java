package com.huawei.algorithm;

import org.junit.Test;

import java.util.ArrayList;

public class NodePractice {

    @Test
    public void reverseIterator() {
        int[] arr = {1, 3, 5, 1};
        Node head = constructNode(arr);
        printNode(head);
        Node node = reverseNodeRecursion(head);
        printNode(node);
    }

    /**
     * 翻转链表  使用迭代
     *
     * @param head
     * @return
     */
    public Node reverseNodeIterator(Node head) {
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            Node tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public Node reverseNodeRecursion(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node node = reverseNodeRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    @Test
    public void isPalindromeNode() {
        int[] arr = {1};
        Node head = constructNode(arr);
        printNode(head);
        System.out.println(isPalindromeNode1(head));
    }

    /**
     * 判断是否是回文链表
     *
     * @param head
     * @return
     */
    public Boolean isPalindromeNode(Node head) {
        // 存放在数组中，然后使用双指针判断
        ArrayList list = new ArrayList();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            if (list.get(i) != list.get(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 先找出链表的中点，再循环判断
     *
     * @param head
     * @return
     */
    public Boolean isPalindromeNode1(Node head) {
        // 1  -> 3 -> 5 -> 3 -> 1
        // 1 -> 2 -> 2 -> 1
        Node middle = middleNode(head);
        Node node = reverseNodeIterator(middle);
        while (node != null) {
            if (head.val != node.val) {
                return false;
            }
            node = node.next;
            head = head.next;
        }
        return true;
    }


    Node frontNode;

    /**
     * 使用递归判断 是否是回文链表
     *
     * @param head
     * @return
     */
    public Boolean isPalindromeNodeRecursion(Node head) {
        frontNode = head;
        return isPalindromeNodeRc(head);
    }

    private Boolean isPalindromeNodeRc(Node head) {
        if (head != null) {
            //先递归走到链表尾部  再从链表头部开始比较
            if (!isPalindromeNodeRc(head.next)) {
                return false;
            }
            if (head.val != frontNode.val) {
                return false;
            }
            frontNode = frontNode.next;
        }
        return true;
    }

    @Test
    public void middleNode() {
        int[] arr = {};
        Node head = constructNode(arr);
        printNode(head);
        Node node = middleNode(head);
        System.out.println(node.val);
    }

    /**
     * 找出链表的中心节点  使用快慢指针查找
     *
     * @param head
     * @return
     */
    public Node middleNode(Node head) {
        if (head == null) return head;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    @Test
    public void mergeTwoNode() {
        int[] arr1 = {1, 4, 7, 9};
        int[] arr2 = {1, 3, 6, 8};
        Node node1 = constructNode(arr1);
        Node node2 = constructNode(arr2);
        Node node = mergeTwoNodeRecursion(node1, node2);
        printNode(node);
    }

    /**
     * 合并两个有序链表
     *
     * @param node1
     * @param node2
     * @return
     */
    public Node mergeTwoNode(Node node1, Node node2) {
        Node tmp = new Node();
        Node cur = tmp;
        while (node1 != null && node2 != null) {
            if (node1.val > node2.val) {
                cur.next = node2;
                node2 = node2.next;
            } else {
                cur.next = node1;
                node1 = node1.next;
            }
            cur = cur.next;
        }
        if (node1 == null) {
            cur.next = node2;
        }
        if (node2 == null) {
            cur.next = node1;
        }
        return tmp.next;
    }

    public Node mergeTwoNodeRecursion(Node node1,Node node2) {
        //找到小的节点，然后用小的节点的下一个节点与另一个链表比较 依次类推
        if(node1 == null) {
            //表示node1已经比较完毕
            return node2;
        }else if(node2 == null) {
            return node1;
        }else if(node1.val > node2.val ) {
            node2.next = mergeTwoNodeRecursion(node1,node2.next);
            return node2;
        }else{
            node1.next = mergeTwoNodeRecursion(node1.next,node2);
            return node1;
        }
    }

    @Test
    public void hasCycle() {
        Node node1 = new Node(1,null);
        Node node2 = new Node(2,null);
        Node node3 = new Node(3,null);
        Node node4 = new Node(4,null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        Boolean flag = hasCycle(node1);
        System.out.println(flag);
    }

    /**
     * 判断链表是否有环 使用快慢指针
     *
     * @param node
     * @return
     */
    public Boolean hasCycle(Node node) {
        if (node == null) return false;
        Node slow = node;
        Node fast = node.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public Node constructNode(int[] arr) {
        Node head = new Node();
        Node cur = head;
        for (int i : arr) {
            Node tmp = new Node(i, null);
            cur.next = tmp;
            cur = tmp;
        }
        return head.next;
    }

    public void printNode(Node head) {
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
}
