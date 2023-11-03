package com.example.springbatch;

import org.springframework.batch.core.JobExecution;

public class MyListener implements org.springframework.batch.core.JobExecutionListener {
    @Override
    public void beforeJob(final JobExecution jobExecution) {
        jobExecution.getExecutionContext().put("name", "steve");
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {

    }
}
