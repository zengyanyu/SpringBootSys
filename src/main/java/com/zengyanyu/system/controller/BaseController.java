/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zengyanyu
 */
public class BaseController {

//    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    protected ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    protected HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    protected HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }
}
