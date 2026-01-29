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
