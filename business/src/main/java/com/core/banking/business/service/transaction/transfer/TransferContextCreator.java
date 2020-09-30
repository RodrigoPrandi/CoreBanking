package com.core.banking.business.service.transaction.transfer;

import com.core.banking.business.dto.Event;
import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.service.transaction.EventContextCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferContextCreator extends EventContextCreator<TransferEventContext> {

    @Autowired
    public TransferContextCreator(AccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    public TransferEventContext createEventContext(Event event) {
        return new TransferEventContext(
                getAccountOrElseThrow(event.getOrigin()),
                getAccountOrElseNew(event.getDestination()),
                event.getAmount()
        );
    }
}
