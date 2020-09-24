package com.core.banking.api.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BalanceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void whenGetBalanceWithOutAccountIdThenExpected404() throws Exception {
        var result = mockMvc.perform(get("/balance"))
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("0", result);
    }

    @Test
    void whenGetBalanceWithInvalidAccountIdThenExpected404() throws Exception {
        var result = mockMvc.perform(get("/balance?account_id=1234"))
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("0", result);
    }

    @Test
    void whenGetBalanceWithValidAccountIdThenExpectedBalanceAccount() throws Exception {
        var result = mockMvc.perform(get("/balance?account_id=100"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("20", result);
    }

}