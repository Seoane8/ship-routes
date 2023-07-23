package com.shiproutes.routes.journey.domain;

import com.shiproutes.shared.domain.InstantValueObject;

import java.time.Instant;

public class DepartureDate extends InstantValueObject {
    public DepartureDate(Instant value) {
        super(value);
    }

    public static DepartureDate empty() {
        return new DepartureDate(null);
    }

    public boolean isEmpty() {
        return value() == null;
    }

    public DepartureDate startOfDay() {
        return new DepartureDate(super.startOfDayInstant());
    }

    public DepartureDate endOfDay() {
        return new DepartureDate(super.endOfDayInstant());
    }
}
