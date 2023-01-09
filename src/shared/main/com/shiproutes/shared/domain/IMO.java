package com.shiproutes.shared.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public final class IMO implements Serializable {
    private final String value;

    public IMO(String value) throws IllegalArgumentException {
        ensureValidIMO(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IMO)) return false;
        IMO imo = (IMO) o;
        return Objects.equals(value, imo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void ensureValidIMO(String value) throws IllegalArgumentException {
        if (value.length() != 7) {
            throw new IllegalArgumentException("Invalid IMO length: " + value + ". IMO must be a 7-digit number");
        }
        Integer[] digits;
        try {
            digits = Arrays.stream(value.split("")).map(Integer::parseInt).toArray(Integer[]::new);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid IMO: " + value + ". IMO must be a 7-digit number");
        }
        int checkDigit = digits[6];
        int product = 7 * digits[0] + 6 * digits[1] + 5 * digits[2] + 4 * digits[3] + 3 * digits[4] + 2 * digits[5];
        if (checkDigit != product % 10) {
            throw new IllegalArgumentException("Invalid IMO: " + value);
        }
    }
}
