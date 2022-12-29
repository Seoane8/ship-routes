package com.shiproutes.ship.auth.application.authenticate;

import com.shiproutes.ship.auth.domain.AuthPassword;
import com.shiproutes.ship.auth.domain.AuthPasswordMother;
import com.shiproutes.ship.auth.domain.AuthUsername;
import com.shiproutes.ship.auth.domain.AuthUsernameMother;

public final class AuthenticateUserCommandMother {
    public static AuthenticateUserCommand create(AuthUsername username, AuthPassword password) {
        return new AuthenticateUserCommand(username.value(), password.value());
    }

    public static AuthenticateUserCommand random() {
        return create(AuthUsernameMother.random(), AuthPasswordMother.random());
    }
}
