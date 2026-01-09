package com.zengyanyu.system.util;

import com.zengyanyu.system.bean.DefaultThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

    /**
     * 定时任务执行器线程池
     *
     * @param corePoolSize 线程池大小
     * @param maximumPoolSize 最大线程池大小
     * @param keepAliveTime 保活时间
     * @param unit 时间单位：秒
     * @param workQueue 工作队列
     * @param threadFactory 线程工厂
     * @param handler 拒绝执行处理器
     */
    private final ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(
            4,
            4,
            30, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2048),
            new DefaultThreadFactory("scanner-schedule-thread"),
            new ThreadPoolExecutor.AbortPolicy()
    );
}
