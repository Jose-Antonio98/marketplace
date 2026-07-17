package com.jose.marketplace.ticketing.infrastructure.http.request;

import com.jose.marketplace.ticketing.domain.SeatId;

public record SeatSelectionRequest(String id) {
    public SeatId toInput() {
        return new SeatId(id);
    }
}