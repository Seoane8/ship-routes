package com.shiproutes.backoffice.user.application.create;

import com.shiproutes.backoffice.user.domain.*;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.UserRole;

@Service
public final class UserCreator {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserCreator(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserId id, Username username, UserRawPassword rawPassword, UserEmail email) {
        ensureUserNotExist(username, email);

        UserPassword password = passwordEncoder.encode(rawPassword);
        User user = new User(id, username, password, email, UserRole.USER);

        repository.save(user);
    }

    private void ensureUserNotExist(Username username, UserEmail email) throws UserAlreadyExists {
        if (repository.exists(username, email)) {
            throw new UserAlreadyExists(username, email);
        }
    }
}
