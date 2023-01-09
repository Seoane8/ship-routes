package com.shiproutes.ports.departure.domain;

import com.shiproutes.ports.shared.domain.PortIdMother;
import com.shiproutes.shared.domain.IMOMother;

public final class DepartureMother {

    public static Departure random() {
        return new Departure(
            DepartureIdMother.random(),
            PortIdMother.random(),
            IMOMother.random(),
            DepartureDateMother.random()
        );
    }
}
