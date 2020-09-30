package com.core.banking.business.service.transaction.withdraw;

import com.core.banking.business.dto.Event;
import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.service.transaction.EventContextCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithdrawContextCreator extends EventContextCreator<WithdrawEventContext> {

    @Autowired
    public WithdrawContextCreator(AccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    public WithdrawEventContext createEventContext(Event event) {
        return new WithdrawEventContext(
                getAccountOrElseThrow(event.getOrigin()),
                event.getAmount()
        );
    }
}