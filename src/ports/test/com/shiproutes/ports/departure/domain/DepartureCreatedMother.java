package com.shiproutes.ports.departure.domain;

public final class DepartureCreatedMother {

    public static DepartureCreated fromDeparture(Departure departure) {
        return new DepartureCreated(
            departure.id().value(),
            departure.portId().value(),
            departure.shipId().value(),
            departure.date().value()
        );
    }
}
