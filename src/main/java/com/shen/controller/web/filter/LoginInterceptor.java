package com.shen.controller.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是登陆页面或者验证用户名密码，则允许通过
        if("/login.mvc".equals(request.getServletPath()) || request.getServletPath().contains("validate")){
            logger.info(request.getServletPath());
            return true;
        }

        HttpSession session = request.getSession();

        // 如果登陆过，则允许访问，没有登陆则将其指向登陆页面
        if (session.getAttribute("user") != null) {
            logger.info(this.getClass().getName(), "user != null");
            return true;
        } else {
            logger.info(this.getClass().getName(), "user == null");
            response.sendRedirect("/java_ee_design/login.mvc");
            return false;
        }
    }
}
