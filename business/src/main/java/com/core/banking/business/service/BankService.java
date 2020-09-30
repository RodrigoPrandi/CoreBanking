package com.core.banking.business.service;

import com.core.banking.business.dto.AccountBalance;
import com.core.banking.business.dto.Event;
import com.core.banking.business.dto.EventResponse;
import com.core.banking.business.exceptions.AccountNotFoundException;
import com.core.banking.business.model.Account;
import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.service.transaction.EventContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BankService {

    private final AccountRepository accountRepository;
    private final EventContextFactory eventContextFactory;

    @Autowired
    public BankService(AccountRepository accountRepository, EventContextFactory eventContextFactory) {
        this.accountRepository = accountRepository;
        this.eventContextFactory = eventContextFactory;
    }

    public BigDecimal getAccountBalance(String accountId) {
        var account = findAccount(accountId);
        return account.getBalance();
    }

    public EventResponse processEvent(Event event) {

        var eventContext = eventContextFactory.createEventContext(event);

        eventContext.applyTransactions();

        var response = new EventResponse();

        eventContext.getOriginAccount()
                .ifPresent(account ->{

                    accountRepository.save(account);

                    var balanceOrigin =new AccountBalance();
                    balanceOrigin.setId(account.getExternalId());
                    balanceOrigin.setBalance(account.getBalance());

                    response.setOrigin(balanceOrigin);
                });


        eventContext.getDestinationAccount()
                .ifPresent(account ->{

                    accountRepository.save(account);

                    var balanceOrigin =new AccountBalance();
                    balanceOrigin.setId(account.getExternalId());
                    balanceOrigin.setBalance(account.getBalance());

                    response.setDestination(balanceOrigin);
                });

        return response;
    }

    private Account findAccount(String accountId) {
        var optionalAccount = accountRepository.findFirstByExternalId(accountId);
        return optionalAccount.orElseThrow(AccountNotFoundException::new);
    }
}
