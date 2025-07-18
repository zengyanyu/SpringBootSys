package com.zengyanyu.system.config;

import com.zengyanyu.system.entity.UserInfo;
import com.zengyanyu.system.service.IUserInfoService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录过滤器
 */
@Configuration
public class LoginFilter implements Filter {

    @Resource
    private IUserInfoService userInfoService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;

        String auth = req.getHeader("authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.split(" ")[1];
            UserInfo userInfo = userInfoService.getUserInfoByToken(token).getData();
            UserContextHolder.setUserContext(userInfo);
        }

        // 放行
        chain.doFilter(request, response);
    }

}
