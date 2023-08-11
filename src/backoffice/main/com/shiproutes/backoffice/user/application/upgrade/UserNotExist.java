package com.shiproutes.backoffice.user.application.upgrade;

import com.shiproutes.backoffice.user.domain.UserId;
import com.shiproutes.shared.domain.DomainError;

public class UserNotExist extends DomainError {
    public UserNotExist(UserId userId) {
        super("user_not_exist", String.format("The user <%s> doesn't exist", userId.value()));
    }
}
