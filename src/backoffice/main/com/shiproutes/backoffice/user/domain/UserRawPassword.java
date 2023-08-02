package com.shiproutes.backoffice.user.domain;

import java.util.Objects;

public final class UserRawPassword {

    private final String value;

    public UserRawPassword(String value) {
        ensureRawPasswordLength(value);
        this.value = value;
    }

    private void ensureRawPasswordLength(String value) {
        if (value.length() < 8) {
            throw new IllegalArgumentException("The password must hace at least 8 characters");
        }
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRawPassword)) return false;
        UserRawPassword that = (UserRawPassword) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
