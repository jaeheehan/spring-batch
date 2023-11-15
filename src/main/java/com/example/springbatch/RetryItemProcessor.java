package com.example.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class RetryItemProcessor implements ItemProcessor<String, String> {

    private int cnt = 0;
    @Override
    public String process(final String item) throws Exception {

        if(item.equals("2") || item.equals("3")){
            cnt++;
            throw new RetryableException();
        }

        return item;
    }
}
