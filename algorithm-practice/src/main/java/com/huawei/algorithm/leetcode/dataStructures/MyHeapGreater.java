package com.huawei.algorithm.leetcode.dataStructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author king
 * 加强堆  给每个元素添加一个反向索引表
 * @date 2023/6/24-14:36
 * @Desc
 */
public class MyHeapGreater<T> {
    private List<T> arr;
    private int heapSize;
    private Map<T, Integer> heapMap;

    private Comparator<? super T> comp;

    public MyHeapGreater(Comparator<T> comp) {
        arr = new ArrayList<>();
        heapSize = 0;
        heapMap = new HashMap<>();
        this.comp = comp;
    }

    public T peek() {
        if (heapSize == 0) {
            throw new RuntimeException("没有元素了");
        }
        return arr.get(0);
    }

    public void push(T t) {
        arr.add(t);
        heapMap.put(t, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        if (heapSize == 0) {
            throw new RuntimeException("没有元素了");
        }
        T ans = arr.get(0);
        swap(0, heapSize - 1);
        arr.remove(--heapSize);
        heapMap.remove(ans);
        heapify(0);
        return ans;
    }

    private void heapInsert(int k) {
        while (comp.compare(arr.get(k), arr.get((k - 1) / 2)) < 0) {
            swap(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    private void heapify(int k) {
        int left = k * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comp.compare(arr.get(left + 1), arr.get(left)) < 0 ? left + 1 : left;
            int index = comp.compare(arr.get(best), arr.get(k)) < 0 ? best : k;
            if (index == k) {
                break;
            }
            swap(index, k);
            k = index;
            left = k * 2 + 1;
        }
    }

    public void remove(T obj) {
        if (Objects.isNull(heapMap.get(obj))) {
            throw new RuntimeException("集合中没有该元素");
        }
        int k = heapMap.get(obj);
        swap(k, heapSize - 1);
        heapMap.remove(obj);
        arr.remove(--heapSize);
        if (k != heapSize) {
            resign(k);
        }
    }

    public void resign(int k) {
        heapInsert(k);
        heapify(k);
    }

    private void swap(int m, int n) {
        T obj1 = arr.get(m);
        T obj2 = arr.get(n);
        arr.set(m, obj2);
        arr.set(n, obj1);
        heapMap.put(obj1, n);
        heapMap.put(obj2, m);
    }

    public static void main(String[] args) {
        MyHeapGreater<Integer> heapGreater = new MyHeapGreater<>((Comparator.comparingInt(o -> o)));
        heapGreater.push(4);
        heapGreater.push(5);
        System.out.println(heapGreater.peek());
        heapGreater.push(3);
        System.out.println(heapGreater.peek());
        heapGreater.push(2);
        System.out.println(heapGreater.pop());
        heapGreater.push(2);
        heapGreater.remove(3);
        System.out.println(heapGreater.pop());
    }
}
