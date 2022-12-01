package com.huawei.po;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

/**
 * @author king
 * @date 2022/2/16-22:42
 * @Desc
 */
@Data
public class User {
    private Integer id;
    @Length(min = 2, max = 12)
    private String username;
    @Pattern(regexp = "[A_Z]\\w{4,6}")
    private String password;
    private Boolean gender;
    private Integer age;
    private Double salary;
    private Date birthday;
    private Date createdTime;
    private Date updatedTime;
    private List<OrderVo> orders;

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
