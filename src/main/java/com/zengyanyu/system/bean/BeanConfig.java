package com.zengyanyu.system.bean;

import com.zengyanyu.system.pxoxy.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zengyanyu
 */
@Configuration
public class BeanConfig {

    @Bean
    public TransactionManager transactionManager() {
        return new TransactionManager();
    }
}
