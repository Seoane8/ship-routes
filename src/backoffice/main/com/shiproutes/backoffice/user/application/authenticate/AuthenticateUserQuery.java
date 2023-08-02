package com.shiproutes.backoffice.user.application.authenticate;

import com.shiproutes.shared.domain.bus.query.Query;

import java.util.Objects;

public class AuthenticateUserQuery implements Query {

    private final String username;
    private final String password;

    public AuthenticateUserQuery(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticateUserQuery)) return false;
        AuthenticateUserQuery that = (AuthenticateUserQuery) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
