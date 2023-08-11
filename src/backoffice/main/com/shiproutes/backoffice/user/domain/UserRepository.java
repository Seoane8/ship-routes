package com.shiproutes.backoffice.user.domain;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    void save(User user);

    Optional<User> search(Username username);

    boolean exists(Username username, UserEmail email);

    Set<User> search(String partialUsername);

    Optional<User> search(UserId userId);
}
