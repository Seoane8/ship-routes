package com.shiproutes.routes.journey_month.domain;

import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.JourneysCounterMother;
import com.shiproutes.routes.shared.domain.RoutePathMother;
import com.shiproutes.shared.domain.*;
import com.shiproutes.shared.domain.ports.PortIdMother;
import com.shiproutes.shared.domain.ports.TeusCounter;

public class JourneysByMonthMother {

    public static JourneysByMonth random() {
        return new JourneysByMonth(
            JourneysByMonthIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random(),
            MonthMother.random(),
            YearMother.random(),
            JourneysCounterMother.random(),
            new TeusCounter(IntegerMother.random()));
    }

    public static JourneysByMonth initialized(Journey journey) {
        return new JourneysByMonth(
            JourneysByMonthIdMother.random(),
            journey.originPort(),
            journey.destinationPort(),
            journey.path(),
            Month.fromInstant(journey.departureDate().value()),
            Year.fromInstant(journey.departureDate().value()),
            JourneysCounter.initialize(),
            TeusCounter.initialize()
        );
    }

    public static JourneysByMonth fromJourney(Journey journey) {
        return new JourneysByMonth(
            JourneysByMonthIdMother.random(),
            journey.originPort(),
            journey.destinationPort(),
            journey.path(),
            Month.fromInstant(journey.departureDate().value()),
            Year.fromInstant(journey.departureDate().value()),
            JourneysCounterMother.random(),
            TeusCounter.initialize()
        );
    }

    public static JourneysByMonth incrementing(JourneysByMonth journeysByMonth, Journey journey) {
        return new JourneysByMonth(
            journeysByMonth.id(),
            journeysByMonth.originPort(),
            journeysByMonth.destinationPort(),
            journeysByMonth.path(),
            journeysByMonth.month(),
            journeysByMonth.year(),
            journeysByMonth.journeys().increment(),
            journeysByMonth.teus().increment(journey.teus())
        );
    }

    public static JourneysByMonth decrementing(JourneysByMonth journeysByMonth, Journey journey) {
        return new JourneysByMonth(
            journeysByMonth.id(),
            journeysByMonth.originPort(),
            journeysByMonth.destinationPort(),
            journeysByMonth.path(),
            journeysByMonth.month(),
            journeysByMonth.year(),
            journeysByMonth.journeys().decrement(),
            journeysByMonth.teus().decrement(journey.teus())
        );
    }
}
