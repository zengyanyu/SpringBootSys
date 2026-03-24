/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.service;

import com.zengyanyu.system.util.DateUtils;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author zengyanyu
 */
@Service
public class RetryTestService {

    /**
     * 重试核心方法
     *
     * @Retryable 参数说明：
     * value：指定发生什么异常时重试（这里是所有异常）
     * maxAttempts：最大重试次数（默认3次，包含第一次执行）
     * delay：第一次重试延迟时间（毫秒）
     * multiplier：延迟时间倍数（指数退避策略）
     * backoff：重试间隔配置
     */
    @Retryable(
            value = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public String testRetry(int num) throws Exception {
        System.out.println("方法执行，当前时间：" + DateUtils.SIMPLE_DATE_FORMAT.format(System.currentTimeMillis()));

        // 模拟业务异常（触发重试）
        if (num < 10) {
            throw new Exception("参数小于10，执行失败，触发重试");
        }

        return "执行成功！参数：" + num;
    }

    /**
     * 重试全部失败后的回调方法
     *
     * @Recover：标记重试失败兜底方法 方法参数/返回值必须和 @Retryable 方法一致
     */
    @Recover
    public String recover(Exception e, int num) {
        System.out.println("重试全部失败！执行兜底逻辑");
        return "重试失败，错误信息：" + e.getMessage() + "，参数：" + num;
    }
}
