package com.core.banking.api.controller;

import com.core.banking.business.model.Account;
import com.core.banking.business.model.TransactionType;
import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ResetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void whenPostResetApiThenResetApplication() throws Exception {
        setValuesDatabase();
        Assert.assertTrue(accountRepository.count() > 0);
        Assert.assertTrue(transactionRepository.count() > 0);

        var result = mockMvc.perform(post("/reset"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("OK", result);
        Assert.assertFalse(accountRepository.count() > 0);
        Assert.assertFalse(transactionRepository.count() > 0);
    }

    void setValuesDatabase() {
        var account1 = new Account("3213");
        account1.addTransaction(TransactionType.CREDIT, BigDecimal.TEN);
        accountRepository.save(account1);

        var account2 = new Account("sdfasf");
        account2.addTransaction(TransactionType.DEBIT, BigDecimal.ONE);
        accountRepository.save(account2);
    }
}