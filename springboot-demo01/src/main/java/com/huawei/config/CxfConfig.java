package com.huawei.config;

import com.huawei.webservice.WeatherService;
import com.huawei.webservice.WeatherServiceImpl;
import org.apache.catalina.servlets.CGIServlet;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author king
 * @date 2022/3/8-23:42
 * @Desc
 */
@Configuration
public class CxfConfig {
//createServletRegistrationBean
    @Bean
    public ServletRegistrationBean createServletRegistrationBean(){
        return new ServletRegistrationBean(new CXFServlet(),"/demo/*");
    }

    @Bean(Bus.DEFAULT_BUS_ID)
    public SpringBus springBus(){
        return new SpringBus();
    }

    @Bean
    public WeatherService weatherService(){
        return new WeatherServiceImpl();
    }

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(springBus(),weatherService());
        endpoint.publish("/api");
        return endpoint;
    }
}
