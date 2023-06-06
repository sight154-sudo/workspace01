package com.tuling;

import com.huawei.graphql.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author king
 * @date 2023/5/27-17:34
 * @Desc
 */
public class OOMTest {

    public static void main(String[] args) {
        List<User1> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (true) {
            list.add(new User1(i++, UUID.randomUUID().toString()));
            new User1(j--, UUID.randomUUID().toString());
        }

    }
}
