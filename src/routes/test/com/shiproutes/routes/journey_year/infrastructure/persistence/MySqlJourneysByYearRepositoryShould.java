package com.shiproutes.routes.journey_year.infrastructure.persistence;

import com.shiproutes.routes.journey_year.JourneysByYearModuleInfrastructureTestCase;
import com.shiproutes.routes.journey_year.domain.JourneysByYear;
import com.shiproutes.routes.journey_year.domain.JourneysByYearMother;
import com.shiproutes.shared.domain.YearMother;
import com.shiproutes.shared.domain.ports.PortIdMother;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySqlJourneysByYearRepositoryShould extends JourneysByYearModuleInfrastructureTestCase {

    @Test
    void save_a_journey_by_year() {
        JourneysByYear journeysByYear = JourneysByYearMother.random();

        mySqlJourneysByYearRepository.save(journeysByYear);
    }

    @Test
    void return_an_existent_journey_by_year() {
        JourneysByYear journeysByYear = JourneysByYearMother.random();
        mySqlJourneysByYearRepository.save(journeysByYear);

        assertEquals(
            Optional.of(journeysByYear),
            mySqlJourneysByYearRepository.search(
                journeysByYear.originPort(),
                journeysByYear.destinationPort(),
                journeysByYear.year()
            )
        );
    }

    @Test
    void not_return_a_non_existent_journey_by_year() {
        assertEquals(
            Optional.empty(),
            mySqlJourneysByYearRepository.search(
                PortIdMother.random(),
                PortIdMother.random(),
                YearMother.random()
            )
        );
    }

    @Test
    void return_all_existent_journeys_by_year() {
        JourneysByYear firstEntity = JourneysByYearMother.random();
        JourneysByYear secondEntity = JourneysByYearMother.random();
        mySqlJourneysByYearRepository.save(firstEntity);
        mySqlJourneysByYearRepository.save(secondEntity);

        assertEquals(Set.of(firstEntity, secondEntity), mySqlJourneysByYearRepository.searchAll());
    }

    @Test
    void return_empty_list_when_no_journeys_by_year_exist() {
        assertEquals(Collections.emptySet(), mySqlJourneysByYearRepository.searchAll());
    }
}
