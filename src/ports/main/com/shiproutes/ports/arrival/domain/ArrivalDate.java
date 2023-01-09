package com.shiproutes.ports.arrival.domain;

import java.time.Instant;
import java.util.Objects;

public final class ArrivalDate {

    private final Instant value;

    public ArrivalDate(Instant value) {
        this.value = value;
    }

    public Instant value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrivalDate)) return false;
        ArrivalDate that = (ArrivalDate) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
