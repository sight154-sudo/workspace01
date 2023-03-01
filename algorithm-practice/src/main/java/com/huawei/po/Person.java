package com.huawei.po;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author king
 * @date 2023/2/25-0:10
 * @Desc
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable<Person> {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }

    public static void main(String[] args) {
        Person p1 = new Person(5, "zhangsan");
        Person p2 = new Person(2, "lisi");
        Person p3 = new Person(4, "zhaoliu");
        Person p4 = new Person(3, "wangwu");
        Person p5 = new Person(2, "lisi1");
        List<Person> personList = Stream.of(p1, p2, p3, p4, p5).collect(Collectors.toList());

        TreeSet<Person> set = new TreeSet<>(personList);
        System.out.println(set);
        Person qq = new Person(2, "qq");
        System.out.println(personList);
        List<Person> collect = personList.stream().sorted().collect(Collectors.toList());
        System.out.println("collect = " + collect);
        System.out.println(personList);
        int index1 = personList.indexOf(qq);

        int index2 = Collections.binarySearch(personList, qq);

        Collections.sort(personList);
        System.out.println(personList);
        System.out.println(index1+ " "+ index2);

    }

    @Override
    public int compareTo(Person o) {
//        return Comparator.comparing(Person::getId).thenComparingInt(Person::getId).compare(this, o);
        /*Comparator.comparing((item) -> this.getName().compareTo(o.getName()))
                .thenComparingInt((item) -> this.getId().compareTo(o.getId()))
                .compare(this, o);*/
        if (this.id == o.getId()) {
            return this.name.compareTo(o.getName());
        }
        return this.id.compareTo(o.getId());
    }
}

