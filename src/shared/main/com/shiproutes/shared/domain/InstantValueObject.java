package com.shiproutes.shared.domain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class InstantValueObject {

    protected final Instant value;

    public InstantValueObject(Instant value) {
        this.value = value;
    }

    public Instant value() {
        return value;
    }

    public boolean isAfter(InstantValueObject that) {
        return this.value.isAfter(that.value);
    }

    public boolean isBefore(InstantValueObject that) {
        return this.value.isBefore(that.value);
    }

    protected Instant startOfDayInstant() {
        return this.value.truncatedTo(ChronoUnit.DAYS);
    }

    protected Instant endOfDayInstant() {
        return this.value
            .truncatedTo(ChronoUnit.DAYS)
            .plus(1, ChronoUnit.DAYS)
            .minus(1, ChronoUnit.MILLIS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstantValueObject)) return false;
        InstantValueObject that = (InstantValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
