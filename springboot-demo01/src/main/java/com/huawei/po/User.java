package com.huawei.po;

import lombok.Data;

import java.util.Date;

/**
 * @author king
 * @date 2022/2/16-22:42
 * @Desc
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Boolean gender;
    private Integer age;
    private Double salary;
    private Date birthday;
    private Date createdTime;
    private Date updatedTime;

    public User() {
    }

    public User(Integer id, String username, String password, Boolean gender, Integer age, Double salary, Date birthday, Date createdTime, Date updatedTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.birthday = birthday;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
