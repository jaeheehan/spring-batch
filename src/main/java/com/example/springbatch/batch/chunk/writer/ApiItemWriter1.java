package com.example.springbatch.batch.chunk.writer;

import com.example.springbatch.batch.domain.ApiRequestVO;
import com.example.springbatch.batch.domain.ApiResponseVO;
import com.example.springbatch.service.AbstractApiService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

public class ApiItemWriter1 implements ItemWriter<ApiRequestVO> {

    private final AbstractApiService apiService;

    public ApiItemWriter1(final AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(final java.util.List<? extends ApiRequestVO> items) throws Exception {
        ApiResponseVO service = apiService.service(items);
        System.out.println("ApiResponseVO = " + service);
    }
}
