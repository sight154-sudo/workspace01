package com.huawei;

import com.huawei.po.OrderEntity;
import com.huawei.service.OrderService;
import com.huawei.utils.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;

/**
 * @author king
 * @date 2022/2/16-22:43
 * @Desc
 */
@SpringBootApplication
@MapperScan(basePackages = "com.huawei.mapper")
//@EnableDynamicThreadPool
public class LivedataApplication implements ApplicationRunner {

    @Autowired
    private OrderService orderService;


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LivedataApplication.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        OrderService orderService = SpringContextUtils.getApplicationContext().getBean("orderServiceImpl", OrderService.class);
        orderService.addOrder(new OrderEntity("101",1100d));
    }

}
