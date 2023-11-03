package com.example.springbatch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

public class MyStepListener implements org.springframework.batch.core.StepExecutionListener {
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        stepExecution.getExecutionContext().put("name2", "babo");
    }

    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        return null;
    }
}
