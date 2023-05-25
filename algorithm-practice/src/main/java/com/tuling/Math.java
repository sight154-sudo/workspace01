package com.tuling;

import com.huawei.graphql.entity.User;

/**
 * @author king
 * @date 2023/5/8-23:47
 * @Desc
 */
public class Math {

    public static final Integer a = 123;
    public static User user = new User();

    public int compute() {
        int a = 1;
        int b = 4;
        int c = (a+b)*10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }
}
