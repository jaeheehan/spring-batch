package com.example.springbatch;

import org.springframework.batch.item.ItemReader;

import java.util.List;

public class CustomeItemReader implements ItemReader<Customer> {

    private List<Customer> list;

    public CustomeItemReader(List<Customer> list) {
        this.list = list;
    }

    @Override
    public Customer read() throws Exception {
        if (!list.isEmpty()) {
            return list.remove(0);
        }
        return null;
    }
}
