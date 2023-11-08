package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SampleJobConfiguration {


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
                .<ProcessInfo, ProcessInfo>chunk(10)
                .reader(new ItemReader<ProcessInfo>() {
                    int i = 0;
                    @Override
                    public ProcessInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        i++;
                        ProcessInfo processInfo = ProcessInfo.builder().id(i).build();
                        return i > 3 ? null : processInfo;
                    }
                })
                .processor(customItemProcessor())
                .writer(items -> {
                    for (ProcessInfo item : items) {
                        System.out.println(item);
                    }
                })
                .build();
    }

    public ItemProcessor<ProcessInfo, ProcessInfo> customItemProcessor() {

        ClassifierCompositeItemProcessor<ProcessInfo, ProcessInfo> processor = new ClassifierCompositeItemProcessor<>();

        ProcessorClassfier<ProcessInfo, ItemProcessor<?, ? extends ProcessInfo>> classifier = new ProcessorClassfier();
        Map<Integer, ItemProcessor<ProcessInfo, ProcessInfo>> processorMap = new HashMap<>();
        processorMap.put(1, new CustomItemProcessor1());
        processorMap.put(2, new CustomItemProcessor2());
        processorMap.put(3, new CustomItemProcessor3());

        classifier.setProcessorMap(processorMap);
        processor.setClassifier(classifier);

        return processor;
    }


}
