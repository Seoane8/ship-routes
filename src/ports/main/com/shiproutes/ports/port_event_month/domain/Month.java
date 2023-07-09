package com.shiproutes.ports.port_event_month.domain;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Month {
    private final java.time.Month value;

    public Month(Integer value) {
        this.value = java.time.Month.of(value);
    }

    public static Month fromInstant(Instant instant) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(instant));
        return new Month(cal.get(Calendar.MONTH));
    }

    public Integer value() {
        return value.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Month)) return false;
        Month month1 = (Month) o;
        return value == month1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
