package com.hj.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

/**
 * @author: hj
 * @date: 2022/11/21
 * @time: 9:55 AM
 */
public class MyJob implements Job {

    Logger logger = org.slf4j.LoggerFactory.getLogger(MyJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("hello world");
    }
}
