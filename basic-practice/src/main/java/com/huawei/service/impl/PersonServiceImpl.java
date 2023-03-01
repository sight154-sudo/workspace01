package com.huawei.service.impl;

import com.huawei.mapper.PersonDao;
import com.huawei.service.PersonService;
import com.huawei.wrong.example.po.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author king
 * @date 2023/3/1-0:11
 * @Desc
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public List<Person> findPerson() {
        return personDao.queryPerson();
    }
}
