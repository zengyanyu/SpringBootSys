/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zengyanyu
 */
@EnableScheduling
@SpringBootApplication
public class SpringBootSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSysApplication.class, args);
    }

}
