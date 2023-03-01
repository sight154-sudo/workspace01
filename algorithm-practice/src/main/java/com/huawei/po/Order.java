package com.huawei.po;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author king
 * @date 2023/2/26-15:31
 * @Desc
 */
public class Order {

    private Long orderId;
    private Long personId;
    private String orderName;
    private Double price;

    private String[] address;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(personId, order.personId) && Objects.equals(orderName, order.orderName) && Objects.equals(price, order.price) && Arrays.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(personId, orderName, price);
        result = 31 * result + Arrays.hashCode(address);
        return result;
    }
}
