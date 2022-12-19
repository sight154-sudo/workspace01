package com.huawei.myenum;

import com.huawei.service.impl.JdSaleServiceImpl;
import com.huawei.service.impl.SfSaleServiceImpl;

/**
 * @author king
 * @date 2022/12/6-23:58
 * @Desc
 */
public enum SaleTypeEnum {
    JD(1, JdSaleServiceImpl.class),
    SF(2, SfSaleServiceImpl.class);

    private Integer type;

    private Class clazz;

    SaleTypeEnum(int i, Class clazz) {
    }



}
