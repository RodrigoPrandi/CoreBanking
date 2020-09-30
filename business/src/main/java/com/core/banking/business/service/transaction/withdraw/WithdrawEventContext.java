package com.core.banking.business.service.transaction.withdraw;

import com.core.banking.business.model.Account;
import com.core.banking.business.service.transaction.EventContext;

import java.math.BigDecimal;
import java.util.Optional;

import static com.core.banking.business.model.TransactionType.DEBIT;

public class WithdrawEventContext implements EventContext {

    private final Account originAccount;
    private final BigDecimal value;

    public WithdrawEventContext(Account originAccount, BigDecimal value) {
        this.originAccount = originAccount;
        this.value = value;
    }

    @Override
    public Optional<Account> getOriginAccount() {
        return Optional.of(originAccount);
    }

    @Override
    public Optional<Account> getDestinationAccount() {
        return Optional.empty();
    }

    @Override
    public void applyTransactions() {
        originAccount.addTransaction(DEBIT, value);
    }
}