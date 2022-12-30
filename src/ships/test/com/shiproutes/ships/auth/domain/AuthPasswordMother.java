package com.shiproutes.ships.auth.domain;

import com.shiproutes.shared.domain.WordMother;

public final class AuthPasswordMother {
    public static AuthPassword create(String value) {
        return new AuthPassword(value);
    }

    public static AuthPassword random() {
        return create(WordMother.random());
    }
}
