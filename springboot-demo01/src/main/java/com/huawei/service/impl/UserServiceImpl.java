package com.huawei.service.impl;

import com.huawei.mapper.UserMapper;
import com.huawei.po.User;
import com.huawei.service.UserService;
import com.huawei.utils.BaseResponse;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author king
 * @date 2022/10/25-23:39
 * @Desc
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private Map<String, BiFunction<String, Integer, BaseResponse>> biFunctionMap = new HashMap<>();

    {
        biFunctionMap.put("Reg", this::register);
        biFunctionMap.put("Cha", this::change);
    }

    @Override
    public List<User> findAll(User user) {
        return userMapper.findAll(user);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    public BaseResponse handleConsumer(String appType, Integer appNo) {
        BiFunction<String, Integer, BaseResponse> biFunction =
                biFunctionMap.get(appType);
        if (biFunction != null) {
            return biFunction.apply(appType, appNo);
        }
        return null;
    }

    public BaseResponse register(String appType, Integer appNo) {
        System.out.println(appType+" "+appNo);
        User user = userMapper.findById(appNo);
        return BaseResponse.ok(appType+" "+appNo, user);
    }

    public BaseResponse change(String appType, Integer appNo) {
        System.out.println(appType+" "+appNo);
        return BaseResponse.ok(appType+" "+appNo);
    }

}
