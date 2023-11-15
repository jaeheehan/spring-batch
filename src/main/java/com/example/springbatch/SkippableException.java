package com.example.springbatch;

public class SkippableException extends Exception {
    public SkippableException(final String s) {
        super(s);
    }
}
