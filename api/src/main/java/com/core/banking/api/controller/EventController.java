package com.core.banking.api.controller;

import com.core.banking.api.service.EventService;
import com.core.banking.business.dto.Event;
import com.core.banking.business.dto.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ResponseStatus(value = CREATED)
    @PostMapping(produces = "application/json")
    public EventResponse post(@RequestBody Event eventCommand) {
        return eventService.registerEvent(eventCommand);
    }
}
