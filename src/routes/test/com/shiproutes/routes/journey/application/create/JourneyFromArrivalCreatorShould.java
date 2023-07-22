package com.shiproutes.routes.journey.application.create;

import com.shiproutes.routes.journey.JourneyModuleUnitTestCase;
import com.shiproutes.routes.journey.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class JourneyFromArrivalCreatorShould extends JourneyModuleUnitTestCase {

    JourneyFromArrivalCreator creator;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        creator = new JourneyFromArrivalCreator(repository, uuidGenerator, queryBus, eventBus);
    }

    @Test
    void create_an_incomplete_journey_when_not_match_with_any_journey() {
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
    void create_a_journey_when_match_with_a_incomplete_journey() {
        Journey journey = JourneyMother.random();
        Journey incompleteMatch = JourneyMother.incompleteMatchOfArrival(journey);
        shouldExists(incompleteMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHaveSaved(journey);
    }

    @Test
    void remove_incomplete_journey_when_created_journey_matches_it() {
        Journey journey = JourneyMother.random();
        Journey incompleteMatch = JourneyMother.incompleteMatchOfArrival(journey);
        shouldExists(incompleteMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHaveRemoved(incompleteMatch);
    }

    @Test
    void publish_journey_created_when_match_with_incomplete_journey() {
        Journey journey = JourneyMother.random();
        JourneyCreatedEvent event = JourneyCreatedEventMother.from(journey);
        Journey match = JourneyMother.incompleteMatchOfArrival(journey);
        shouldExists(match);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHavePublished(event);
    }

    @Test
    void not_publish_journey_removed_and_unlinked_when_match_with_incomplete_journey() {
        Journey journey = JourneyMother.random();
        Journey match = JourneyMother.incompleteMatchOfArrival(journey);
        JourneyRemovedEvent removedEvent = JourneyRemovedEventMother.from(match);
        JourneyUnlinkedEvent unlinkedEvent = JourneyUnlinkedEventMother.fromArrival(match);
        shouldExists(match);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldNotHavePublished(List.of(removedEvent, unlinkedEvent));
    }

    @Test
    void create_a_journey_when_match_with_complete_journey() {
        Journey journey = JourneyMother.random();
        Journey completeMatch = JourneyMother.completeMatchOfArrival(journey);
        shouldExists(completeMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHaveSaved(journey);
    }

    @Test
    void remove_complete_journey_when_created_journey_matches_it() {
        Journey journey = JourneyMother.random();
        Journey completeMatch = JourneyMother.completeMatchOfArrival(journey);
        shouldExists(completeMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHaveRemoved(completeMatch);
    }

    @Test
    void publish_journey_created_when_match_with_complete_journey() {
        Journey journey = JourneyMother.random();
        JourneyCreatedEvent event = JourneyCreatedEventMother.from(journey);
        Journey match = JourneyMother.completeMatchOfArrival(journey);
        shouldExists(match);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHavePublished(event);
    }

    @Test
    void publish_journey_removed_and_unlinked_when_match_with_complete_journey() {
        Journey journey = JourneyMother.random();
        Journey match = JourneyMother.completeMatchOfArrival(journey);
        JourneyRemovedEvent removedEvent = JourneyRemovedEventMother.from(match);
        JourneyUnlinkedEvent unlinkedEvent = JourneyUnlinkedEventMother.fromArrival(match);
        shouldExists(match);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.destinationPort(), journey.arrivalDate());

        shouldHavePublished(List.of(removedEvent, unlinkedEvent));
    }

}
