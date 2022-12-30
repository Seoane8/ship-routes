package com.shiproutes.ships.auth.application.authenticate;

import com.shiproutes.ships.auth.domain.AuthPassword;
import com.shiproutes.ships.auth.domain.AuthPasswordMother;
import com.shiproutes.ships.auth.domain.AuthUsername;
import com.shiproutes.ships.auth.domain.AuthUsernameMother;

public final class AuthenticateUserCommandMother {
    public static AuthenticateUserCommand create(AuthUsername username, AuthPassword password) {
        return new AuthenticateUserCommand(username.value(), password.value());
    }

    public static AuthenticateUserCommand random() {
        return create(AuthUsernameMother.random(), AuthPasswordMother.random());
    }
}
