package com.huawei.graphql.entity;

import lombok.Data;

@Data
public class Card {
    private String cardNumber;
    private Long userId;

    public Card(String cardNumber, Long userId) {
        this.cardNumber = cardNumber;
        this.userId = userId;
    }
}