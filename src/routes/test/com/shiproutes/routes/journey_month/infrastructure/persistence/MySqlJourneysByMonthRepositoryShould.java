package com.shiproutes.routes.journey_month.infrastructure.persistence;

import com.shiproutes.routes.journey_month.JourneysByMonthModuleInfrastructureTestCase;
import com.shiproutes.routes.journey_month.domain.JourneysByMonth;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthMother;
import com.shiproutes.shared.domain.MonthMother;
import com.shiproutes.shared.domain.YearMother;
import com.shiproutes.shared.domain.ports.PortIdMother;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySqlJourneysByMonthRepositoryShould extends JourneysByMonthModuleInfrastructureTestCase {

    @Test
    void save_a_journey_by_month() {
        JourneysByMonth journeysByMonth = JourneysByMonthMother.random();

        mySqlJourneysByMonthRepository.save(journeysByMonth);
    }

    @Test
    void return_an_existent_journey_by_month() {
        JourneysByMonth journeysByMonth = JourneysByMonthMother.random();
        mySqlJourneysByMonthRepository.save(journeysByMonth);

        assertEquals(
            Optional.of(journeysByMonth),
            mySqlJourneysByMonthRepository.search(
                journeysByMonth.originPort(),
                journeysByMonth.destinationPort(),
                journeysByMonth.month(),
                journeysByMonth.year()
            )
        );
    }

    @Test
    void not_return_a_non_existent_journey_by_month() {
        assertEquals(
            Optional.empty(),
            mySqlJourneysByMonthRepository.search(
                PortIdMother.random(),
                PortIdMother.random(),
                MonthMother.random(),
                YearMother.random()
            )
        );
    }
}
