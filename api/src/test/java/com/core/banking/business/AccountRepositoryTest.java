package com.core.banking.business;


import com.core.banking.business.model.Account;
import com.core.banking.business.repository.AccountRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    public void testFindFistByExternalId() throws Exception {

        var externalId = RandomString.make();
        var account = new Account(externalId);

        account = accountRepository.save(account);

        var accountResponse = accountRepository.findFirstByExternalId(externalId).orElseThrow();

        Assert.assertEquals(account.getId(), accountResponse.getId());
        Assert.assertEquals(externalId, accountResponse.getExternalId());
    }
}