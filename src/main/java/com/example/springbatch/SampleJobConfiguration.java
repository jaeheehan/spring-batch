package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SampleJobConfiguration {


    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job BatchJob() throws Exception {
        return this.jobBuilderFactory.get("Job")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(2)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }


    @Bean
    public ItemReader<Customer> customItemReader() throws Exception {

        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("firstName", "A%");

       return new JdbcPagingItemReaderBuilder<Customer>()
               .name("jdbcPaingReader")
               .pageSize(10)
               .dataSource(dataSource)
               .rowMapper(new BeanPropertyRowMapper<>(Customer.class))
               .queryProvider(createQueryProvider())
               .parameterValues(parameterValues)
               .build();

    }

    @Bean
    public PagingQueryProvider createQueryProvider() throws Exception {




        SqlPagingQueryProviderFactoryBean queryProvier = new SqlPagingQueryProviderFactoryBean();
        queryProvier.setDataSource(dataSource);
        queryProvier.setSelectClause("id, firstName, lastName, birthdate");
        queryProvier.setFromClause("from customer");
        queryProvier.setWhereClause("where firstName like :firstName");

        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);

        queryProvier.setSortKeys(sortKeys);

        return queryProvier.getObject();

    }


    @Bean
    public ItemWriter<Customer> customItemWriter() {
        return items -> {
            for (Customer item : items) {
                System.out.println(item.toString());
            }
        };
    }
}
