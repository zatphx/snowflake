package com.example.snowflake.service;

import com.example.snowflake.SnowflakeApplication;
import com.example.snowflake.exception.InvalidOperationException;
import com.example.snowflake.model.BinaryOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

@SpringBootTest(classes = SnowflakeApplication.class)
public class CalculateApiServiceTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private CalculateApiService calculateApiService;

    @BeforeClass
    public void setup() {
        calculateApiService = webApplicationContext.getBean(CalculateApiService.class);
    }

    @Test
    public void testGetApiResultValidOperation() {
        BinaryOperation binaryOperation = new BinaryOperation();
        binaryOperation.setOperandOne(new BigDecimal(3));
        binaryOperation.setOperandTwo(new BigDecimal(3));
        binaryOperation.setOperator("*");

        BigDecimal result = calculateApiService.getApiResult(binaryOperation);

        Assert.assertEquals(result, new BigDecimal(9));

    }


    @Test
    public void testPostApiResultValidOperation() {
        BinaryOperation binaryOperation = new BinaryOperation();
        binaryOperation.setOperandOne(new BigDecimal(3));
        binaryOperation.setOperandTwo(new BigDecimal(3));
        binaryOperation.setOperator("*");

        BigDecimal result = calculateApiService.postApiResult(binaryOperation);
        Assert.assertEquals(result, new BigDecimal(9));

    }

    @Test(expectedExceptions = HttpClientErrorException.class)
    public void testPostApiResultInvalidOperation() {
        BinaryOperation binaryOperation = new BinaryOperation();
        binaryOperation.setOperandOne(new BigDecimal(3));
        binaryOperation.setOperandTwo(new BigDecimal(3));
        binaryOperation.setOperator("%");

        BigDecimal result = calculateApiService.postApiResult(binaryOperation);
        Assert.assertEquals(result, new BigDecimal(9));

    }

    @Test(expectedExceptions = InvalidOperationException.class)
    public void testInvalidOperationFail() {
        BinaryOperation binaryOperation = new BinaryOperation();
        binaryOperation.setOperandOne(new BigDecimal(3));
        binaryOperation.setOperandTwo(new BigDecimal(3));
        binaryOperation.setOperator("%");

        calculateApiService.getApiResult(binaryOperation);
    }


}
