package com.shiproutes.backoffice.user.application.search;

import com.shiproutes.backoffice.user.application.authenticate.UserResponse;
import com.shiproutes.backoffice.user.domain.User;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersResponse implements Response {

    private final Set<UserResponse> users;

    public UsersResponse(Collection<UserResponse> users) {
        this.users = new HashSet<>(users);
    }

    public static UsersResponse from(Collection<User> users) {
        return users.stream().map(UserResponse::from)
            .collect(Collectors.collectingAndThen(Collectors.toSet(), UsersResponse::new));
    }

    public Set<UserResponse> users() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersResponse)) return false;
        UsersResponse that = (UsersResponse) o;
        return Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }
}
