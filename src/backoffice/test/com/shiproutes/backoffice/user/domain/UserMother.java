package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.UserRole;

public class UserMother {

    public static User random() {
        return new User(
            UserIdMother.random(),
            UsernameMother.random(),
            UserPasswordMother.random(),
            UserEmailMother.random(),
            UserRoleMother.random()
        );
    }

    public static User randomUser() {
        return new User(
            UserIdMother.random(),
            UsernameMother.random(),
            UserPasswordMother.random(),
            UserEmailMother.random(),
            UserRole.USER
        );
    }
}
