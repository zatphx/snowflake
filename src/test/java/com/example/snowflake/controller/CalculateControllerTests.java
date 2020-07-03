package com.example.snowflake.controller;

import com.example.snowflake.SnowflakeApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SnowflakeApplication.class)
public class CalculateControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testPostValidCalculationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("operandOne", "1")
                .param("operator", "+")
                .param("operandTwo", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("binaryOperation"))
                .andExpect(model().hasNoErrors());

    }

    @Test
    public void testPostNullCalculationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("operandOne", "")
                .param("operator", "")
                .param("operandTwo", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().hasErrors());

    }

    @Test
    public void testPostInvalidCalculationFormHasErrors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("operandOne", "1")
                .param("operator", "%")
                .param("operandTwo", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().hasErrors());
    }
}
