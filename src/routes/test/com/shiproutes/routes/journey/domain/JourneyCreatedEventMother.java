package com.shiproutes.routes.journey.domain;

public class JourneyCreatedEventMother {

    public static JourneyCreatedEvent from(Journey journey) {
        return new JourneyCreatedEvent(
            journey.id().value(),
            journey.shipId().value(),
            journey.originPort().value(),
            journey.destinationPort().value(),
            journey.departureDate().value(),
            journey.arrivalDate().value()
        );
    }

}
