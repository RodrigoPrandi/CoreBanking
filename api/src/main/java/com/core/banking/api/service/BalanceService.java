package com.core.banking.api.service;

import com.core.banking.business.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceService {

    private final BankService bankService;

    @Autowired
    public BalanceService(BankService bankService) {
        this.bankService = bankService;
    }

    public BigDecimal getBalance(String accountId)
    {
        return bankService.getAccountBalance(accountId);
    }
}
