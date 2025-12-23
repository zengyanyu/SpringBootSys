package com.zengyanyu.system.job;

import com.zengyanyu.system.service.ILogRecordService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 定时删除-日志记录
 *
 * @author zengyanyu
 */
public class RemoveLogRecordJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(RemoveLogRecordJob.class);

    @Resource
    private ILogRecordService logRecordService;

    @Override
    public void execute(JobExecutionContext context) {
        logger.info("定时删除-日志记录-quartz任务");
        logRecordService.removeLogRecordByConditions();
    }
}
