package com.example.springbatch.batch.classifier;

import com.example.springbatch.batch.domain.ApiRequestVO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class WriteClassifier<C, T> implements Classifier<C, T> {


    private Map<String, ItemWriter<ApiRequestVO>> writerMap = new HashMap<>();

    @Override
    public T classify(final C classifiable) {
        return (T)writerMap.get(((ApiRequestVO)classifiable).getProductVO().getType());
    }

    public void setWriterMap(final Map<String, ItemWriter<ApiRequestVO>> writerMap) {
        this.writerMap = writerMap;
    }
}
