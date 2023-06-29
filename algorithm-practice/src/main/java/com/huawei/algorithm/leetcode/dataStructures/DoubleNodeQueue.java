package com.huawei.algorithm.leetcode.dataStructures;

import com.huawei.algorithm.leetcode.linkedPractice.DoubleListNode;

/**
 * 双向链表实现队列
 * @author king
 * @date 2023/6/6-23:29
 * @Desc
 */
public class DoubleNodeQueue<T> {
    private DoubleListNode<T> head; // 头指针

    private DoubleListNode<T> tail; // 尾指针

    public void addHead(T t) {
        DoubleListNode<T> node = new DoubleListNode<T>(t);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;
        }
    }

    public T removeHead() {
        if (head == null) {
            return null;
        }
        DoubleListNode cur = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            cur.next = null;
            head.pre = null;
        }
        return (T) cur.val;
    }

    public void addTail(T t) {
        DoubleListNode<T> node = new DoubleListNode<T>(t);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.pre = tail;
            tail.next = node;
            tail = node;
        }
    }
}
