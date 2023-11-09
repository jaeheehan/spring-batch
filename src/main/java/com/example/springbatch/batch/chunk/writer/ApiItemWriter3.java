package com.example.springbatch.batch.chunk.writer;

import com.example.springbatch.batch.domain.ApiRequestVO;
import com.example.springbatch.batch.domain.ApiResponseVO;
import com.example.springbatch.service.AbstractApiService;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

public class ApiItemWriter3 extends FlatFileItemWriter<ApiRequestVO> {

    private final AbstractApiService apiService;

    public ApiItemWriter3(final AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(final java.util.List<? extends ApiRequestVO> items) throws Exception {
        ApiResponseVO service = apiService.service(items);
        System.out.println("ApiResponseVO = " + service);

        for (final ApiRequestVO item : items) {
            item.setApiResponseVO(service);
        }

        super.setResource(new FileSystemResource("src/main/resources/product3.txt"));
        super.open(new ExecutionContext());
        super.setLineAggregator(new DelimitedLineAggregator<>());
        super.setAppendAllowed(true);
        super.write(items);
    }
}
