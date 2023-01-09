package com.shiproutes.ports.arrival.domain;

public final class ArrivalCreatedMother {

    public static ArrivalCreated fromArrival(Arrival arrival) {
        return new ArrivalCreated(
            arrival.id().value(),
            arrival.portId().value(),
            arrival.shipId().value(),
            arrival.date().value()
        );
    }

}
