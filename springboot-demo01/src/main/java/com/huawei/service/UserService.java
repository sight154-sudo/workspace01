package com.huawei.service;

import com.huawei.po.User;
import com.huawei.utils.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author king
 * @date 2022/10/25-23:38
 * @Desc
 */
public interface UserService {
    public User login(User user);

    List<User> findAll(User user);

    public BaseResponse handleConsumer(String appType, Integer appNo);

}
