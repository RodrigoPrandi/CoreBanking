package com.core.banking.business.service.transaction.deposit;

import com.core.banking.business.dto.Event;
import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.service.transaction.EventContextCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositContextCreator extends EventContextCreator<DepositEventContext> {

    @Autowired
    public DepositContextCreator(AccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    public DepositEventContext createEventContext(Event event) {
        return new DepositEventContext(
                getAccountOrElseNew(event.getDestination()),
                event.getAmount()
        );
    }
}