package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.MotherCreator;
import com.shiproutes.shared.domain.UserRole;

public class UserRoleMother {
    public static UserRole random() {
        return MotherCreator.random().options().option(UserRole.class);
    }
}
