package com.shiproutes.routes.journey.application.create_from_departure;

import com.shiproutes.routes.journey.JourneyModuleUnitTestCase;
import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JourneyFromDepartureCreatorShould extends JourneyModuleUnitTestCase {

    JourneyFromDepartureCreator creator;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        creator = new JourneyFromDepartureCreator(repository, uuidGenerator, queryBus);
    }

    @Test
    void create_a_departure_journey_when_not_match_with_any_journey() {
        Journey departure = JourneyMother.randomDeparture();
        Journey incompleteUnmatch = JourneyMother.incompleteUnmatchOfDeparture(departure);
        Journey completeUnmatch = JourneyMother.completeUnmatchOfDeparture(departure);
        shouldExists(incompleteUnmatch);
        shouldExists(completeUnmatch);
        shouldGenerateUuid(departure.id().value());

        creator.create(departure.shipId(), departure.originPort(), departure.departureDate());

        shouldHaveSaved(departure);
    }

    @Test
    void create_a_journey_when_match_with_a_journey_arrival() {
        Journey journey = JourneyMother.random();
        Journey incompleteMatch = JourneyMother.incompleteMatchOfDeparture(journey);
        shouldExists(incompleteMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHaveSaved(journey);
    }

    @Test
    void remove_arrival_journey_when_created_journey_matches_it() {
        Journey journey = JourneyMother.random();
        Journey incompleteMatch = JourneyMother.incompleteMatchOfDeparture(journey);
        shouldExists(incompleteMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHaveRemoved(incompleteMatch);
    }

    @Test
    void create_a_journey_when_match_with_other_journey() {
        Journey journey = JourneyMother.random();
        Journey completeMatch = JourneyMother.completeMatchOfDeparture(journey);
        shouldExists(completeMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHaveSaved(journey);
    }

    @Test
    void remove_journey_when_created_journey_matches_it() {
        Journey journey = JourneyMother.random();
        Journey completeMatch = JourneyMother.completeMatchOfDeparture(journey);
        shouldExists(completeMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHaveRemoved(completeMatch);
    }
}
