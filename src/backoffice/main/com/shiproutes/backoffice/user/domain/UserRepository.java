package com.shiproutes.backoffice.user.domain;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> search(Username username);

    boolean exists(Username username, UserEmail email);

}
