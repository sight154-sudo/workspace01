package com.huawei.algorithm.leetcode.dataStructures;

/**
 * 使用数组实现栈
 *
 * @author king
 * @date 2023/6/6-23:30
 * @Desc
 */
public class MyArrayStack<T> {
    private Object[] arr;
    private int index;
    private int size;

    public MyArrayStack(int init) {
        arr = new Object[init];
    }

    public T pop() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素了!!!");
        }
        size--;
        index--;
        return (T) arr[index];
    }

    public T peek() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素了!!!");
        }
        return (T) arr[index-1];
    }

    public T push(T t) {
        if (size >= arr.length) {
            resize();
        }
        size++;
        arr[index++] = t;
        return t;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        Object[] newObj = new Object[arr.length * 2];
        System.arraycopy(arr, 0 , newObj, 0, arr.length);
        arr = newObj;
    }

    public static void main(String[] args) {
        MyArrayStack<Integer> myArrayStack = new MyArrayStack<>(5);
        myArrayStack.push(1);
        myArrayStack.push(2);
        myArrayStack.push(3);
        myArrayStack.push(4);
        myArrayStack.push(5);
        myArrayStack.push(6);
        myArrayStack.push(7);
        System.out.println(myArrayStack.peek());
    }


    /**
     * TODO 实现一个特殊栈，既有栈的常见功能，也可以直接返回栈中的最小元素
     */

}
