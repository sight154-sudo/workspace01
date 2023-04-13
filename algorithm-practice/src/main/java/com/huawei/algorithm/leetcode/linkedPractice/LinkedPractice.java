package com.huawei.algorithm.leetcode.linkedPractice;

import com.huawei.algorithm.Node;
import org.junit.Test;

/**
 * @author king
 * @date 2023/4/4-0:05
 * @Desc
 */
public class LinkedPractice {
    @Test
    public void deleteDuplicatesTest() {
        int[] arr = {1,1,2,3,3};
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
}
