package com.example.springbatch;

public class CustomService<T> {

    public void joinCustomer(T item) {
        System.out.println(item);
    }
}
