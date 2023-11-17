package com.example.springbatch;

import javax.batch.api.chunk.listener.ItemReadListener;
import javax.batch.api.chunk.listener.ItemWriteListener;
import java.awt.event.ItemListener;
import java.util.List;

public class CustomItemWriterListener implements ItemWriteListener {

    @Override
    public void onWriteError(final List<Object> list, final Exception e) throws Exception {
        System.out.println("onWriteError");
    }

    @Override
    public void beforeWrite(final List items) {
        System.out.println("beforeWrite");
    }

    @Override
    public void afterWrite(final List items) {
        System.out.println("afterWrite");
    }


}
