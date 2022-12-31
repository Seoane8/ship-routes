package com.shiproutes.ships.ship.domain;

import java.util.Objects;

public final class Teus {
    private final Integer value;

    public Teus(Integer value) {
        ensureIsGreaterThenZero(value);
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teus)) return false;
        Teus teus = (Teus) o;
        return Objects.equals(value, teus.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void ensureIsGreaterThenZero(Integer value) {
        if (value < 0) throw new IllegalArgumentException("Teus must be positive");
    }
}
