package com.shiproutes.ports.port_event_year.domain;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Objects;

public class Year {

    private final java.time.Year value;

    public Year(Integer value) {
        this.value = java.time.Year.of(value);
    }

    public static Year fromInstant(Instant instant) {
        return new Year(instant.atOffset(ZoneOffset.UTC).getYear());
    }

    public Integer value() {
        return value.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Year)) return false;
        Year that = (Year) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
