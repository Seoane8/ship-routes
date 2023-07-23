package com.shiproutes.shared.domain;

public class MonthMother {
    public static Month random() {
        return new Month(MotherCreator.random().number().numberBetween(1, 12));
    }
}
