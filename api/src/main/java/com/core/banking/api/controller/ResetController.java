package com.core.banking.api.controller;

import com.core.banking.business.repository.AccountRepository;
import com.core.banking.business.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("reset")
public class ResetController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public ResetController(AccountRepository accountRepository,
                           TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @ResponseStatus(value = OK)
    @PostMapping(produces = "application/json")
    public String resetApi() {
        clearDatabase();
        return "OK";
    }

    private void clearDatabase() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
