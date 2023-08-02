package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class UserEmailMother {
    public static UserEmail random() {
        return new UserEmail(MotherCreator.random().internet().emailAddress());
    }
}
