package com.core.banking.api.controller;

import com.core.banking.business.model.Account;
import com.core.banking.business.model.Transaction;
import com.core.banking.business.model.TransactionType;
import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.Before;
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
        var account1 = new Account();
        account1.setExternalId("");
        var transaction1 = new Transaction();
        transaction1.setValue(BigDecimal.TEN);
        transaction1.setType(TransactionType.CREDIT);
        account1.addTransaction(transaction1);
        accountRepository.save(account1);

        var account2 = new Account();
        account2.setExternalId("");
        var transaction2 = new Transaction();
        transaction2.setValue(BigDecimal.TEN);
        transaction2.setType(TransactionType.CREDIT);
        account1.addTransaction(transaction2);
        accountRepository.save(account2);
    }
}