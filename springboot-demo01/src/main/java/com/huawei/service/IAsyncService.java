package com.huawei.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @author king
 * @date 2022/2/19-16:29
 * @Desc
 */
public interface IAsyncService {
    public void doTask(Map<String,Object> map, CountDownLatch countDownLatch);

    public  <T> Future<T> doTaskRtn();
}
