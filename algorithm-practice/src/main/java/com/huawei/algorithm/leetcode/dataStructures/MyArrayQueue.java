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
        Object[] obj = new Object[arr.length * 2];
        System.arraycopy(arr,pop, obj,0, arr.length-pop);
        if (pop == push) {
            for (int i = 0,index = arr.length-pop-1; i < pop; i++,index++) {
                obj[index] = arr[i];
            }
        }
        pop = 0;
        push = arr.length;
        arr = (T[]) obj;
    }

    public T poll() {
        if (size == 0) {
            throw new RuntimeException("队列中没有元素了");
        }
        size--;
        T t = arr[pop];
        pop = nextIndex(pop);
        return t;
    }

    private int nextIndex(int idx) {
        return idx + 1 < arr.length ? idx+1 : 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public static void main(String[] args) {
        MyArrayQueue<Integer> arrayQueue = new MyArrayQueue<Integer>(3);
        arrayQueue.offer(1);
        arrayQueue.offer(2);
        System.out.println(arrayQueue.poll());
        arrayQueue.offer(3);
        arrayQueue.offer(4);
        System.out.println(arrayQueue.poll());
        arrayQueue.offer(5);
        arrayQueue.offer(6);
    }


}
