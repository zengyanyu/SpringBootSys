package com.zengyanyu.system.job;

import com.zengyanyu.system.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 注解式：定时任务 @Scheduled
 *
 * @author zengyanyu
 */
@Slf4j
@Component
public class ScheduledTask {

    /**
     * 初始化延迟两分钟，然后每两分钟再执行一次
     */
    @Scheduled(initialDelay = 1000 * 60 * 2, fixedDelay = 1000 * 60 * 2)
    public void testTask() {
        log.info("com.zengyanyu.system.job.ScheduledTask 定时任务执行成功");
        log.info("当前时间 = " + DateUtils.SIMPLE_DATE_FORMAT.format(new Date()));

//        log.error("com.zengyanyu.system.job.ScheduledTask 定时任务执行成功");
//        log.error("当前时间 = " + DateUtils.SIMPLE_DATE_FORMAT.format(new Date()));
    }
}
