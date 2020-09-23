package com.core.banking.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("event")
public class EventController {

    @ResponseStatus(value = CREATED)
    @PostMapping(produces = "application/json")
    public void post() {

    }
}
