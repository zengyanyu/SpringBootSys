package com.zengyanyu.system.util;

import java.util.Random;

/**
 * 生成随机数工具类
 */
public class RandomUtil {

    private RandomUtil() {
    }

    /**
     * 生成一个6位随机数
     *
     * @return
     */
    public static String randomNumber() {
        // 创建Random对象实例
        Random random = new Random();
        // 生成一个六位随机数，范围是100000到999999
        return String.valueOf(random.nextInt(900000) + 100000);
    }

}
