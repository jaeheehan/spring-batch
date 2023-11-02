package com.example.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomeTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        String stepName = contribution.getStepExecution().getStepName();
        String jobName = chunkContext.getStepContext().getJobName();

        System.out.println("Step name: " + stepName);
        System.out.println("Job name: " + jobName);

        return RepeatStatus.FINISHED;
    }
}
