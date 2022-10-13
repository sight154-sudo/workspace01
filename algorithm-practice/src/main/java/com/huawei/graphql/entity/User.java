package com.huawei.graphql.entity;

import lombok.Data;


@Data
public class User {
    private int age;
    private long id;
    private String name;
    private String[] address;

    private Card card;

    public User(int age, long id, String name, Card card) {
        this.age = age;
        this.id = id;
        this.name = name;
        this.card = card;
    }

    public User(int age, long id, String name, String[] address) {
        this.age = age;
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public User(int age, long id, String name) {
        this.age = age;
        this.id = id;
        this.name = name;
    }
}