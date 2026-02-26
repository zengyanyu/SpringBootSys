package com.zengyanyu.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zengyanyu
 */
@Controller
public class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

//    protected ServletRequestAttributes getServletRequestAttributes() {
//        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//    }
//
//    protected HttpServletRequest getRequest() {
//        return getServletRequestAttributes().getRequest();
//    }
//
//    protected HttpServletResponse getResponse() {
//        return getServletRequestAttributes().getResponse();
//    }
}
