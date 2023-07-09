package com.shiproutes.ports.shared.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class TotalDeparturesMother {

    public static TotalDepartures random() {
        return new TotalDepartures(MotherCreator.random().number().numberBetween(0L, Long.MAX_VALUE));
    }

}
