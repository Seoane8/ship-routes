package com.shiproutes.ports.arrival.domain;

import com.shiproutes.shared.domain.InstantValueObject;

import java.time.Instant;

public final class ArrivalDate extends InstantValueObject {

    public ArrivalDate(Instant value) {
        super(value);
    }
}
