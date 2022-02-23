package com.huawei.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author king
 * @date 2022/2/16-23:21
 * @Desc
 */
public interface StudentMapper {
    List<Map<String,Object>> findInfo(String cid);

    List<String> findStudentIds(String cid);

    Map<String,Object> findStudentInfo(@Param("sid") String tid, @Param("cid") String cid);
}
