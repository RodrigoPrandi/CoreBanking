package com.core.banking.api.service;

import com.core.banking.business.dto.Event;
import com.core.banking.business.dto.EventResponse;
import com.core.banking.business.service.BankService;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final BankService bankService;

    public EventService(BankService bankService) {
        this.bankService = bankService;
    }

    public EventResponse registerEvent(Event event) {

        return bankService.processEvent(event);
    }
}
