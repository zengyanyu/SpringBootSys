/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.controller;

import com.zengyanyu.system.service.RetryTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "重试 控制器对象")
@RestController
public class TestController {

    @Resource
    private RetryTestService retryTestService;

    @ApiOperation(value = "重试方法", notes = "\n必须引入 spring-retry + spring-boot-starter-aop 依赖\n" +
            "启动类必须加 @EnableRetry 开启功能\n" +
            "@Retryable 标记重试方法，@Recover 标记失败兜底方法\n" +
            "重试次数包含第一次执行，延迟策略支持固定 / 指数退避\n" +
            "该注解只能用于 Spring 管理的 Bean 方法，私有方法无效")
    @GetMapping("/test/{num}")
    public String test(@PathVariable int num) throws Exception {
        return retryTestService.testRetry(num);
    }
}
