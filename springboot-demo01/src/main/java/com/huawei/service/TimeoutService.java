package com.huawei.service;

import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author king
 * @date 2022/7/12-1:41
 * @Desc
 */
@Service
public class TimeoutService {

    @Resource
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    public String timeout() throws ExecutionException, InterruptedException {
        Future<String> submit = taskExecutor.submit(() -> task());
        return submit.get();
    }

    private String task() {
        boolean flag = true;
        while(flag) {

        }
        return "SUCCESS";
    }
}
