package com.zengyanyu.system.job;

import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 注解式：定时任务 @Scheduled
 *
 * @author zengyanyu
 */
public class ScheduledTask {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 初始化延迟两分钟，然后每两分钟再执行一次
     */
    @Scheduled(initialDelay = 1000 * 60 * 2, fixedDelay = 1000 * 60 * 2)
    public void testTask() {
        System.out.println("com.zengyanyu.system.job.ScheduledTask 定时任务执行成功");
        System.out.println("当前时间 = " + SIMPLE_DATE_FORMAT.format(new Date()));
    }
}
