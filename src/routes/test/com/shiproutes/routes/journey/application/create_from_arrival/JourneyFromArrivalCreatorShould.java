package com.shiproutes.routes.journey.application.create_from_arrival;

import com.shiproutes.routes.journey.JourneyModuleUnitTestCase;
import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JourneyFromArrivalCreatorShould extends JourneyModuleUnitTestCase {

    JourneyFromArrivalCreator creator;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        creator = new JourneyFromArrivalCreator(repository, uuidGenerator, queryBus);
    }

    @Test
    void create_an_arrival_journey_when_not_match_with_any_journey() {
        Journey arrival = JourneyMother.randomArrival();
        Journey incompleteUnmatch = JourneyMother.incompleteUnmatchOfArrival(arrival);
        Journey completeUnmatch = JourneyMother.completeUnmatchOfArrival(arrival);
        shouldExists(incompleteUnmatch);
        shouldExists(completeUnmatch);
        shouldGenerateUuid(arrival.id().value());

        creator.create(arrival.shipId(), arrival.destinationPort(), arrival.arrivalDate());

        shouldHaveSaved(arrival);
    }

    @Test
    void create_a_journey_when_match_with_a_journey_departure() {
        Journey journey = JourneyMother.random();
        Journey incompleteMatch = JourneyMother.incompleteMatchOfArrival(journey);
        shouldExists(incompleteMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHaveSaved(journey);
    }

    @Test
    void remove_departure_journey_when_created_journey_matches_it() {
        Journey journey = JourneyMother.random();
        Journey incompleteMatch = JourneyMother.incompleteMatchOfArrival(journey);
        shouldExists(incompleteMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHaveRemoved(incompleteMatch);
    }

    @Test
    void create_a_journey_when_match_with_other_journey() {
        Journey journey = JourneyMother.random();
        Journey completeMatch = JourneyMother.completeMatchOfArrival(journey);
        shouldExists(completeMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHaveSaved(journey);
    }

    @Test
    void remove_journey_when_created_journey_matches_it() {
        Journey journey = JourneyMother.random();
        Journey completeMatch = JourneyMother.completeMatchOfArrival(journey);
        shouldExists(completeMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHaveRemoved(completeMatch);
    }
}
