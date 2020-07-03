package com.example.snowflake.controllers;

import com.example.snowflake.exception.InvalidOperationException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler implements ErrorController {

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<Object> handleInvalidOperation(Exception exception) {
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleInvalidConstraints(Exception exception) {
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/error")
    public ResponseEntity<Object> handleError(HttpServletRequest request) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
