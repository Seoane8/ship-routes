package com.shiproutes.backoffice.user.domain;

import java.util.Objects;

public final class UserEmail {

    private static final String EMAIL_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private String value;

    public UserEmail(String value) {
        ensureIsValidEmail(value);
        this.value = value;
    }

    private void ensureIsValidEmail(String value) {
        if (!value.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException(String.format("The email <%s> is invalid", value));
        }
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEmail)) return false;
        UserEmail userEmail = (UserEmail) o;
        return Objects.equals(value, userEmail.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
