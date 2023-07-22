package com.shiproutes.routes.journey.domain;

import com.shiproutes.shared.domain.InstantValueObject;

import java.time.Instant;

public class ArrivalDate extends InstantValueObject {
    public ArrivalDate(Instant value) {
        super(value);
    }

    public static ArrivalDate empty() {
        return new ArrivalDate(null);
    }

    public boolean isEmpty() {
        return value() == null;
    }
}
