package com.shiproutes.shared.domain;

public class YearMother {

    public static Year random() {
        return new Year(MotherCreator.random().number().numberBetween(2000, java.time.Year.now().getValue()));
    }
}
