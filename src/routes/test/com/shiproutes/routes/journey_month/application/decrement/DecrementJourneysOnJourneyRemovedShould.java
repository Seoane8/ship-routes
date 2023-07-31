package com.shiproutes.routes.journey_month.application.decrement;

import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyMother;
import com.shiproutes.routes.journey.domain.JourneyRemovedEventMother;
import com.shiproutes.routes.journey_month.JourneysByMonthModuleUnitTestCase;
import com.shiproutes.routes.journey_month.domain.JourneysByMonth;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthMother;
import com.shiproutes.routes.shared.domain.JourneyRemovedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DecrementJourneysOnJourneyRemovedShould extends JourneysByMonthModuleUnitTestCase {

    DecrementJourneysByMonthOnJourneyRemoved listener;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        listener = new DecrementJourneysByMonthOnJourneyRemoved(
            new JourneysByMonthDecrementer(repository, uuidGenerator, queryBus)
        );
    }

    @Test
    void decrement_journeys_of_existent_entity() {
        Journey journey = JourneyMother.random();
        JourneyRemovedEvent event = JourneyRemovedEventMother.from(journey);
        JourneysByMonth journeysByMonth = JourneysByMonthMother.fromJourney(journey);
        JourneysByMonth expectedJourneysByMonthToSave = JourneysByMonthMother.decrementing(journeysByMonth, journey);

        shouldExists(journeysByMonth);

        listener.on(event);

        shouldHaveSaved(expectedJourneysByMonthToSave);
    }

    @Test
    void create_entity_and_decrement_journeys_when_entity_not_exists() {
        Journey journey = JourneyMother.random();
        JourneyRemovedEvent event = JourneyRemovedEventMother.from(journey);
        JourneysByMonth journeysByMonth = JourneysByMonthMother.initialized(journey);
        JourneysByMonth expectedJourneysByMonthToSave = JourneysByMonthMother.decrementing(journeysByMonth, journey);

        shouldGenerateUuid(expectedJourneysByMonthToSave.id().value());
        shouldExistRoutePath(expectedJourneysByMonthToSave);

        listener.on(event);

        shouldHaveSaved(expectedJourneysByMonthToSave);
    }
}
