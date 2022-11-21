package com.hj;

import com.hj.job.MyJob;
import com.hj.job.MyJobListenner;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * @author: hj
 * @date: 2022/11/21
 * @time: 9:57 AM
 */
public class App {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger triger = TriggerBuilder.newTrigger()
                .withIdentity("t1", "g1")
//                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever().withMisfireHandlingInstructionIgnoreMisfires())
                .build();

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("j1", "g1")
                .build();




        scheduler.getListenerManager().addJobListener(new MyJobListenner(), KeyMatcher.keyEquals(jobDetail.getKey()));

        scheduler.scheduleJob(jobDetail, triger);



//        scheduler.pauseJobs(GroupMatcher.jobGroupEquals("g1"));

        scheduler.start();


    }

}
