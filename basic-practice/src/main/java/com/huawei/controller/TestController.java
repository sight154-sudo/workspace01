package com.huawei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author king
 * @date 2023/2/22-0:05
 * @Desc
 */


@RestController
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    List<String> list = new ArrayList<>();

    @GetMapping("internperformance")
    public int internperformance(@RequestParam(value = "size", defaultValue = "10000000")int size) {
        //-XX:+PrintStringTableStatistics
        //-XX:StringTableSize=10000000
        long begin = System.currentTimeMillis();
        list = IntStream.rangeClosed(1, size)
                .mapToObj(i-> String.valueOf(i).intern())
                .collect(Collectors.toList());
        LOGGER.info("size:{} took:{}", size, System.currentTimeMillis() - begin);
        return list.size();
    }

}
