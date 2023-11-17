package com.example.springbatch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class CustomStepExecutionListener implements StepExecutionListener {
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        String stepName = stepExecution.getStepName();
        System.out.println("stepExecution = " + stepExecution);
        stepExecution.getExecutionContext().put("name", "user1");
    }

    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        ExitStatus exitStatus = stepExecution.getExitStatus();
        System.out.println("exitStatus = " + exitStatus);
        BatchStatus status = stepExecution.getStatus();
        System.out.println("status : " + status);
        String name = (String)stepExecution.getExecutionContext().get("name");
        System.out.println("name = " + name);

        return ExitStatus.COMPLETED;
    }
}
