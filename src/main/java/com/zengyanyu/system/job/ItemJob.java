package com.zengyanyu.system.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ItemJob.class);

    @Autowired
    private SocketService socketService;

    @Override
    public void execute(JobExecutionContext context) {
//        logger.info("context item job ================");
//        socketService.sendNotification(" context item job ===============");
    }
}
