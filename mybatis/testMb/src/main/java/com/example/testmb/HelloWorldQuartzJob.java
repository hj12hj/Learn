package com.example.testmb;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author: hj
 * @date: 2022/10/18
 * @time: 2:13 PM
 */

public class HelloWorldQuartzJob implements Job {

    /**
     * 定时任务：当所定时间结束后，要执行的方法。
     * @param jobExecutionContext
     */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String strTime = new SimpleDateFormat("HH-mm-ss").format(new Date());//开始时间
        //执行任务代码块
        System.out.println(strTime + ":Helloword");
    }

}

