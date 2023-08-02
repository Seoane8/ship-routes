package com.shiproutes.backoffice.user.domain;

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
}
