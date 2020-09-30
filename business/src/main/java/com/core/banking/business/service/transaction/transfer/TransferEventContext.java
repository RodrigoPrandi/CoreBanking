package com.core.banking.business.service.transaction.transfer;

import com.core.banking.business.model.Account;
import com.core.banking.business.service.transaction.EventContext;

import java.math.BigDecimal;
import java.util.Optional;

import static com.core.banking.business.model.TransactionType.CREDIT;
import static com.core.banking.business.model.TransactionType.DEBIT;

public class TransferEventContext implements EventContext {

    private final Account originAccount;
    private final Account destinationAccount;
    private final BigDecimal value;

    public TransferEventContext(Account originAccount, Account destinationAccount, BigDecimal value) {
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.value = value;
    }

    @Override
    public Optional<Account> getOriginAccount() {
        return Optional.of(originAccount);
    }

    @Override
    public Optional<Account> getDestinationAccount() {
        return Optional.of(destinationAccount);
    }

    @Override
    public void applyTransactions() {
        originAccount.addTransaction(DEBIT, value);
        destinationAccount.addTransaction(CREDIT, value);
    }
}