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
}
