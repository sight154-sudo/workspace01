package com.huawei.service.impl;

import com.huawei.mapper.StudentMapper;
import com.huawei.service.IAsyncService;
import com.huawei.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @author king
 * @date 2022/2/19-16:34
 * @Desc
 */
@Component
public class AsyncServiceImpl implements IAsyncService {

    @Autowired
    @Lazy
    private StudentService studentService;


    @Override
    @Async("taskExecutor")
    public void doTask(Map<String, Object> map,  CountDownLatch countDownLatch) {
        studentService.doHandle(map,countDownLatch);
    }

    @Override
    public <T> Future<T> doTaskRtn() {
        return null;
    }


}
