package com.core.banking.business.service.transaction;

import com.core.banking.business.dto.Event;
import com.core.banking.business.exceptions.AccountNotFoundException;
import com.core.banking.business.model.Account;
import com.core.banking.business.repository.AccountRepository;

public abstract class EventContextCreator<T> {
    private final AccountRepository accountRepository;

    protected EventContextCreator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public abstract T createEventContext(Event event);


    protected Account getAccountOrElseThrow(String accountId)
    {
        var optionalOriginAccount = accountRepository.findFirstByExternalId(accountId);
        return optionalOriginAccount.orElseThrow(AccountNotFoundException::new);
    }

    protected Account getAccountOrElseNew(String accountId)
    {
        var optionalOriginAccount = accountRepository.findFirstByExternalId(accountId);
        return optionalOriginAccount.orElse(new Account(accountId));
    }
}
