package com.huawei.wrong.example.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author king
 * @date 2023/2/28-21:30
 * @Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long orderId;
    private Long personId;
    private String orderName;
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(personId, order.personId) && Objects.equals(orderName, order.orderName) && Objects.equals(price, order.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, orderName, price);
    }
}
