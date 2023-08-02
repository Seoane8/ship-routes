package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.UserRole;

import java.util.Objects;

public final class User {

    private final UserId id;
    private final Username username;
    private final UserPassword password;
    private final UserEmail email;
    private final UserRole role;

    public User(UserId id, Username username, UserPassword password, UserEmail email, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserId id() {
        return id;
    }

    public Username username() {
        return username;
    }

    public UserPassword password() {
        return password;
    }

    public UserEmail email() {
        return email;
    }

    public UserRole role() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username)
            && Objects.equals(password, user.password) && Objects.equals(email, user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, role);
    }
}
