package com.huawei.interceptor;

import cn.hutool.crypto.symmetric.AES;
import cn.hutool.system.UserInfo;
import com.huawei.config.ContextUtils;
import com.huawei.mapper.UserMapper;
import com.huawei.po.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Base64;

import static com.huawei.constant.PublicConstant.SYSTEM_USER_SESSION;

/**
 * @author king
 * @date 2022/9/15-23:20
 * @Desc
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获得cookie
        Cookie[] cookies = request.getCookies();
        // 没有cookie信息，则重定向到登录界面
        if (null == cookies) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        // 定义cookie_username，用户的一些登录信息，例如：用户名，密码等
        String cookie_username = null;
        // 获取cookie里面的一些用户信息
        for (Cookie item : cookies) {
            if ("cookie_username".equals(item.getName())) {
                cookie_username = new String(Base64.getDecoder().decode(item.getValue()), "UTF-8");
                break;
            }
        }
        // 如果cookie里面没有包含用户的一些登录信息，则重定向到登录界面
        if (StringUtils.isEmpty(cookie_username)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        // 获取HttpSession对象
        HttpSession session = request.getSession();
        // 获取我们登录后存在session中的用户信息，如果为空，表示session已经过期
        Object obj = session.getAttribute(SYSTEM_USER_SESSION);
        if (null == obj) {
            // 根据用户登录账号获取数据库中的用户信息
            User dbUser = userMapper.queryUserInfo(cookie_username);
            // 将用户保存到session中
            session.setAttribute(SYSTEM_USER_SESSION, dbUser);
        }
        // 已经登录
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextUtils.clear();
    }
}
