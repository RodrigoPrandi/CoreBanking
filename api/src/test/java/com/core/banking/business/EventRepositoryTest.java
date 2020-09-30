package com.core.banking.business;

import com.core.banking.business.model.Account;
import com.core.banking.business.model.TransactionType;
import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.repository.TransactionRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    public void findAllByAccount() {
        var externalId = RandomString.make();
        var account = new Account(externalId);
        account.addTransaction(TransactionType.CREDIT, BigDecimal.valueOf(100));
        account.addTransaction(TransactionType.DEBIT, BigDecimal.valueOf(200));

        account = accountRepository.save(account);

        var transactions = transactionRepository.findAll();

        Assert.assertEquals(2, transactions.size());

        Assert.assertEquals(1, transactions.stream().filter(t -> t.getType() == TransactionType.CREDIT
                && t.getValue().equals(BigDecimal.valueOf(100))).count());
        Assert.assertEquals(1, transactions.stream().filter(t -> t.getType() == TransactionType.DEBIT
                && t.getValue().equals(BigDecimal.valueOf(200))).count());
    }
}