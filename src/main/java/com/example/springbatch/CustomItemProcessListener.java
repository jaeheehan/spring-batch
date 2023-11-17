package com.example.springbatch;

import org.springframework.batch.core.ItemProcessListener;

public class CustomItemProcessListener implements ItemProcessListener<Integer, String> {
    @Override
    public void beforeProcess(final Integer item) {
        System.out.println("beforeProcess");
    }

    @Override
    public void afterProcess(final Integer item, final String result) {
        System.out.println("afterProcess");
    }

    @Override
    public void onProcessError(final Integer item, final Exception e) {
        System.out.println("onProcessError");
    }
}
