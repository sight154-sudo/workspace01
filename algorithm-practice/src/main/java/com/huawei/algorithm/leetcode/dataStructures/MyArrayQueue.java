package com.huawei.algorithm.leetcode.dataStructures;

/**
 * 使用数组实现队列
 *
 * @author king
 * @date 2023/6/6-23:30
 * @Desc
 */
public class MyArrayQueue<T> {
    private T[] arr;
    private int size;
    private int push;
    private int pop;
    private int init;

    public MyArrayQueue(int init) {
        arr = (T[]) new Object[init];
    }

    public T offer(T t) {
        if (size >= arr.length) {
            resize();
        }
        size++;
        arr[push] = t;
        push = nextIndex(push);
        return t;
    }

    private void resize() {

    }

    public T poll() {
        if (size == 0 || push == pop) {
            throw new RuntimeException("队列中没有元素了");
        }
        size--;
        T t = arr[pop];
        pop = nextIndex(pop);
        return t;
    }

    private int nextIndex(int idx) {
        return idx + 1 == size ? 0 : idx + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }


}
