package com.shiproutes.routes.journey_month.application.increment;

import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyCreatedEventMother;
import com.shiproutes.routes.journey.domain.JourneyMother;
import com.shiproutes.routes.journey_month.JourneysByMonthModuleUnitTestCase;
import com.shiproutes.routes.journey_month.domain.JourneysByMonth;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthMother;
import com.shiproutes.routes.shared.domain.JourneyCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncrementJourneysByMonthOnJourneyCreatedShould extends JourneysByMonthModuleUnitTestCase {

    IncrementJourneysByMonthOnJourneyCreated listener;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        listener = new IncrementJourneysByMonthOnJourneyCreated(
            new JourneysByMonthIncrementer(repository, uuidGenerator, queryBus)
        );
    }

    @Test
    void increment_journeys_of_existent_entity() {
        Journey journey = JourneyMother.random();
        JourneyCreatedEvent event = JourneyCreatedEventMother.from(journey);
        JourneysByMonth journeysByMonth = JourneysByMonthMother.fromJourney(journey);
        JourneysByMonth expectedJourneysByMonthToSave = JourneysByMonthMother.incrementing(journeysByMonth);

        shouldExists(journeysByMonth);

        listener.on(event);

        shouldHaveSaved(expectedJourneysByMonthToSave);
    }

    @Test
    void create_entity_and_increment_journeys_when_entity_not_exist() {
        Journey journey = JourneyMother.random();
        JourneyCreatedEvent event = JourneyCreatedEventMother.from(journey);
        JourneysByMonth journeysByMonth = JourneysByMonthMother.initialized(journey);
        JourneysByMonth expectedJourneysByMonthToSave = JourneysByMonthMother.incrementing(journeysByMonth);

        shouldGenerateUuid(expectedJourneysByMonthToSave.id().value());
        shouldExistRoutePath(expectedJourneysByMonthToSave);

        listener.on(event);

        shouldHaveSaved(expectedJourneysByMonthToSave);
    }
}
