package com.shiproutes.backoffice.user.application.authenticate;

import com.shiproutes.backoffice.user.domain.User;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Objects;

public class UserResponse implements Response {

    private final String id;
    private final String username;
    private final String password;
    private final String email;
    private final String role;

    public UserResponse(String id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.id().value(), user.username().value(),
            user.password().value(), user.email().value(), user.role().name());
    }

    public String id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String email() {
        return email;
    }

    public String role() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponse)) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username)
            && Objects.equals(password, that.password) && Objects.equals(email, that.email)
            && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, role);
    }
}
