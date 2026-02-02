package com.zengyanyu.system.framework.filter;

import com.zengyanyu.system.service.IUserInfoService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现认证逻辑
 * @author zengyanyu
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Resource
    private IUserInfoService userInfoService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("authorization");

        String username = null;
        String jwtToken = null;

        // 从Authorization头部获取JWT Token
//        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//            jwtToken = requestTokenHeader.substring(7);
//            try {
//                // 根据token获取用用户名
//                username = JwtUtil.extractUsername(jwtToken);
//            } catch (IllegalArgumentException e) {
//                System.out.println("Unable to get JWT Token");
//            } catch (ExpiredJwtException e) {
//                System.out.println("JWT Token has expired");
//            }
//        }
//
//        // 验证token
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userInfoService.loadUserByUsername(username);
//
//            if (JwtUtil.validateToken(jwtToken, userDetails.getUsername())) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }

        // 放行
        chain.doFilter(request, response);
    }

}
