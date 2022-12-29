package com.shiproutes.ship.auth.domain;

import com.shiproutes.shared.domain.WordMother;

public final class AuthUsernameMother {
    public static AuthUsername create(String value) {
        return new AuthUsername(value);
    }

    public static AuthUsername random() {
        return create(WordMother.random());
    }
}
