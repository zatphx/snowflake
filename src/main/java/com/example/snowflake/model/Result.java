package com.example.snowflake.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Result implements Serializable {
    private BigDecimal result;

    public Result() {
    }

    public Result(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
