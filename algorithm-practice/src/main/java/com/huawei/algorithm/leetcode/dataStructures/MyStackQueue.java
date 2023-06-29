package com.huawei.algorithm.leetcode.dataStructures;

import java.util.Stack;

/**
 * 使用栈结构实现队列
 * @author king
 * @date 2023/6/8-23:08
 * @Desc
 */
public class MyStackQueue<T> {
    Stack<T> pushS;
    Stack<T> popS;
    int size;

    public MyStackQueue() {
        pushS = new Stack<>();
        popS = new Stack<>();
    }

    public void offer(T t) {
        size++;
        pushS.push(t);
    }

    public T poll() {
        if (size == 0) {
            throw new RuntimeException("队列中没有元素了");
        }
        if (popS.isEmpty()) {
            while (!pushS.isEmpty()) {
                popS.push(pushS.pop());
            }
        }
        size--;
        return popS.pop();
    }

    public T peek () {
        if (size == 0) {
            throw new RuntimeException("队列中没有元素了");
        }
        if (popS.isEmpty()) {
            while (!pushS.isEmpty()) {
                popS.push(pushS.pop());
            }
        }

        return popS.peek();
    }

    public boolean isEmpty() {
        return size==0;
    }

    public static void main(String[] args) {
        MyStackQueue<Integer> stackQueue = new MyStackQueue<>();
        stackQueue.offer(1);
        stackQueue.offer(2);
        System.out.println(stackQueue.peek());
        System.out.println(stackQueue.poll());
        System.out.println(stackQueue.poll());
        stackQueue.offer(3);
        System.out.println(stackQueue.peek());
        System.out.println(stackQueue.poll());
        stackQueue.offer(4);
        stackQueue.offer(5);
        System.out.println(stackQueue.poll());
        System.out.println(stackQueue.poll());
        System.out.println(stackQueue.poll());
        System.out.println(stackQueue.poll());
    }

}
