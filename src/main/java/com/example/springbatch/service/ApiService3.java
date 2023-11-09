package com.example.springbatch.service;

import com.example.springbatch.batch.domain.ApiInfo;
import com.example.springbatch.batch.domain.ApiResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService3 extends AbstractApiService{

    @Override
    public ApiResponseVO doApiService(RestTemplate restTemplate, ApiInfo apiInfo){

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8083/api/product/3", apiInfo, String.class);

        int statusCodeValue = response.getStatusCodeValue();
        ApiResponseVO apiResponseVO = ApiResponseVO.builder().status(statusCodeValue).message(response.getBody()).build();

        return apiResponseVO;
    }
}
