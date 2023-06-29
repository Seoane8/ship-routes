package com.shiproutes.ports.departure.domain;

import com.shiproutes.ports.shared.domain.PortIdMother;
import com.shiproutes.shared.domain.IMOMother;

public final class DepartureCreatedMother {

    public static DepartureCreated fromDeparture(Departure departure) {
        return new DepartureCreated(
            departure.id().value(),
            departure.portId().value(),
            departure.shipId().value(),
            departure.date().value()
        );
    }

    public static DepartureCreated random() {
        return new DepartureCreated(
            DepartureIdMother.random().value(),
            PortIdMother.random().value(),
            IMOMother.random().value(),
            DepartureDateMother.random().value()
        );
    }
}
