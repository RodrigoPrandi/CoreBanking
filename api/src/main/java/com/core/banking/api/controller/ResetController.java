package com.core.banking.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("reset")
public class ResetController {

    @ResponseStatus(value = OK)
    @PostMapping(produces = "application/json")
    public String resetApi() {
        return "OK";
    }
}
