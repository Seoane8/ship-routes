package com.shiproutes.routes.journey.domain;

public class JourneyRemovedEventMother {

    public static JourneyRemovedEvent from(Journey journey) {
        return new JourneyRemovedEvent(
            journey.id().value(),
            journey.shipId().value(),
            journey.originPort().value(),
            journey.destinationPort().value(),
            journey.departureDate().value(),
            journey.arrivalDate().value()
        );
    }

}
