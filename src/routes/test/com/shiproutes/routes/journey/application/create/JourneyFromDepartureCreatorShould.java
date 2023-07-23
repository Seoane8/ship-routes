package com.shiproutes.routes.journey.application.create;

import com.shiproutes.routes.journey.JourneyModuleUnitTestCase;
import com.shiproutes.routes.journey.domain.*;
import com.shiproutes.routes.shared.domain.JourneyCreatedEvent;
import com.shiproutes.routes.shared.domain.JourneyRemovedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class JourneyFromDepartureCreatorShould extends JourneyModuleUnitTestCase {

    JourneyFromDepartureCreator creator;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        creator = new JourneyFromDepartureCreator(repository, uuidGenerator, queryBus, eventBus);
    }

    @Test
    void create_an_incomplete_journey_when_not_match_with_any_journey() {
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
    void create_a_journey_when_match_with_a_incomplete_journey() {
        Journey journey = JourneyMother.random();
        Journey incompleteMatch = JourneyMother.incompleteMatchOfDeparture(journey);
        shouldExists(incompleteMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHaveSaved(journey);
    }

    @Test
    void remove_incomplete_journey_when_created_journey_matches_it() {
        Journey journey = JourneyMother.random();
        Journey incompleteMatch = JourneyMother.incompleteMatchOfDeparture(journey);
        shouldExists(incompleteMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHaveRemoved(incompleteMatch);
    }

    @Test
    void publish_journey_created_when_match_with_incomplete_journey() {
        Journey journey = JourneyMother.random();
        JourneyCreatedEvent event = JourneyCreatedEventMother.from(journey);
        Journey match = JourneyMother.incompleteMatchOfDeparture(journey);
        shouldExists(match);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHavePublished(event);
    }

    @Test
    void not_publish_journey_removed_and_unlinked_when_match_with_incomplete_journey() {
        Journey journey = JourneyMother.random();
        Journey match = JourneyMother.incompleteMatchOfDeparture(journey);
        JourneyRemovedEvent removedEvent = JourneyRemovedEventMother.from(match);
        JourneyUnlinkedEvent unlinkedEvent = JourneyUnlinkedEventMother.fromDeparture(match);
        shouldExists(match);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldNotHavePublished(List.of(removedEvent, unlinkedEvent));
    }

    @Test
    void not_publish_journey_unlinked_when_match_with_incomplete_journey() {

    }

    @Test
    void create_a_journey_when_match_with_complete_journey() {
        Journey journey = JourneyMother.random();
        Journey completeMatch = JourneyMother.completeMatchOfDeparture(journey);
        shouldExists(completeMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHaveSaved(journey);
    }

    @Test
    void remove_complete_journey_when_created_journey_matches_it() {
        Journey journey = JourneyMother.random();
        Journey completeMatch = JourneyMother.completeMatchOfDeparture(journey);
        shouldExists(completeMatch);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHaveRemoved(completeMatch);
    }

    @Test
    void publish_journey_created_when_match_with_complete_journey() {
        Journey journey = JourneyMother.random();
        JourneyCreatedEvent event = JourneyCreatedEventMother.from(journey);
        Journey match = JourneyMother.completeMatchOfDeparture(journey);
        shouldExists(match);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHavePublished(event);
    }

    @Test
    void publish_journey_removed_and_unlinked_when_match_with_complete_journey() {
        Journey journey = JourneyMother.random();
        Journey match = JourneyMother.completeMatchOfDeparture(journey);
        JourneyRemovedEvent removedEvent = JourneyRemovedEventMother.from(match);
        JourneyUnlinkedEvent unlinkedEvent = JourneyUnlinkedEventMother.fromDeparture(match);
        shouldExists(match);
        shouldGenerateUuid(journey.id().value());
        shouldExistRoutePath(journey);

        creator.create(journey.shipId(), journey.originPort(), journey.departureDate());

        shouldHavePublished(List.of(removedEvent, unlinkedEvent));
    }

}
