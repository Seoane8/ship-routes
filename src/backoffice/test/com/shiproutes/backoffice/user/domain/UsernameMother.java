package com.shiproutes.backoffice.user.domain;

import com.shiproutes.shared.domain.WordMother;

public class UsernameMother {
    public static Username random() {
        return new Username(WordMother.random());
    }
}
