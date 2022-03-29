package com.huawei.queue;

/**
 * @author king
 * @date 2022/3/29-23:51
 * @Desc 顺序队列  队列特性：先进先出
 */
public class ArrayQueue {
    // 初始化大小
    private String[] item;
    private int n;
    private int head;
    private int tail;

    public ArrayQueue(){
        item = new String[10];

    }
}
