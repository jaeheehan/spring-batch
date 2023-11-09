package com.example.springbatch.batch.listener;

import org.springframework.batch.core.JobExecution;

public class JobListener implements org.springframework.batch.core.JobExecutionListener{
    @Override
    public void beforeJob(final JobExecution jobExecution) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {
        System.out.println("##############################");
    }
}
