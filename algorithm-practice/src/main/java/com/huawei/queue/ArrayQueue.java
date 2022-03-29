package com.huawei.queue;

/**
 * @author king
 * @date 2022/3/29-23:51
 * @Desc 顺序队列  队列特性：先进先出
 */
public class ArrayQueue {
    // 初始化大小
    private String[] items;
    private int n;
    private int head;
    private int tail;

    // 申请一个大小容量为capacity的队列
    public ArrayQueue(int capacity) {
        items = new String[capacity];
        // 设置初始容量
        n = capacity;
    }

    /**
     * 入队   在队尾插入元素  在队首删除元素
     *
     * @param item
     * @return
     */
    public boolean enqueue(String item) {
        // 若n==tail,则表示元素已满，不能再放入元素
        if (n == tail) {
            if (head == 0) {
                return false;
            }
            // 当容器中没有空闲空间时，触发一次元素的迁移
            int len = tail - head;
            for (int i = head; i < len; i++) {
                items[i - head] = items[head + i];
            }
            head -= len;
            tail -= len;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        if (head == tail) {
            // 表示队列中已无元素
            return null;
        }
        String item = items[head];
        head++;
        return item;
    }


    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(6);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
    }
}
