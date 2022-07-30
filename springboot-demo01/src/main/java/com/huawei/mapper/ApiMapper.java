package com.huawei.mapper;

import com.huawei.po.ApiEntity;

/**
 * @author king
 * @date 2022/7/22-23:43
 * @Desc
 */
public interface ApiMapper {

    int addApi(ApiEntity entity);

    ApiEntity queryApi(ApiEntity entity);
}
