package com.huawei.mapper;

import com.huawei.po.ApiEntity;

import java.util.List;

/**
 * @author king
 * @date 2022/7/22-23:43
 * @Desc
 */
public interface ApiMapper {

    int addApi(ApiEntity entity);

    List<ApiEntity> queryApi(ApiEntity entity);
}
