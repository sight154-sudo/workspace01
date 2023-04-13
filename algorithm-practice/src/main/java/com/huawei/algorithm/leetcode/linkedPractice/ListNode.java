package com.huawei.algorithm.leetcode.linkedPractice;

import com.huawei.algorithm.Node;

/**
 * @author king
 * @date 2023/4/4-0:02
 * @Desc
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(){};

    public ListNode(int val) {
        this.val= val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

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
}
