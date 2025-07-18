package com.zengyanyu.system;

@EnableSwagger2
@SpringBootApplication
@MapperScan(value = {"com.zengyanyu.**.mapper"})
public class SpringBootSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSystemApplication.class, args);
    }

}
