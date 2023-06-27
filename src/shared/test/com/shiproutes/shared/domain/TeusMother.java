package com.shiproutes.shared.domain;

public final class TeusMother {

    public static Teus random() {
        return new Teus(MotherCreator.random().number().numberBetween(0, Integer.MAX_VALUE));
    }
}
