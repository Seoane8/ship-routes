package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.UuidMother;

public class UserIdMother {
    public static UserId random() {
        return new UserId(UuidMother.random());
    }
}
