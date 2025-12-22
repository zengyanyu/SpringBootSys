package com.zengyanyu.system.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author 86134
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDUtils {

    /**
     * 生成UUID
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
