package com.example.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class RetryItemProcessor implements ItemProcessor<String, String> {

    private int cnt = 0;
    @Override
    public String process(final String item) throws Exception {

        cnt++;
        throw new RetryableException();

        //return null;
    }
}
