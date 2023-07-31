package com.shiproutes.routes.journey_year.application.decrement;

import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyMother;
import com.shiproutes.routes.journey.domain.JourneyRemovedEventMother;
import com.shiproutes.routes.journey_year.JourneysByYearModuleUnitTestCase;
import com.shiproutes.routes.journey_year.domain.JourneysByYear;
import com.shiproutes.routes.journey_year.domain.JourneysByYearMother;
import com.shiproutes.routes.shared.domain.JourneyRemovedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DecrementJourneysByYearOnJourneyRemovedShould extends JourneysByYearModuleUnitTestCase {

    DecrementJourneysByYearOnJourneyRemoved listener;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        listener = new DecrementJourneysByYearOnJourneyRemoved(
            new JourneysByYearDecrementer(repository, uuidGenerator, queryBus)
        );
    }

    @Test
    void decrement_journeys_of_existent_entity() {
        Journey journey = JourneyMother.random();
        JourneyRemovedEvent event = JourneyRemovedEventMother.from(journey);
        JourneysByYear journeysByYear = JourneysByYearMother.fromJourney(journey);
        JourneysByYear expectedJourneysByYearToSave = JourneysByYearMother.decrementing(journeysByYear, journey);

        shouldExists(journeysByYear);

        listener.on(event);

        shouldHaveSaved(expectedJourneysByYearToSave);
    }

    @Test
    void create_entity_and_decrement_journeys_when_entity_not_exists() {
        Journey journey = JourneyMother.random();
        JourneyRemovedEvent event = JourneyRemovedEventMother.from(journey);
        JourneysByYear journeysByYear = JourneysByYearMother.initialized(journey);
        JourneysByYear expectedJourneysByYearToSave = JourneysByYearMother.decrementing(journeysByYear, journey);

        shouldGenerateUuid(expectedJourneysByYearToSave.id().value());
        shouldExistRoutePath(expectedJourneysByYearToSave);

        listener.on(event);

        shouldHaveSaved(expectedJourneysByYearToSave);
    }
}
