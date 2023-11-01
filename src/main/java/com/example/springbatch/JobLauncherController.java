package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequiredArgsConstructor
@Slf4j
public class JobLauncherController {

    private final Job job;
    private final JobLauncher jobLauncher;
    private final BasicBatchConfigurer basicBatchConfigurer;

    @PostMapping("/batch")
    public String launch(@RequestBody Member member) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("member", member.getId())
                .addDate("date", new Date())
                .toJobParameters();

        SimpleJobLauncher jobLauncher1 = (SimpleJobLauncher) basicBatchConfigurer.getJobLauncher();
        jobLauncher1.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher1.run(job, jobParameters);

        return "batch completed";
    }

}
