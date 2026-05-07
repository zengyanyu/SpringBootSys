/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.framework.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录处理拦截器
 *
 * @author zengyanyu
 */
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 处理之前
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return 是否放行
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 处理之后
     *
     * @param request      请求
     * @param response     响应
     * @param handler      处理器
     * @param modelAndView 模型试图
     * @throws Exception 异常
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("处理之后----" + URLDecoder.decode(request.getRequestURL().toString(), StandardCharsets.UTF_8.name()));
    }

    /**
     * 完成后
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @param ex       异常
     * @throws Exception 异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("完成后----" + URLDecoder.decode(request.getRequestURL().toString(), StandardCharsets.UTF_8.name()));
    }
}
