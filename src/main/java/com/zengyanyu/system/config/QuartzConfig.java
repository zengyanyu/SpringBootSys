package com.zengyanyu.system.config;

import com.zengyanyu.system.job.ItemJob;
import com.zengyanyu.system.job.RemoveLogRecordJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail recordLogDetail() {
        return JobBuilder.newJob(RemoveLogRecordJob.class)
                .withIdentity("recordLogDetail", "group1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(recordLogDetail())
                .withIdentity("sampleJobTrigger", "group1")
                // 每天的凌晨2点执行任务
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 2 * * ?"))
                .build();
    }

    @Bean
    public JobDetail itemJobDetail() {
        return JobBuilder.newJob(ItemJob.class)
                .withIdentity("itemJobDetail", "group1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger itemJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(itemJobDetail())
                .withIdentity("itemJobTrigger", "group1")
                // 每3妙执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .build();
    }

}
