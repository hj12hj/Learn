package com.hj.job;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author: hj
 * @date: 2022/11/21
 * @time: 1:55 PM
 */
public class MyStdSchedulerFactory extends StdSchedulerFactory {

    @Override
    public Scheduler getScheduler() throws SchedulerException {
        return super.getScheduler();
    }

}
