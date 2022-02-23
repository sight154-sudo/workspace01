package com.huawei.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author king
 * @date 2022/2/16-23:38
 * @Desc
 */
public interface StudentService {
    public List<Map<String,Object>> findInfo(String cid);

    public List<Map<String,Object>> findStudentSearch(String cid);
    public void doHandle(Map<String,Object> map, CountDownLatch countDownLatch);
}
