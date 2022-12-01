package com.huawei.service;

import com.huawei.po.OrderEntity;
import com.huawei.po.OrderVo;

/**
 * @author king
 * @date 2022/11/21-23:13
 * @Desc
 */
public interface OrderService {
    boolean addOrder(OrderEntity orderEntity);

    boolean updateOrder(OrderVo order);
}
