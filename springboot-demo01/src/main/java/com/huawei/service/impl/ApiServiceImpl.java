package com.huawei.service.impl;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import com.huawei.mapper.ApiMapper;
import com.huawei.po.ApiEntity;
import com.huawei.service.ApiService;
import com.huawei.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * @author king
 * @date 2022/7/22-23:56
 * @Desc
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiMapper apiMapper;

    @Override
    @Transactional
    public BaseResponse<Map> addApi(ApiEntity apiEntity) {
        ApiEntity info = new ApiEntity();
        info.setApiName(apiEntity.getApiName());
        ApiEntity apiEntity1 = apiMapper.queryApi(info);
        if (apiEntity1 != null) {
            return new BaseResponse(HttpStatus.HTTP_INTERNAL_ERROR,"api name is exist");
        }
        int res = apiMapper.addApi(apiEntity);
        if (res != 1) {
            return BaseResponse.failed();
        }
        return BaseResponse.ok();
    }

    @Override
    public BaseResponse<JSONObject> queryApi(ApiEntity entity) {
        ApiEntity apiEntity = apiMapper.queryApi(entity);
        JSONObject json = new JSONObject();
        json.put("result", apiEntity);
        return new BaseResponse<JSONObject>(HttpStatus.HTTP_OK,"ok", json);
    }
}
