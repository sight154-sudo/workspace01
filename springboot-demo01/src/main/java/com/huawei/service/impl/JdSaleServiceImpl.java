package com.huawei.service.impl;

import com.huawei.service.SaleService;
import com.huawei.vo.SaleRequest;
import org.springframework.stereotype.Component;

/**
 * @author king
 * @date 2022/12/6-22:53
 * @Desc
 */
@Component
public class JdSaleServiceImpl implements SaleService {

    @Override
    public double salePrice(SaleRequest request) {
        System.out.println("jdSaleService run...");
        return request.getPrice();
    }

}
