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
    private String userName;
    private Boolean gender;
    private Integer age;
    private Double salary;
    private Date birthday;
    private Date createdTime;
    private Date updatedTime;
}
