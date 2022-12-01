package com.huawei.controller;

import com.huawei.po.User;
import com.huawei.service.UserService;
import com.huawei.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import static com.huawei.constant.PublicConstant.SYSTEM_USER_SESSION;

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

    @Autowired
    private UserService userService;

    @PostMapping(value = "login", produces = "application/json;charset=utf-8")
    public ResponseEntity<User> login(@RequestBody @Validated User user, HttpServletRequest request, HttpServletResponse response) {
        User userInfo = userService.login(user);
        if (Objects.isNull(userInfo)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Cookie cookie = new Cookie("cookie_username", Base64.getEncoder().encodeToString(userInfo.getUsername().getBytes(StandardCharsets.UTF_8)));
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(cookie);
        response.setContentType("UTF-8");
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 删除session里面的用户信息
        session.removeAttribute(SYSTEM_USER_SESSION);
        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie("cookie_username", "");
        // 设置cookie的持久化时间，0
        cookie_username.setMaxAge(0);
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);
        return "login";
    }

    @PostMapping("findAll")
    public ResponseEntity<List<User>> findAll(@RequestBody User user) {
        return ResponseEntity.ok(userService.findAll(user));
    }

    @GetMapping("function")
    public BaseResponse function(String appType, Integer appNo){
        return userService.handleConsumer(appType, appNo);
    }
}
