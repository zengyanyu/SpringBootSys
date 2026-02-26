package com.zengyanyu.system.framework.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.ResourceServlet;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 【德鲁伊】连接池配置
 * /druid/index.html
 *
 * @author zengyanyu
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource() {
        log.info("==== 使用Druid（德鲁伊）连接池 ====");
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置Druid监控
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put(ResourceServlet.PARAM_NAME_USERNAME, "zengyanyu");
        initParams.put(ResourceServlet.PARAM_NAME_PASSWORD, "123456");
        initParams.put(ResourceServlet.PARAM_NAME_ALLOW, "");
        bean.setInitParameters(initParams);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        // 配置过滤器作用范围
        bean.setUrlPatterns(Arrays.asList("/*"));
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        return bean;
    }

}
