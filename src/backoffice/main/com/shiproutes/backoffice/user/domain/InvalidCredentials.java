package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.DomainError;

public class InvalidCredentials extends DomainError {
    public InvalidCredentials(Username username) {
        super("invalid_credentials",
            String.format("The credentials for user <%s> are invalid", username.value()));
    }
}
