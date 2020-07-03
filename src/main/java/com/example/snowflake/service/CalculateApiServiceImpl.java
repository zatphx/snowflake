package com.example.snowflake.service;

import com.example.snowflake.exception.InvalidOperationException;
import com.example.snowflake.model.BinaryOperation;
import com.example.snowflake.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class CalculateApiServiceImpl implements CalculateApiService {

    private RestTemplate restTemplate;
    private final String api_url;

    public CalculateApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    private String generateApiString(BinaryOperation binaryOperation) {
        String op = new String();
        switch (binaryOperation.getOperator()) {
            case "*":
                op = "/multiply/";
                break;
            case "/":
                op = "/divide/";
                break;
            case "+":
                op = "/add/";
                break;
            case "-":
                op = "/subtract/";
                break;
            default:
                throw new InvalidOperationException();
        }
        return op + binaryOperation.getOperandOne() + "/" + binaryOperation.getOperandTwo();
    }

    @Override
    public BigDecimal getApiResult(BinaryOperation binaryOperation) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(api_url)
                .path(generateApiString(binaryOperation));
        Result result = restTemplate.getForObject(uriComponentsBuilder.toUriString(), Result.class);
        return result.getResult();
    }

    @Override
    public BigDecimal postApiResult(BinaryOperation binaryOperation) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(api_url)
                .path("/calculate");
        Result result = restTemplate.postForObject(uriComponentsBuilder.toUriString(), binaryOperation, Result.class);
        return result.getResult();
    }
}
