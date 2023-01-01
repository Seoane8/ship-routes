package com.shiproutes.ports.port.domain;

import java.util.Objects;

public final class Locode {
    private final String country;
    private final String code;

    public Locode(String locode) {
        ensureIsValidLocode(locode);
        this.country = locode.substring(0, 2);
        this.code = locode.substring(2);
    }

    public String value() {
        return country + code;
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Locode)) return false;
        Locode locode = (Locode) o;
        return Objects.equals(country, locode.country) && Objects.equals(code, locode.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, code);
    }

    private void ensureIsValidLocode(String locode) {
        if (locode.length() != 5) {
            throw new IllegalArgumentException("Invalid locode: " + locode);
        }
    }
}
