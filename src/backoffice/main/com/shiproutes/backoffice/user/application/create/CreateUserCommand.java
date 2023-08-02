package com.shiproutes.backoffice.user.application.create;

import com.shiproutes.shared.domain.bus.command.Command;

import java.util.Objects;

public class CreateUserCommand implements Command {

    private final String id;
    private final String username;
    private final String password;
    private final String email;

    public CreateUserCommand(String id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserCommand)) return false;
        CreateUserCommand that = (CreateUserCommand) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username)
            && Objects.equals(password, that.password) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email);
    }
}
