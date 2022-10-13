package com.huawei.controller;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import com.huawei.config.ContextUtils;
import com.huawei.po.ApiEntity;
import com.huawei.service.ApiService;
import com.huawei.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @author king
 * @date 2022/7/22-23:31
 * @Desc
 */
@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping("/add")
    public BaseResponse<Map> addApi(@RequestBody ApiEntity apiEntity) {
        apiEntity.setApiUUID(UUID.randomUUID().toString().replaceAll("-",""));
        return apiService.addApi(apiEntity);
    }

    @GetMapping("/query")
    public BaseResponse<JSONObject> queryApi(@RequestBody ApiEntity apiEntity) {
        return apiService.queryApi(apiEntity);
    }

    @GetMapping("/exec")
    public BaseResponse<Object> ttlTest(String param) {
        ContextUtils.put("log"," main log");
        apiService.operatorTtl(param);
        Object log = ContextUtils.get("log");
        return new BaseResponse<Object>(HttpStatus.HTTP_OK, "operation success", log);
    }
}
