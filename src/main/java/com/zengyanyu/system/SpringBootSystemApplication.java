package com.zengyanyu.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan(value = {"com.zengyanyu.**.mapper"})
public class SpringBootSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSystemApplication.class, args);
    }

}
