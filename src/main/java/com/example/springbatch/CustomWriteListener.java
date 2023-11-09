package com.example.springbatch;

import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class CustomWriteListener implements ItemWriteListener<Customer>{
    @Override
    public void beforeWrite(final List<? extends Customer> items) {

    }

    @Override
    public void afterWrite(final List<? extends Customer> items) {
        System.out.println("Thread : " + Thread.currentThread().getName() + ", write items : " + items.size());

    }

    @Override
    public void onWriteError(final Exception exception, final List<? extends Customer> items) {

    }
}
