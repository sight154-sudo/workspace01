package com.huawei.service;

import cn.hutool.json.JSONObject;
import com.huawei.po.ApiEntity;
import com.huawei.utils.BaseResponse;

import java.util.Map;

/**
 * @author king
 * @date 2022/7/22-23:55
 * @Desc
 */
public interface ApiService {

    BaseResponse<Map> addApi(ApiEntity apiEntity);
    BaseResponse<JSONObject> queryApi(ApiEntity entity);

    void operatorTtl(String param);
}
