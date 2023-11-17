package com.example.springbatch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.util.Date;

public class CustomJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(final JobExecution jobExecution) {
        System.out.println("Job is started");
        System.out.println("JobName : " + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {
        long startTime = jobExecution.getStartTime().getTime();
        long endTime = jobExecution.getEndTime().getTime();

        System.out.println("총 소요시간 : " + (endTime - startTime) / 1000 + " 초");

    }
}
