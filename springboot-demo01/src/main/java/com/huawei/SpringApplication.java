package com.huawei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author king
 * @date 2022/2/16-22:43
 * @Desc
 */
@SpringBootApplication
@MapperScan(basePackages = "com.huawei.mapper")
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class);
    }
}
