package com.shiproutes.ports.port_event_month.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class MonthMother {
    public static Month random() {
        return new Month(MotherCreator.random().number().numberBetween(1, 12));
    }
}
