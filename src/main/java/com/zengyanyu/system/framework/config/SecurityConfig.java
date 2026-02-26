package com.zengyanyu.system.framework.config;

import com.zengyanyu.system.framework.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 安全配置
 *
 * @author zengyanyu
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Resource
    private JwtAuthFilter jwtAuthFilter;

    @Override
    public void configure(WebSecurity web) {
        // 放行Swagger相关路径
        web.ignoring().antMatchers(
                "/doc.html",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/webjars/**",
                "/v3/api-docs/**",
                "/druid/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 启用 CORS 支持
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // 禁用 CSRF 保护
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
                // 不需要验证的接口
                .antMatchers("/index.html",
                        "/user-info/login", "/user-info/userInfo", "/verification/get",
                        // 静态资源
                        "/ws/**", "/favicon.ico",
                        "/usr/local/upload/**",
                        "/preview/**", "/H5/**",
                        "D:/upload/**", "D:/uploadH5/**",
                        "/**"// 测试,先全部请求进行放行,不需要加token
                ).permitAll()
                //.antMatchers("/**").authenticated() // 其他所有接口需要认证
                .anyRequest().authenticated() // 所有未匹配的请求也需要认证
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
    }

    /**
     * 跨域配置
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
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
        return source;
    }

}
