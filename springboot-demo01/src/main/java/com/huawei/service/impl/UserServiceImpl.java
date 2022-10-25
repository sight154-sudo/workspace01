package com.huawei.service.impl;

import com.huawei.mapper.UserMapper;
import com.huawei.po.User;
import com.huawei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author king
 * @date 2022/10/25-23:39
 * @Desc
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

}
