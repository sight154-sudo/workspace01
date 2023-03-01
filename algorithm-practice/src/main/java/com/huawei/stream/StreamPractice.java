package com.huawei.stream;

import cn.hutool.core.date.StopWatch;
import com.google.common.collect.Lists;
import com.huawei.po.Person;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author king
 * @date 2023/2/25-0:10
 * @Desc
 */
public class StreamPractice {
    @Test
    public void stream2Test() {
        Person p1 = new Person(5, "zhangsan");
        Person p2 = new Person(2, "lisi");
        Person p3 = new Person(4, "zhaoliu");
        Person p4 = new Person(3, "wangwu");
        Person p5 = new Person(2, "lisi1");
        List<Person> personList = Stream.of(p1, p2, p3, p4, p5).collect(Collectors.toList());

        Person[] people = personList.toArray(new Person[personList.size()]);

        HashSet<Person> personHashSet = personList.stream().collect(Collectors.toCollection(HashSet::new));
//        System.out.println(personHashSet);

        /*new Comparable<Person>() {
            @Override
            public int compareTo(Person o) {

                id-o.getId()
                return 0;
            }
        }*/

        List<Person> personList1 = personList.stream().sorted((o1, o2) -> {
            if (o1.getId() == o2.getId()) {
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getId() - o2.getId();
        }).collect(Collectors.toList());
        System.out.println(personList1);
    }

    @Test
    public void listTest() {
        int[] arr = {1, 23, 3};
        List<int[]> ints = Arrays.asList(arr);
        System.out.println(ints);
        System.out.println(ints.size());
        System.out.println(ints.get(0).getClass());
//        Lists.partition()
    }

    private static List<List<Integer>> data = new ArrayList<>();

    @Test
    public void testSubList() {
        // 测试oom
        for (int i = 0; i < 1000; i++) {
            List<Integer> list = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
            data.add(list.subList(0, 1));
        }
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
    }

    private static Object listSearch(int elementCount, int loopCount) {
        List<Order> list = IntStream.rangeClosed(1, elementCount).mapToObj(i -> new Order(i)).collect(Collectors.toList());
        IntStream.rangeClosed(1, loopCount).forEach(i -> {
            int search = ThreadLocalRandom.current().nextInt(elementCount);
            Order result = list.stream().filter(order -> order.getOrderId() == search).findFirst().orElse(null);
            Assert.assertTrue(result != null && result.getOrderId() == search);
        });
        return list;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Order {
        private Integer orderId;
    }

    private static Object mapSearch(int elementCount, int loopCount) {
        Map<Integer, Order> map = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toMap(Function.identity(), i -> new Order(i)));
        IntStream.rangeClosed(1, loopCount).forEach(i -> {
            int search = ThreadLocalRandom.current().nextInt(elementCount);
            Order result = map.get(search);
            Assert.assertTrue(result != null && result.getOrderId() == search);
        });
        return map;
    }
    @Test
    public void codeObj() {

        int elementCount = 1000000;
        int loopCount = 1000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("listSearch");
        Object list = listSearch(elementCount, loopCount);
        System.out.println(ObjectSizeCalculator.getObjectSize(list));
        stopWatch.stop();
        stopWatch.start("mapSearch");
        Object map = mapSearch(elementCount, loopCount);
        stopWatch.stop();
        System.out.println(ObjectSizeCalculator.getObjectSize(map));
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void testHashCode() {
        HashSet<String> s1 = new HashSet<>();
        s1.add("11");
        s1.add("12");
        HashSet<String> s2 = new HashSet<>();
        s2.add("12");
        s2.add("11");
        boolean equals = s1.equals(s2);
        System.out.println("equals = " + equals);
    }
}
