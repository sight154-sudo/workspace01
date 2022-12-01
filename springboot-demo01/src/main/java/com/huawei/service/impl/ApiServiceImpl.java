package com.huawei.service.impl;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.huawei.config.ContextUtils;
import com.huawei.mapper.ApiMapper;
import com.huawei.po.ApiEntity;
import com.huawei.service.ApiService;
import com.huawei.utils.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author king
 * @date 2022/7/22-23:56
 * @Desc
 */
@Service
public class ApiServiceImpl implements ApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Autowired
    private ApiMapper apiMapper;

    @Override
    @Transactional
    public BaseResponse<Map> addApi(ApiEntity apiEntity) {
        ApiEntity info = new ApiEntity();
        info.setApiName(apiEntity.getApiName());
        List<ApiEntity> apiEntity1 = apiMapper.queryApi(info);
        if (apiEntity1 != null && apiEntity1.size() > 0) {
            return new BaseResponse(HttpStatus.HTTP_INTERNAL_ERROR, "api name is exist");
        }
        int res = apiMapper.addApi(apiEntity);
        if (res != 1) {
            return BaseResponse.failed();
        }
        return BaseResponse.ok();
    }

    @Override
    public BaseResponse<JSONObject> queryApi(ApiEntity entity) {
        List<ApiEntity> apiEntity = apiMapper.queryApi(entity);
        JSONObject json = new JSONObject();
        json.set("result", apiEntity);
        return new BaseResponse<JSONObject>(HttpStatus.HTTP_OK, "ok", json);
    }

    private static ExecutorService executorService = new ThreadPoolExecutor(
            1,1,1000, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(100),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    @Override
    public void operatorTtl(String param) {
        try {
            LOGGER.info("begin exec ...");
            CompletableFuture<BaseResponse> future = CompletableFuture.supplyAsync(
                    () -> exec(param)
            , TtlExecutors.getTtlExecutor(executorService));
//            Future<BaseResponse> future = TtlExecutors.getTtlExecutorService(executorService).submit(() -> exec(param));
            future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LOGGER.error("exec error...");
        }
    }

    public BaseResponse exec(String param) {
        try {
            ContextUtils.put("log", param);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return BaseResponse.ok();
    }
}
