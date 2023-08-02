package com.shiproutes.backoffice.user.application.authenticate;

import com.shiproutes.backoffice.user.domain.UserRawPassword;
import com.shiproutes.backoffice.user.domain.Username;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class AuthenticateUserQueryHandler implements QueryHandler<AuthenticateUserQuery, UserResponse> {

    private final UserAuthenticator authenticator;

    public AuthenticateUserQueryHandler(UserAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public UserResponse handle(AuthenticateUserQuery query) {
        return authenticator.authenticate(
            new Username(query.username()),
            new UserRawPassword(query.password())
        );
    }
}
