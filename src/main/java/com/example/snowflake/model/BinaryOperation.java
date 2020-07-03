package com.example.snowflake.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class BinaryOperation {

    @NotNull
    private BigDecimal operandOne;
    @NotNull
    private BigDecimal operandTwo;

    @NotNull
    @Size(min = 1, max = 1, message = "Only one operator allowed")
    @Pattern(regexp = "[\\*\\-+\\/]", message = "Only operations +, -, /, * are supported")
    private String operator;

    public BigDecimal getOperandOne() {
        return operandOne;
    }

    public void setOperandOne(BigDecimal operandOne) {
        this.operandOne = operandOne;
    }

    public BigDecimal getOperandTwo() {
        return operandTwo;
    }

    public void setOperandTwo(BigDecimal operandTwo) {
        this.operandTwo = operandTwo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}

