package com.core.banking.business.dto;

public class EventResponse {

    private AccountBalance origin;

    private AccountBalance destination;

    public AccountBalance getOrigin() {
        return origin;
    }

    public void setOrigin(AccountBalance origin) {
        this.origin = origin;
    }

    public AccountBalance getDestination() {
        return destination;
    }

    public void setDestination(AccountBalance destination) {
        this.destination = destination;
    }
}
