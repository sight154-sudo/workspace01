package com.huawei.convert.impl;

import com.huawei.convert.IConvert;
import com.huawei.po.OperatorLog;
import com.huawei.po.OrderEntity;

/**
 * @author king
 * @date 2022/11/22-0:08
 * @Desc
 */
public class OrderEntityConvertImpl implements IConvert<OrderEntity> {
    @Override
    public OperatorLog convert(OrderEntity orderEntity) {
        OperatorLog operatorLog = new OperatorLog();
        operatorLog.setId(Long.valueOf(orderEntity.getOrderId()));
        return operatorLog;
    }
}
