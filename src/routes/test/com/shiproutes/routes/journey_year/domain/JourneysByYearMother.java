package com.shiproutes.routes.journey_year.domain;

import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.JourneysCounterMother;
import com.shiproutes.routes.shared.domain.RoutePathMother;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.YearMother;
import com.shiproutes.shared.domain.ports.PortIdMother;

public class JourneysByYearMother {

    public static JourneysByYear random() {
        return new JourneysByYear(
            JourneysByYearIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random(),
            YearMother.random(),
            JourneysCounterMother.random()
        );
    }

    public static JourneysByYear initialized(Journey journey) {
        return new JourneysByYear(
            JourneysByYearIdMother.random(),
            journey.originPort(),
            journey.destinationPort(),
            journey.path(),
            Year.fromInstant(journey.departureDate().value()),
            JourneysCounter.initialize()
        );
    }

    public static JourneysByYear fromJourney(Journey journey) {
        return new JourneysByYear(
            JourneysByYearIdMother.random(),
            journey.originPort(),
            journey.destinationPort(),
            journey.path(),
            Year.fromInstant(journey.departureDate().value()),
            JourneysCounterMother.random()
        );
    }

    public static JourneysByYear incrementing(JourneysByYear journeysByYear) {
        return new JourneysByYear(
            journeysByYear.id(),
            journeysByYear.originPort(),
            journeysByYear.destinationPort(),
            journeysByYear.path(),
            journeysByYear.year(),
            journeysByYear.journeys().increment()
        );
    }

    public static JourneysByYear decrementing(JourneysByYear journeysByYear) {
        return new JourneysByYear(
            journeysByYear.id(),
            journeysByYear.originPort(),
            journeysByYear.destinationPort(),
            journeysByYear.path(),
            journeysByYear.year(),
            journeysByYear.journeys().decrement()
        );
    }
}
