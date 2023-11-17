package com.example.springbatch;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

public class CustomSkipListener implements SkipListener<Integer, String>{
    @Override
    public void onSkipInRead(final Throwable t) {
        System.out.println(">> onSkipInRead : " + t.getMessage());

    }

    @Override
    public void onSkipInWrite(final String item, final Throwable t) {
        System.out.println(">> onSkipInWrite : " + item + " : " + t.getMessage());
    }

    @Override
    public void onSkipInProcess(final Integer item, final Throwable t) {
        System.out.println(">> onSkipInProcess : " + item + " : " + t.getMessage());
    }


}

