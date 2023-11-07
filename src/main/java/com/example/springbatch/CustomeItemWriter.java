package com.example.springbatch;

import org.springframework.batch.item.ItemWriter;

public class CustomeItemWriter  implements ItemWriter<Customer>{

    @Override
    public void write(final java.util.List<? extends Customer> list) throws Exception {
        for (Customer customer : list) {
            System.out.println("customer = " + customer);
        }
    }
}
