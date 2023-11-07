package com.example.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class CustomeItemProcessor implements ItemProcessor<Customer, Customer>{
    @Override
    public Customer process(final Customer customer) throws Exception {
        customer.setName(customer.getName().toUpperCase());
        return customer;
    }
}
