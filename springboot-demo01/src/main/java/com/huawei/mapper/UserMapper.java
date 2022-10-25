package com.huawei.mapper;

import com.huawei.po.User;

import java.util.List;

/**
 * @author king
 * @date 2022/2/16-23:16
 * @Desc
 */
public interface UserMapper {

    public List<User> findAll(User user);

    public User findById(Integer id);

    public User login(User user);

    public User queryUserInfo(String username);
}
