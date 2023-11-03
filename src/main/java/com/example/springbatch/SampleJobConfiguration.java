package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SampleJobConfiguration {


    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job BatchJob() {
        return this.jobBuilderFactory.get("Job")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .next(decider())
                .from(decider()).on("ODD").to(odd())
                .from(decider()).on("EVEN").to(even())
                .end()
                .build();
    }


    @Bean
    public JobExecutionDecider decider() {
        return new CustomDecider();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("startStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("this is the start tasklet");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Step even() {
        return stepBuilderFactory.get("even")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("even has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step odd() {
        return stepBuilderFactory.get("odd")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("odd has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
