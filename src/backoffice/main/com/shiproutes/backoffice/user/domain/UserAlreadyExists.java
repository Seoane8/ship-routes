package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.DomainError;

public class UserAlreadyExists extends DomainError {
    public UserAlreadyExists(Username username, UserEmail email) {
        super("user_already_exists",
            String.format("The user <%s> with email <%s> already exists", username.value(), email.value()));
    }
}
