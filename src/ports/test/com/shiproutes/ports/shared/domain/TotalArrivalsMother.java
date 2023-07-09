package com.shiproutes.ports.shared.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class TotalArrivalsMother {

    public static TotalArrivals random() {
        return new TotalArrivals(MotherCreator.random().number().numberBetween(0L, Long.MAX_VALUE));
    }

}
