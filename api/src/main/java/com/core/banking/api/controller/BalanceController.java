package com.core.banking.api.controller;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("balance")
public class BalanceController {

    @ResponseStatus(value = OK)
    @GetMapping(produces = "application/json")
    public Long getAccountBalance(@RequestParam String account_id) {
        return 0L;
    }
}
