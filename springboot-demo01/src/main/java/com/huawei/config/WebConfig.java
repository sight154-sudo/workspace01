package com.huawei.config;

import com.huawei.interceptor.GlobalInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author king
 * @date 2022/9/5-23:50
 * @Desc
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean(name="remoteRestTemplate")
    public RestTemplate restTemplate(ClientHttpRequestFactory simpleClientHttpRequestFactory){
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        restTemplate.setRequestFactory(new HttpComponentsClientRestfulHttpRequestFactory());
        return restTemplate;
    }
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(60000);
        factory.setReadTimeout(20000);
        return factory;
    }

    @Autowired
    private GlobalInterceptor globalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor) //拦截器注册对象
                .addPathPatterns("/**") //指定要拦截的请求
                .excludePathPatterns("/user/login"); //排除请求
    }
}
