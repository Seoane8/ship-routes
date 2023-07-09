package com.shiproutes.ports.port_event_year.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class YearMother {

    public static Year random() {
        return new Year(MotherCreator.random().number().numberBetween(2000, java.time.Year.now().getValue()));
    }
}
