package com.shiproutes.routes.journey.infrastructure.persistence;

import com.shiproutes.routes.journey.JourneyModuleInfrastructureTestCase;
import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyIdMother;
import com.shiproutes.routes.journey.domain.JourneyMother;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MySqlJourneyRepositoryShould extends JourneyModuleInfrastructureTestCase {

    @Test
    void save_new_journey() {
        Journey journey = JourneyMother.random();

        mySqlJourneyRepository.save(journey);
    }

    @Test
    void return_an_existing_journey() {
        Journey journey = JourneyMother.random();
        mySqlJourneyRepository.save(journey);

        assertEquals(Optional.of(journey), mySqlJourneyRepository.search(journey.id()));
    }

    @Test
    void not_return_a_non_existent_journey() {
        assertEquals(Optional.empty(), mySqlJourneyRepository.search(JourneyIdMother.random()));
    }
}
