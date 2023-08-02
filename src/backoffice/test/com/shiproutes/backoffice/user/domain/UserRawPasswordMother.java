package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class UserRawPasswordMother {

    public static UserRawPassword random() {
        return new UserRawPassword(MotherCreator.random().internet().password(8, 20));
    }

}
