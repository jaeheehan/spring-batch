package com.example.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor3 implements ItemProcessor<ProcessInfo, ProcessInfo> {

    @Override
    public ProcessInfo process(ProcessInfo item) throws Exception {
        System.out.println("CustomItemProcessor3");
        return item;
    }
}
