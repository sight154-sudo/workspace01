package com.huawei.algorithm;/**
 * @author king
 * @date 2022/2/12-10:52
 * @Desc
 */

/**
 * @program: springboot-demo
 * @author: Mr.King
 * @create: 2022-02-12 10:52
 **/

public class Node {

    int val;
    Node next;

    public Node() {
        this.val = -1;
        this.next = null;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}
