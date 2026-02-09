package com.zengyanyu.system.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 *
 * @author zengyanyu
 */
@Configuration
public class CorsConfig {

    /**
     * 跨域配置
     *
     * @return
     */
    @Bean
    @Profile("dev")
    public CorsFilter corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 开发时可以使用，或指定具体域名
//        config.setAllowedOriginPatterns(Arrays.asList(
//                "http://192.168.120.55",
//                "http://192.168.120.168",
//                "http://192.168.120.221",
//                "http://192.168.120.77",
//                "http://localhost",
//                "http://127.0.0.1",
//                // 测试环境-linux环境
//                "http:192.168.244.131"
//        ));
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        // 允许的 HTTP 方法
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // 允许的请求头
        config.setAllowedHeaders(Arrays.asList("Content-Type", "X-Requested-With", "Origin", "Accept",
                "Referer", "User-Agent", "Cookie", "Authorization"));
        // 是否允许发送 Cookie (凭证)
        // 如果设置为 true，那么 setAllowedOriginPatterns 不能使用 "*"
        config.setAllowCredentials(true);
        // 预检请求的缓存时间（秒）
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 对所有路径应用这个 CORS 配置
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
