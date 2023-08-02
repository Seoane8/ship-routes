package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class UserPasswordMother {
    public static UserPassword random() {
        return new UserPassword(MotherCreator.random().internet().password());
    }
}
