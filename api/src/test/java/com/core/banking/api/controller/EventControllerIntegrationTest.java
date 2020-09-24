package com.core.banking.api.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    void setUp() {
        //reset application
    }

    @Test
    void whenPostDepositEventThenSaveEventAndReturnCreated() throws Exception {

        var request = "{\"type\":\"deposit\", \"destination\":\"100\", \"amount\":10}";

        var result = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(
                "{\"destination\": {\"id\":\"100\", \"balance\":10}}",
                result, JSONCompareMode.LENIENT);
    }


    @Test
    void whenPostWithdrawEventFromExistingAccountThenSaveEventAndReturnCreated() throws Exception {

        //initialize account

        var request = "{\"type\":\"withdraw\", \"origin\":\"100\", \"amount\":5}";

        var result = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(
                "{\"origin\": {\"id\":\"100\", \"balance\":15}}",
                result, JSONCompareMode.LENIENT);
    }

    @Test
    void whenPostWithdrawEventFromNonExistingAccountThenReturn404() throws Exception {

        var request = "{\"type\":\"withdraw\", \"origin\":\"200\", \"amount\":10}";

        var result = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("0", result);
    }

    @Test
    void whenPostTransferEventFromExistingAccountThenSaveEventAndReturnCreated() throws Exception {

        //initialize account

        var request = "{\"type\":\"transfer\", \"origin\":\"100\", \"amount\":15, \"destination\":\"300\"}";

        var result = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(
                "{\"origin\": {\"id\":\"100\", \"balance\":0}, \"destination\": {\"id\":\"300\", \"balance\":15}}",
                result, JSONCompareMode.LENIENT);
    }

    @Test
    void whenPostTransferEventFromNonExistingAccountThenReturn404() throws Exception {

        var request = "{\"type\":\"transfer\", \"origin\":\"200\", \"amount\":15, \"destination\":\"300\"}";

        var result = mockMvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("0", result);
    }

}