package com.huawei.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.po.User;

import javax.jws.WebService;

/**
 * @author king
 * @date 2022/3/8-23:35
 * @Desc
 */
@WebService(name = "weatherService",
        targetNamespace = "http://webservice.huawei.com",
        endpointInterface = "com.huawei.webservice.WeatherService")
public class WeatherServiceImpl implements WeatherService{

    @Override
    public String queryWeather(String email, String username, String password) {
        return "\nemail:"+email+",username:"+username+",password:"+password;
    }

    @Override
    public String queryUser(User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(user);
        return s;
    }
}
