package com.shiproutes.shared.domain;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class InstantMother {

    public static Instant random() {
        return MotherCreator.random().date().past(2000, TimeUnit.DAYS).toInstant();
    }

    public static Instant now() {
        return Instant.now();
    }

    public static Instant randomBefore(Instant value) {
        return MotherCreator.random().date().past(2000, TimeUnit.DAYS, Date.from(value)).toInstant();
    }

    public static Instant randomBetween(Instant startDate, Instant endDate) {
        return MotherCreator.random().date().between(Date.from(startDate), Date.from(endDate)).toInstant();
    }

    public static Instant randomAfter(Instant value) {
        return MotherCreator.random().date().future(2000, TimeUnit.DAYS, Date.from(value)).toInstant();
    }
}
