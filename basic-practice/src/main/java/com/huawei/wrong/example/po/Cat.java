package com.huawei.wrong.example.po;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author king
 * @date 2023/2/22-0:37
 * @Desc
 */
public class Cat extends Animal {
    private String function;
    private String desc;

    public static void main(String[] args) {
        Animal a1 = new Dog();
        Animal a2 = new Cat();
        System.out.println(a1.getClass());
        System.out.println(a2.getClass());

        List<String> list = Stream.of("11", "222", "333").collect(Collectors.toList());
        List<String> strings = list.subList(1, 4);
        System.out.println(strings);
        /*int i = 0;
        for (int j = 0; j < 10; j++) {
            try {
                System.out.println("aaa");
                throw new RuntimeException();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                continue;
            }
        }
*/
    }
}
