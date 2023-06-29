package com.huawei.algorithm.leetcode.dataStructures;

import org.apache.poi.ss.formula.functions.T;

import java.util.Comparator;

/**
 * @author king
 * @date 2023/6/8-22:42
 * @Desc
 */
public class MyArrayMinStack {

    int[] data;
    int[] min;
    int size;
    int init;
    int push;

    public MyArrayMinStack(int init) {
        data  = new int[init];
        min  = new int[init];
    }

    public void push(int t) {
        if (size == data.length) {
            resize();
        }
        size++;
        data[push] = t;
        if (push == 0) {
            min[push] = t;
            push++;
            return;
        }
        if (t < min[push-1]) {
            min[push] = t;
        } else {
            min[push] = min[push-1];
        }
        push++;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素了!!!");
        }
        size--;
        return data[--push];
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素了!!!");
        }
        return data[push-1];
    }

    public int getMin() {
        return min[push-1];
    }

    private void resize() {
        int[] newObj = new int[data.length * 2];
        int[] newObj2 = new int[data.length * 2];
        System.arraycopy(data, 0 , newObj, 0, data.length);
        System.arraycopy(min, 0, newObj2, 0 , min.length);
        data = newObj;
        min = newObj2;
    }

    public static void main(String[] args) {
        MyArrayMinStack arrayMinStack = new MyArrayMinStack(5);
        arrayMinStack.push(10);
        arrayMinStack.push(11);
        arrayMinStack.push(12);
        System.out.println(arrayMinStack.pop());
        arrayMinStack.push(9);
        arrayMinStack.push(8);
        arrayMinStack.push(11);
        System.out.println(arrayMinStack.getMin());
    }

}
