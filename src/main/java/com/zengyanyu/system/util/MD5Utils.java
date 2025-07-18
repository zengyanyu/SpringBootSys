package com.zengyanyu.system.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * MD5工具类
 */
public class MD5Utils {

    private MD5Utils() {
    }

    /**
     * @param plainStr 明文
     * @return 密文
     */
    public static String md5(String plainStr) {
        return DigestUtils.md5DigestAsHex(plainStr.getBytes(StandardCharsets.UTF_8));
    }

}
