package com.example.springbatch.batch.partition;

import com.example.springbatch.batch.domain.ProductVO;
import com.example.springbatch.batch.job.api.QueryGenerator;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ProductPartitioner implements Partitioner {

    private DataSource dataSource;

    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, ExecutionContext> partition(final int gridSize) {

        ProductVO[] productVOList = QueryGenerator.getProductList(dataSource);
        Map<String, ExecutionContext> result = new HashMap<>();

        int number = 0;

        for(int i = 0; i < productVOList.length; i++){
            ExecutionContext value = new ExecutionContext();

            result.put("partition" + number, value);
            value.put("product", productVOList[i]);
            number ++;
        }

        return result;
    }
}
