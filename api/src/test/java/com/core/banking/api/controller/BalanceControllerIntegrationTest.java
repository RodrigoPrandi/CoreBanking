package com.core.banking.api.controller;

import com.core.banking.business.model.Account;
import com.core.banking.business.model.TransactionType;
import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.repository.TransactionRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BalanceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
    }


    @Test
    void whenGetBalanceWithOutAccountIdThenExpected404() throws Exception {
        mockMvc.perform(get("/balance"))
                .andExpect(status().is(400))
                .andReturn();
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
        var externalId = RandomString.make();
        var account = new Account(externalId);
        account.addTransaction(TransactionType.CREDIT, BigDecimal.valueOf(20));

        accountRepository.save(account);

        var result = mockMvc.perform(get("/balance?account_id="+externalId))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("20.00", result);
    }
}