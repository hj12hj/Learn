package com.example.testmb;

import com.example.testmb.entity.Job;
import com.example.testmb.mapper.JobMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author: hj
 * @date: 2022/10/18
 * @time: 2:38 下午
 */
@Component
public class Init {

    @Autowired
    Scheduler scheduler;


    @Autowired
    JobMapper jobMapper;


    @PostConstruct
    public void init() throws SchedulerException {
        List<Job> all = jobMapper.findAll();
        for (Job job : all) {
            JobDetail jobDetail = JobBuilder.newJob(HelloWorldQuartzJob.class).withIdentity(job.getName(),job.getGroupName()).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getName(),job.getGroupName()).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(Integer.parseInt(job.getCronExpression())).repeatForever()).build();

            scheduler.scheduleJob(jobDetail,trigger);
            if ("0".equals(job.getStatus())){
                scheduler.pauseJob(jobDetail.getKey());
            }

        }
    }

}
