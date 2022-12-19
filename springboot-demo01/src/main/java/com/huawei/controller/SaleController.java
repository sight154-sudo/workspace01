package com.huawei.controller;

import com.huawei.service.SaleService;
import com.huawei.utils.BaseResponse;
import com.huawei.vo.SaleRequest;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author king
 * @date 2022/12/6-23:26
 * @Desc
 */
@RestController
public class SaleController {

    @Autowired
    private List<SaleService> saleServices;

    @Autowired
    private Map<String, SaleService> saleServiceMap;


    @GetMapping("/sale")
    public BaseResponse getPrice(@RequestBody SaleRequest saleRequest) {
        System.out.println(saleServiceMap);
        SaleService saleService = saleServices.stream().filter((item) -> saleRequest.getType() == 1).findFirst().orElse(null);
        double v = saleService.salePrice(saleRequest);
        return BaseResponse.ok();
    }
}
