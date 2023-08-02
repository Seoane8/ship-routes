package com.shiproutes.backoffice.user.domain;

public class UserIdMother {
    public static UserId random() {
        return new UserId(UserIdMother.random().value());
    }
}
