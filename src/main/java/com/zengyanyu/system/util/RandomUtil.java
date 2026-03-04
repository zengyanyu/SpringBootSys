/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * 生成随机数工具类
 * @author zengyanyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtil {

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
