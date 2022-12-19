package com.huawei.service.impl;

import com.huawei.annotation.RecordLog;
import com.huawei.convert.impl.OrderEntityConvertImpl;
import com.huawei.convert.impl.OrderVoConvertImpl;
import com.huawei.po.OrderEntity;
import com.huawei.po.OrderVo;
import com.huawei.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author king
 * @date 2022/11/21-23:36
 * @Desc
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @RecordLog(value = "save order info!", orderId = "#{orderEntity.orderId}", clazz = OrderEntityConvertImpl.class)
    public boolean addOrder(OrderEntity orderEntity) {
        logger.info(String.format(Locale.ROOT, "add order"));
        return false;
    }

    @Override
    @RecordLog(value = "modify order info", orderId = "#{order.orderId}", clazz = OrderVoConvertImpl.class)
    public boolean updateOrder(OrderVo order) {
        logger.info(String.format(Locale.ROOT, "update order"));
        return false;
    }
}
