package com.huawei.service.impl;

import com.huawei.mapper.StudentMapper;
import com.huawei.service.IAsyncService;
import com.huawei.service.StudentService;
import com.huawei.utils.LivedataThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author king
 * @date 2022/2/16-23:38
 * @Desc
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);


    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private IAsyncService asyncService;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    public void saveStudent() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

    }

    @Override
    public List<Map<String, Object>> findInfo(String cid) {
        return studentMapper.findInfo(cid);
    }

    @Override
    public List<Map<String, Object>> findStudentSearch(String cid) {
        long start = System.currentTimeMillis();
        List<Map<String,Object>> info = studentMapper.findInfo(cid);
        LOGGER.info(String.format("studentMapper.findStudentIds(cid) 耗时: %dms",(System.currentTimeMillis() - start)));
        CountDownLatch countDownLatch = new CountDownLatch(info.size());
        start = System.currentTimeMillis();
        info.stream().forEach((student) -> {
            asyncService.doTask(student, countDownLatch);
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("studentMapper.findStudentInfo(studentId) 耗时: %dms",(System.currentTimeMillis() - start)));
        return info;
    }

    public void doHandle(Map<String,Object> map, CountDownLatch countDownLatch) {
        Map<String,Object> tmp = studentMapper.findStudentInfo(String.valueOf(map.get("sid")), String.valueOf(map.get("cid")));
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("thread-name",Thread.currentThread().getName());
        countDownLatch.countDown();
    }
}