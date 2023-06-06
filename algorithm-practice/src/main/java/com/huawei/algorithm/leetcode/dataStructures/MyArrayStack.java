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
        return (T) arr[index];
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

}
