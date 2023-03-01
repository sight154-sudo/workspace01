package com.huawei;

import org.apache.catalina.core.ApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author king
 * @date 2022/9/6-0:35
 * @Desc
 */
@SpringBootApplication
@MapperScan(basePackages = "com.huawei.mapper")
public class GraphqlApplication {
    public static void main(String[] args) {
        // -Dspring.profiles.active=dev 设置启动环境
        // java -jar GraphqlApplication.jar -Dspring.profiles.active=dev > /dev/null 2>&1
        SpringApplication.run(GraphqlApplication.class);
    }
}
