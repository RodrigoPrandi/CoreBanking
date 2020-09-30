package com.core.banking.api.controller;

import com.core.banking.api.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("balance")
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @ResponseStatus(value = OK)
    @GetMapping(produces = "application/json")
    public BigDecimal getAccountBalance(@RequestParam String account_id) {
        return balanceService.getBalance(account_id);
    }
}
