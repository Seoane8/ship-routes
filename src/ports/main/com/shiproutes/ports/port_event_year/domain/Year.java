package com.shiproutes.ports.port_event_year.domain;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Year {

    private final java.time.Year value;

    public Year(Integer value) {
        this.value = java.time.Year.of(value);
    }

    public static Year fromInstant(Instant instant) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(instant));
        return new Year(cal.get(Calendar.YEAR));
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
