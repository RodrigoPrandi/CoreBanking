package com.core.banking.business.service.transaction;

import com.core.banking.business.dto.Event;
import com.core.banking.business.exceptions.EventTyeNotFoundException;
import com.core.banking.business.service.transaction.deposit.DepositContextCreator;
import com.core.banking.business.service.transaction.transfer.TransferContextCreator;
import com.core.banking.business.service.transaction.withdraw.WithdrawContextCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventContextFactory {

    private final DepositContextCreator depositContextCreator;
    private final TransferContextCreator transferContextCreator;
    private final WithdrawContextCreator withdrawContextCreator;

    @Autowired
    public EventContextFactory(DepositContextCreator depositContextCreator,
                               TransferContextCreator transferContextCreator,
                               WithdrawContextCreator withdrawContextCreator) {
        this.depositContextCreator = depositContextCreator;
        this.transferContextCreator = transferContextCreator;
        this.withdrawContextCreator = withdrawContextCreator;
    }

    public EventContext createEventContext(Event event){
        switch (event.getType()) {
            case DEPOSIT:
                return depositContextCreator.createEventContext(event);
            case TRANSFER:
                return transferContextCreator.createEventContext(event);
            case WITHDRAW:
                return withdrawContextCreator.createEventContext(event);
            default:
                throw new EventTyeNotFoundException();
        }
    }
}