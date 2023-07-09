package com.shiproutes.ports.port_event_month.infrastructure.persistence;

import com.shiproutes.ports.port_event_month.PortEventsByMonthModuleInfrastructureTestCase;
import com.shiproutes.ports.port_event_month.domain.MonthMother;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonth;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthMother;
import com.shiproutes.ports.port_event_year.domain.YearMother;
import com.shiproutes.ports.shared.domain.PortIdMother;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySqlPortEventsByMonthRepositoryShould extends PortEventsByMonthModuleInfrastructureTestCase {

    @Test
    void save_a_port_event_by_year() {
        mySqlPortRepository.save(PortEventsByMonthMother.random());
    }

    @Test
    void return_an_existent_port_event_by_year() {
        PortEventsByMonth portEventsByMonth = PortEventsByMonthMother.random();
        mySqlPortRepository.save(portEventsByMonth);

        assertEquals(
            Optional.of(portEventsByMonth),
            mySqlPortRepository.search(portEventsByMonth.portId(), portEventsByMonth.year(), portEventsByMonth.month())
        );
    }

    @Test
    void not_return_a_non_existent_port_event_by_year() {
        assertEquals(
            Optional.empty(),
            mySqlPortRepository.search(PortIdMother.random(), YearMother.random(), MonthMother.random())
        );
    }

    @Test
    void return_all_existent_port_event_by_year() {
        PortEventsByMonth firstPortEventsByMonth = PortEventsByMonthMother.random();
        PortEventsByMonth secondPortEventsByMonth = PortEventsByMonthMother.random();
        mySqlPortRepository.save(firstPortEventsByMonth);
        mySqlPortRepository.save(secondPortEventsByMonth);

        assertEquals(Set.of(firstPortEventsByMonth, secondPortEventsByMonth), mySqlPortRepository.searchAll());
    }

    @Test
    void return_empty_list_when_no_port_event_by_year_exist() {
        assertEquals(Collections.emptySet(), mySqlPortRepository.searchAll());
    }

}
