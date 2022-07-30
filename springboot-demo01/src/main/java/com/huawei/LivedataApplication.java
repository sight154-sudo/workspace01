package com.huawei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author king
 * @date 2022/2/16-22:43
 * @Desc
 */
@SpringBootApplication
@MapperScan(basePackages = "com.huawei.mapper")
//@EnableDynamicThreadPool
public class LivedataApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivedataApplication.class);
    }
}
