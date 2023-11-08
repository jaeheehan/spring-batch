package com.example.springbatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class ProcessorClassfier<C,T> implements Classifier<C,T>{

    private Map<Integer, ItemProcessor<ProcessInfo, ProcessInfo>> processorMap = new HashMap<>();
    @Override
    public T classify(final C classifiable) {
        return (T)processorMap.get(((ProcessInfo)classifiable).getId());
    }

    public void setProcessorMap(Map<Integer, ItemProcessor<ProcessInfo, ProcessInfo>> processorMap) {
        this.processorMap = processorMap;
    }
}
