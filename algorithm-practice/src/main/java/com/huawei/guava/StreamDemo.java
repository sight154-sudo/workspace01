package com.huawei.guava;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.huawei.graphql.entity.Card;
import com.huawei.graphql.entity.User;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author king
 * @date 2022/11/30-23:52
 * @Desc
 */
public class StreamDemo {

    @Test
    public void test01() {
        ArrayList<User> users = Lists.newArrayList(
                new User(18, 1, "zhang3", new Card("1001", 1L)),
                new User(19, 2, "lisi", new Card("1002", 2L)),
                new User(15, 1, "wang4", new Card("1003", 1L)),
                new User(13, 3, "zhao6", new Card("1004", 3L))
        );
        Map<Long, List<User>> collect = users.stream().collect(Collectors.groupingBy(User::getId));
        System.out.println(JSONUtil.toJsonStr(collect));
        Map<Long, List<String>> collect1 = users.stream().collect(Collectors.groupingBy(User::getId, Collectors.mapping(User::getName, Collectors.toList())));
        System.out.println(JSONUtil.toJsonStr(collect1));
        Map<Long, Long> collect2 = users.stream().collect(Collectors.groupingBy(User::getId, Collectors.counting()));
        System.out.println(JSONUtil.toJsonStr(collect2));


        Map<Long, Double> collect3 = users.stream().collect(Collectors.groupingBy(User::getId, Collectors.averagingDouble(User::getAge)));
        System.out.println(JSONUtil.toJsonStr(collect3));


    }

    public static void main(String[] args) throws IOException {
        File file1 = new File("C:\\Users\\King\\Downloads\\aaa.pdf");
        File file2 = new File("C:\\Users\\King\\Downloads\\bbb.pdf");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("C:\\Users\\King\\Downloads\\test.zip"));
        List<File> lists = Arrays.asList(file1, file2);
        byte[] buffer = new byte[1024];
        for (File list : lists) {
            FileInputStream in = new FileInputStream(list);
            out.putNextEntry(new ZipEntry(list.getName()));
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0 , len);
            }
        }
        out.close();
    }

}
