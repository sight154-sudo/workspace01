package com.huawei.algorithm.leetcode.linkedPractice;

/**
 * @author king
 * @date 2023/6/6-23:24
 * @Desc
 */
public class DoubleListNode<T> {
    public T val;
    public DoubleListNode pre;
    public DoubleListNode next;

    public DoubleListNode(T val) {
        this.val = val;
    }

    public DoubleListNode(T val, DoubleListNode pre, DoubleListNode next) {
        this.val = val;
        this.pre = pre;
        this.next = next;
    }
}
