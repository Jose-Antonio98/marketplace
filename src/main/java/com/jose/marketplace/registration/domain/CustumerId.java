package com.jose.marketplace.registration.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record CustumerId(UUID id) {
    public CustumerId {
        Assert.notNull(id, "id must not be null");
    }

    public CustumerId(){
        this(UUID.randomUUID());
    }
}
