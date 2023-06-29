package com.huawei.algorithm.leetcode.dataStructures;

 import java.util.LinkedList;

/**
 * @author king
 * @date 2023/6/8-23:16
 * @Desc
 */
public class MyQueueStack<T> {

    LinkedList<T> pushS;

    LinkedList<T> popS;

    int size;

    public MyQueueStack() {
        pushS = new LinkedList<>();
        popS = new LinkedList<>();
    }

    public void push(T t) {
        size++;
        while (!popS.isEmpty()) {
            pushS.offer(popS.poll());
        }
        LinkedList tmp = pushS;
        pushS = popS;
        popS = tmp;
    }

    public T pop() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素了");
        }
        size--;
        return popS.poll();
    }

    public T peek() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素了");
        }
        return popS.peek();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        MyQueueStack<Integer> queueStack = new MyQueueStack<>();
        queueStack.push(1);
        queueStack.push(2);
        System.out.println(queueStack.peek());
        System.out.println(queueStack.pop());
        /*queueStack.push(3);
        queueStack.push(4);
        queueStack.push(5);
        System.out.println(queueStack.pop());
        System.out.println(queueStack.pop());
        System.out.println(queueStack.pop());
        System.out.println(queueStack.pop());*/
    }

}
