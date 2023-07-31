package com.shiproutes.routes.journey.domain;

import com.shiproutes.routes.shared.domain.JourneyRemovedEvent;

public class JourneyRemovedEventMother {

    public static JourneyRemovedEvent from(Journey journey) {
        return new JourneyRemovedEvent(
            journey.id().value(),
            journey.shipId().value(),
            journey.teus().value(),
            journey.originPort().value(),
            journey.destinationPort().value(),
            journey.departureDate().value(),
            journey.arrivalDate().value()
        );
    }

}
