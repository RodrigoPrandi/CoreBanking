package com.core.banking.business.service.transaction.deposit;

import com.core.banking.business.model.Account;
import com.core.banking.business.service.transaction.EventContext;

import java.math.BigDecimal;
import java.util.Optional;

import static com.core.banking.business.model.TransactionType.CREDIT;

public class DepositEventContext implements EventContext {

    private final Account destinationAccount;
    private final BigDecimal value;

    public DepositEventContext(Account destinationAccount, BigDecimal value) {
        this.destinationAccount = destinationAccount;
        this.value = value;
    }

    @Override
    public Optional<Account> getOriginAccount() {
        return Optional.empty();
    }

    @Override
    public Optional<Account> getDestinationAccount() {
        return Optional.of(destinationAccount);
    }

    @Override
    public void applyTransactions() {
        destinationAccount.addTransaction(CREDIT, value);
    }
}