package com.shiproutes.backoffice.user.application.authenticate;

import com.shiproutes.backoffice.user.domain.*;
import com.shiproutes.shared.domain.Service;

@Service
public class UserAuthenticator {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public UserAuthenticator(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public UserResponse authenticate(Username username, UserRawPassword password) throws InvalidCredentials {
        User user = repository.search(username).orElseThrow(() -> new InvalidCredentials(username));

        if (!encoder.matches(password, user.password())) {
            throw new InvalidCredentials(username);
        }

        return UserResponse.from(user);
    }
}
