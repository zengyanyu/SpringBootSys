/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.framework.filter;

import com.zengyanyu.system.config.UserContextHolder;
import com.zengyanyu.system.entity.UserInfo;
import com.zengyanyu.system.service.IUserInfoService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤器
 *
 * @author zengyanyu
 */
@Configuration
public class LoginFilter implements Filter {

    @Resource
    private IUserInfoService userInfoService;

    /**
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();
        if (requestURI.contains("/login")) {

        } else {
            String auth = req.getHeader("authorization");
            if (auth != null && auth.startsWith("Bearer ")) {
                String token = auth.split(" ")[1];
                UserInfo userInfo = userInfoService.getUserInfoByToken(token).getData();
                UserContextHolder.setUserContext(userInfo);
            } else {
                // 提示401
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        // 放行
        chain.doFilter(request, response);
    }

}
