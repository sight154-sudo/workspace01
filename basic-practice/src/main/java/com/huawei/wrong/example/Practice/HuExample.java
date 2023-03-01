package com.huawei.wrong.example.Practice;

import org.jetbrains.annotations.TestOnly;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * 开发常见错误用例
 *
 * @author king
 * @date 2023/2/21-23:17
 * @Desc
 */
public class HuExample {

    @Test
    public void testObjectsHash() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Objects.hash("112", 12, Arrays.hashCode(new int[]{1,2})));
        }
    }

    public static void main(String[] args) {
        Integer a = 128;
        Integer b = 128;
        // 默认情况下IntegerCache缓存-128到127的数值， 超过数值范围则返回新的对象,可以设置jvm参数，扩大缓存范围
        // -XX:AutoBoxCacheMax=1000  则结果返回为true
        System.out.println(a==b);
    }

}
