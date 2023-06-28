package com.shiproutes.ports.departure.domain;

import com.shiproutes.ports.shared.domain.CoordinatesMother;
import com.shiproutes.ports.shared.domain.PortIdMother;
import com.shiproutes.shared.domain.IMOMother;
import com.shiproutes.shared.domain.TeusMother;

public final class DepartureMother {

    public static Departure random() {
        return new Departure(
            DepartureIdMother.random(),
            PortIdMother.random(),
            CoordinatesMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            DepartureDateMother.random()
        );
    }
}
