package com.shiproutes.routes.journey_month.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class JourneysCounterMother {

    public static JourneysCounter random() {
        return new JourneysCounter(MotherCreator.random().number().numberBetween(0L, Long.MAX_VALUE));
    }
}
