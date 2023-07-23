package com.shiproutes.routes.journey_month.domain;

import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.shared.domain.RoutePathMother;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.MonthMother;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.YearMother;
import com.shiproutes.shared.domain.ports.PortIdMother;

public class JourneysByMonthMother {

    public static JourneysByMonth random() {
        return new JourneysByMonth(
            JourneysByMonthIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random(),
            MonthMother.random(),
            YearMother.random(),
            JourneysCounterMother.random()
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
            JourneysCounterMother.random()
        );
    }

    public static JourneysByMonth incrementing(JourneysByMonth journeysByMonth) {
        return new JourneysByMonth(
            journeysByMonth.id(),
            journeysByMonth.originPort(),
            journeysByMonth.destinationPort(),
            journeysByMonth.path(),
            journeysByMonth.month(),
            journeysByMonth.year(),
            journeysByMonth.journeys().increment()
        );
    }

    public static JourneysByMonth firstFromJourney(Journey journey) {
        return new JourneysByMonth(
            JourneysByMonthIdMother.random(),
            journey.originPort(),
            journey.destinationPort(),
            journey.path(),
            Month.fromInstant(journey.departureDate().value()),
            Year.fromInstant(journey.departureDate().value()),
            new JourneysCounter(1L)
        );
    }
}
