package com.huawei.service.impl;

import com.huawei.service.SaleService;
import com.huawei.vo.SaleRequest;
import org.springframework.stereotype.Service;

/**
 * @author king
 * @date 2022/12/6-23:25
 * @Desc
 */
@Service
public class SfSaleServiceImpl implements SaleService {
    @Override
    public double salePrice(SaleRequest request) {
        System.out.println("SFSaleService run...");
        return request.getPrice();
    }
}
