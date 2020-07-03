package com.example.snowflake.controllers;

import javax.validation.Valid;

import com.example.snowflake.model.BinaryOperation;
import com.example.snowflake.service.CalculateApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;

@Controller
public class CalculateController implements WebMvcConfigurer {

    private CalculateApiService calculateApiService;

    @Autowired
    public CalculateController(CalculateApiService calculateApiService) {
        this.calculateApiService = calculateApiService;
    }

    @GetMapping("/calculate")
    public String calculateForm(Model model) {
        model.addAttribute("binaryOperation", new BinaryOperation());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculateResult(Model model, @Valid @ModelAttribute("binaryOperation") BinaryOperation binaryOperation,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        BigDecimal result = calculateApiService.getApiResult(binaryOperation);
        model.addAttribute("result", result);
        return "result";
    }

}
