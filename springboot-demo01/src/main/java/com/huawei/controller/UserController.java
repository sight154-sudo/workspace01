package com.huawei.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author king
 * @date 2022/2/16-22:48
 * @Desc
 */
@RestController
public class UserController {

    @RequestMapping("testApi")
    public String testApi(){
        return "success";
    }
}
