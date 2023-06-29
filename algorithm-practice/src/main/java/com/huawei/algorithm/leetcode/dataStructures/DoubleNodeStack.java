package com.huawei.algorithm.leetcode.dataStructures;

import com.huawei.algorithm.leetcode.linkedPractice.DoubleListNode;

/**
 * 使用双向链表实现栈
 *
 * @author king
 * @date 2023/6/6-23:29
 * @Desc
 */
public class DoubleNodeStack<T> {
    private DoubleListNode<T> head;
    private DoubleListNode<T> end;
    private int size;

    public T pop() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素");
        }
        size--;
        T t = (T) end.val;
        end = end.pre;
        return t;
    }

    public T peek() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素");
        }
        return (T) end.val;
    }

    public T push(T t) {
        size++;
        if (head == null) {
            return init(t);
        }
        DoubleListNode node = new DoubleListNode(t, end, null);
        end = node;
        return t;
    }

    private T init(T t) {
        head = new DoubleListNode(t);
        end = head;
        return t;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        DoubleNodeStack<Integer> doubleNodeStack = new DoubleNodeStack<>();
        System.out.println(doubleNodeStack.isEmpty());
        doubleNodeStack.push(1);
        doubleNodeStack.push(3);
        doubleNodeStack.push(5);
        System.out.println(doubleNodeStack.isEmpty());
        System.out.println(doubleNodeStack.pop());
        System.out.println(doubleNodeStack.pop());
        System.out.println(doubleNodeStack.pop());
    }

}
