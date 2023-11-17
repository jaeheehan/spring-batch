package com.example.springbatch;

import javax.batch.api.chunk.listener.ItemReadListener;

public class CustomItemReadListener implements ItemReadListener {
    @Override
    public void beforeRead() throws Exception {
        System.out.println("beforeRead");
    }

    @Override
    public void afterRead(final Object o) throws Exception {
        System.out.println("afterRead");
    }

    @Override
    public void onReadError(final Exception e) throws Exception {
        System.out.println("onReadError");
    }
}
