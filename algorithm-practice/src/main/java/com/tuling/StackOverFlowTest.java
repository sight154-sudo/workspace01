package com.tuling;

/**
 * @author king
 * @date 2023/5/19-0:10
 * @Desc
 */
public class StackOverFlowTest {

    static int count = 0;

    public static void redo () {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            // 设置-Xss128k 线程栈的大小  默认为1M
            redo();
        } catch (Error e) {
            e.printStackTrace();
            System.out.println(count);
        }
    }
}
