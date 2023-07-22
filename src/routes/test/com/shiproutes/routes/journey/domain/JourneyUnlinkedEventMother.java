package com.shiproutes.routes.journey.domain;

public class JourneyUnlinkedEventMother {

    public static JourneyUnlinkedEvent fromDeparture(Journey journey) {
        return new JourneyUnlinkedEvent(
            journey.id().value(),
            journey.shipId().value(),
            journey.originPort().value(),
            journey.departureDate().value(),
            "DEPARTURE"
        );
    }

    public static JourneyUnlinkedEvent fromArrival(Journey journey) {
        return new JourneyUnlinkedEvent(
            journey.id().value(),
            journey.shipId().value(),
            journey.destinationPort().value(),
            journey.arrivalDate().value(),
            "ARRIVAL"
        );
    }
}
