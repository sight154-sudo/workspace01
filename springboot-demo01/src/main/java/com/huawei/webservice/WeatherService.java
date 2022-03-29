package com.huawei.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huawei.po.User;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author king
 * @date 2022/3/8-23:35
 * @Desc
 */
@WebService(targetNamespace = "http://webservice.huawei.com")
public interface WeatherService {

    String queryWeather(@WebParam(name = "email")String email,@WebParam(name = "username") String username,
                        @WebParam(name = "password") String password);

    String queryUser(@WebParam(name= "user") User user) throws JsonProcessingException;
}
