package com.example.springbatch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class SkipItemWriter implements ItemWriter<String>{

    private int cnt = 0;
    @Override
    public void write(final List<? extends String> items) throws Exception {

        for (String item : items) {
            if(item.equals("-12")) {
                System.out.println("ItemWriter : " + item);
                cnt++;
                throw new SkippableException("Write failed. cnt:" + cnt);
            }
            else {
                System.out.println("ItemWriter : " + item);
            }
        }


    }

    @Bean
    public SkipItemProcessor processor() {
        SkipItemProcessor processor = new SkipItemProcessor();
        return processor;
    }

    @Bean
    public SkipItemWriter writer() {
        SkipItemWriter writer = new SkipItemWriter();
        return writer;
    }
}
