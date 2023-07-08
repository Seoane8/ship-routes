package com.shiproutes.ports.year_port_event.domain;

import java.util.Objects;

public class Year {

    private final java.time.Year value;

    public Year(Integer value) {
        this.value = java.time.Year.of(value);
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
