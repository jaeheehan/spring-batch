package com.example.springbatch.template;

import com.example.springbatch.RetryableException;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SampleJobConfiguration2 {


    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<String, Customer>chunk(5)
                .reader(reader())
                .processor(itemProcessor())
                .writer(items -> items.forEach(item -> System.out.println(">> " + item)))
                .faultTolerant()
                /*.skip(RetryableException.class)
                .skipLimit(2)
                .retry(RetryableException.class)
                .retryLimit(2)
                .retryPolicy(retryPolicy())*/
                .build();
    }


    @Bean
    public ListItemReader<String> reader() {
        ArrayList<String> items = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            items.add(String.valueOf(i));
        }
        return new ListItemReader<>(items);
    }

    @Bean ItemProcessor<String, Customer> itemProcessor(){
        return new RetryItemProcessor2();
    }


    @Bean
    public RetryTemplate retryTemplate(){
        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
        exceptionMap.put(RetryableException.class, true);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000L);

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(2, exceptionMap);
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        //retryTemplate.setBackOffPolicy(backOffPolicy);

        return retryTemplate;
    }

}
