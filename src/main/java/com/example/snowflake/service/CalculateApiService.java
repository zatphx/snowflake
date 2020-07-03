package com.example.snowflake.service;

import com.example.snowflake.model.BinaryOperation;

import java.math.BigDecimal;

public interface CalculateApiService {

    BigDecimal getApiResult(BinaryOperation binaryOperation);

    BigDecimal postApiResult(BinaryOperation binaryOperation);
}
