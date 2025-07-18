package com.zengyanyu.system.util;

import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtils {

    private UUIDUtils() {
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
