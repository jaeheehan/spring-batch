package com.example.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class CustomitemProcessor2 implements ItemProcessor<String, String> {
    int cnt = 0;

    @Override
    public String process(final String item) throws Exception {
        cnt++;
        return item + cnt;
    }
}
