package com.huawei.po;

import lombok.Getter;
import lombok.Setter;

/**
 * @author king
 * @date 2022/11/21-23:14
 * @Desc
 */
@Getter
@Setter
public class OrderEntity {
    private String orderId;
    private double price;

    public OrderEntity() {
    }

    public OrderEntity(String orderId, double price) {
        this.orderId = orderId;
        this.price = price;
    }
}
