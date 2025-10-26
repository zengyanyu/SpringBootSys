package com.zengyanyu.system.config;

import com.zengyanyu.system.entity.UserInfo;

/**
 * 用户上下文占位符
 * @author zengyanyu
 */
public class UserContextHolder {

    private static final ThreadLocal<UserInfo> userContextHolder = new ThreadLocal<>();

    public static void setUserContext(UserInfo userContext) {
        userContextHolder.set(userContext);
    }

    public static UserInfo getUserContext() {
        return userContextHolder.get();
    }

    public static void clear() {
        userContextHolder.remove();
    }
}
