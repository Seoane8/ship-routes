package com.shiproutes.shared.domain;

import java.time.Instant;
import java.util.Objects;

public abstract class InstantValueObject {

    protected final Instant value;

    public InstantValueObject(Instant value) {
        this.value = value;
    }

    public Instant value() {
        return value;
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
