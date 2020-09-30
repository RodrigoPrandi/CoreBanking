package com.core.banking.business.service.transaction;

import com.core.banking.business.model.Account;

import java.util.Optional;

public interface EventContext {

    Optional<Account> getOriginAccount();
    Optional<Account> getDestinationAccount();
    void applyTransactions();
}