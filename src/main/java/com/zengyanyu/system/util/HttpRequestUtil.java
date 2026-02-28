package com.zengyanyu.system.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP请求的工具类, 用于获取Request、Response相关信息
 *
 * @author zengyanyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpRequestUtil {

    /**
     * 从 SpringBoot 中获取 Request 请求对象
     *
     * @return 返回当前请求的 Request 对象
     */
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 从 SpringBoot 中获取 Response 请求对象
     *
     * @return 返回当前请求的 Response 对象
     */
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 获取ServletRequestAttributes
     *
     * @return
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        return (ServletRequestAttributes) requestAttributes;
    }

}
