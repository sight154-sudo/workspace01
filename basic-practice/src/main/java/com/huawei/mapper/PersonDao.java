package com.huawei.mapper;

import com.huawei.wrong.example.po.Person;

import java.util.List;

/**
 * @author king
 * @date 2023/2/28-21:32
 * @Desc
 */
public interface PersonDao {
    List<Person> selectPerson();

    List<Person> queryPerson();
}
