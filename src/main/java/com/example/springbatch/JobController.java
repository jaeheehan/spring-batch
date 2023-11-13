package com.example.springbatch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Set;

@RestController
public class JobController {

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    private JobOperator jobOperator;

    @PostMapping(value="/batch/start")
    public String startBatch(@RequestBody JobInfo jobInfo) throws Exception {

        for(Iterator<String> iterator = jobRegistry.getJobNames().iterator(); iterator.hasNext();){
            SimpleJob job = (SimpleJob) jobRegistry.getJob(iterator.next());
            System.out.println("jobname : " + job.getName());
            jobOperator.start(job.getName(), "id=" + jobInfo.getId());
        }

        return "Batch job has been invoked";
    }


    @GetMapping(value = "/batch/stop")
    public String stop() throws Exception {

        for(Iterator<String> iterator = jobRegistry.getJobNames().iterator(); iterator.hasNext();){

            SimpleJob job = (SimpleJob)jobRegistry.getJob(iterator.next());
            System.out.println("job name: " + job.getName());

            Set<JobExecution> runningJobExecutions = jobExplorer.findRunningJobExecutions(job.getName());
            JobExecution jobExecution = runningJobExecutions.iterator().next();

            jobOperator.stop(jobExecution.getId());
        }

        return "batch is stopped";
    }

    @PostMapping(value="/batch/restart")
    public String restart() throws Exception {

        for(Iterator<String> iterator = jobRegistry.getJobNames().iterator(); iterator.hasNext();){
            SimpleJob job = (SimpleJob) jobRegistry.getJob(iterator.next());
            System.out.println("jobname : " + job.getName());

            JobInstance lastJobInstance = jobExplorer.getLastJobInstance(job.getName());
            JobExecution jobExecution = jobExplorer.getLastJobExecution(lastJobInstance);

            jobOperator.restart(jobExecution.getId());
        }

        return "Batch job has been restart";
    }

}

