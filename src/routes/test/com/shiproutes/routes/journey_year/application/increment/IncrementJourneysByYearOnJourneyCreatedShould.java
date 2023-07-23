package com.shiproutes.routes.journey_year.application.increment;

import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyCreatedEventMother;
import com.shiproutes.routes.journey.domain.JourneyMother;
import com.shiproutes.routes.journey_year.JourneysByYearModuleUnitTestCase;
import com.shiproutes.routes.journey_year.domain.JourneysByYear;
import com.shiproutes.routes.journey_year.domain.JourneysByYearMother;
import com.shiproutes.routes.shared.domain.JourneyCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncrementJourneysByYearOnJourneyCreatedShould extends JourneysByYearModuleUnitTestCase {

    IncrementJourneysByYearOnJourneyCreated listener;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        listener = new IncrementJourneysByYearOnJourneyCreated(
            new JourneysByYearIncrementer(repository, uuidGenerator, queryBus)
        );
    }

    @Test
    void increment_journeys_of_existent_entity() {
        Journey journey = JourneyMother.random();
        JourneyCreatedEvent event = JourneyCreatedEventMother.from(journey);
        JourneysByYear journeysByYear = JourneysByYearMother.fromJourney(journey);
        JourneysByYear expectedJourneysByYearToSave = JourneysByYearMother.incrementing(journeysByYear);

        shouldExists(journeysByYear);

        listener.on(event);

        shouldHaveSaved(expectedJourneysByYearToSave);
    }

    @Test
    void create_entity_and_increment_journeys_when_entity_not_exist() {
        Journey journey = JourneyMother.random();
        JourneyCreatedEvent event = JourneyCreatedEventMother.from(journey);
        JourneysByYear journeysByYear = JourneysByYearMother.initialized(journey);
        JourneysByYear expectedJourneysByYearToSave = JourneysByYearMother.incrementing(journeysByYear);

        shouldGenerateUuid(expectedJourneysByYearToSave.id().value());
        shouldExistRoutePath(expectedJourneysByYearToSave);

        listener.on(event);

        shouldHaveSaved(expectedJourneysByYearToSave);
    }
}
