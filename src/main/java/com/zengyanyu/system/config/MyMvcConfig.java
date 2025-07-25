package com.zengyanyu.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

//    @Value("${spring.profiles.active}")
//    private String active;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if ("dev".equals(active)) {
//            registry.addResourceHandler("/H5/**")
//                    .addResourceLocations("file:D:/uploadH5/"); // 本地 HTML 文件夹路径
//        }
//        if ("test".equals(active)) {
//            // Linux env
//            registry.addResourceHandler("/H5/**")
//                    .addResourceLocations("file:/webapps/dist/holah5/H5/"); // 本地 HTML 文件夹路径
//        }
//    }

    /**
     * 配置跨域
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        /* 是否允许请求带有验证信息 */
        config.setAllowCredentials(true);
        /* 允许访问的客户端域名 */
        config.addAllowedOriginPattern(CorsConfiguration.ALL);
        /* 允许服务端访问的客户端请求头 */
        config.addAllowedHeader(CorsConfiguration.ALL);
        /* 允许访问的方法名,GET POST等 */
        config.addAllowedMethod(CorsConfiguration.ALL);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
