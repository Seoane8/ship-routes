package com.shiproutes.ports.departure.domain;

import com.shiproutes.shared.domain.InstantValueObject;

import java.time.Instant;

public final class DepartureDate extends InstantValueObject {

    public DepartureDate(Instant value) {
        super(value);
    }
}
