package com.huawei.convert.impl;

import com.huawei.convert.IConvert;
import com.huawei.po.OperatorLog;
import com.huawei.po.OrderVo;

/**
 * @author king
 * @date 2022/11/22-0:06
 * @Desc
 */
public class OrderVoConvertImpl implements IConvert<OrderVo> {
    @Override
    public OperatorLog convert(OrderVo orderVo) {
        OperatorLog operatorLog = new OperatorLog();
        operatorLog.setId(Long.valueOf(orderVo.getId()));
        return operatorLog;
    }
}
