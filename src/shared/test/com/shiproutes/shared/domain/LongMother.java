package com.shiproutes.shared.domain;

public class LongMother {

    public static Long random() {
        return MotherCreator.random().number().numberBetween(0L, Long.MAX_VALUE);
    }
}
