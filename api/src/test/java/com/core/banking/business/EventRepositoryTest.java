package com.core.banking.business;

import com.core.banking.business.model.Account;
import com.core.banking.business.model.Transaction;
import com.core.banking.business.model.TransactionType;
import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.repository.TransactionRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Before;
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

    @Before
    public void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    public void findAllByAccount() {
        var externalId = RandomString.make();
        var account = new Account();
        account.setExternalId(externalId);
        var transaction = new Transaction();
        transaction.setType(TransactionType.CREDIT);
        transaction.setValue(BigDecimal.valueOf(100));

        var transaction2 = new Transaction();
        transaction2.setType(TransactionType.DEBIT);
        transaction2.setValue(BigDecimal.valueOf(200));

        account.addTransaction(transaction);
        account.addTransaction(transaction2);

        account = accountRepository.save(account);

        var transactions = transactionRepository.findAll();

        Assert.assertEquals(2, transactions.size());

        Assert.assertEquals(1, transactions.stream().filter(t -> t.getType() == TransactionType.CREDIT
                && t.getValue().equals(BigDecimal.valueOf(100))).count());
        Assert.assertEquals(1, transactions.stream().filter(t -> t.getType() == TransactionType.DEBIT
                && t.getValue().equals(BigDecimal.valueOf(200))).count());
    }
}