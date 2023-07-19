package com.shiproutes.ports.port_event_year.infrastructure.persistence;

import com.shiproutes.ports.port_event_year.PortEventsByYearModuleInfrastructureTestCase;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearMother;
import com.shiproutes.ports.port_event_year.domain.YearMother;
import com.shiproutes.shared.domain.ports.PortIdMother;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class MySqlPortEventsByYearRepositoryShould extends PortEventsByYearModuleInfrastructureTestCase {

    @Test
    void save_a_port_event_by_year() {
        mySqlPortRepository.save(PortEventsByYearMother.random());
    }

    @Test
    void return_an_existent_port_event_by_year() {
        PortEventsByYear portEventsByYear = PortEventsByYearMother.random();
        mySqlPortRepository.save(portEventsByYear);

        assertEquals(
            Optional.of(portEventsByYear),
            mySqlPortRepository.search(portEventsByYear.portId(), portEventsByYear.year())
        );
    }

    @Test
    void not_return_a_non_existent_port_event_by_year() {
        assertEquals(
            Optional.empty(),
            mySqlPortRepository.search(PortIdMother.random(), YearMother.random())
        );
    }

    @Test
    void return_all_existent_port_event_by_year() {
        PortEventsByYear firstPortEventsByYear = PortEventsByYearMother.random();
        PortEventsByYear secondPortEventsByYear = PortEventsByYearMother.random();
        mySqlPortRepository.save(firstPortEventsByYear);
        mySqlPortRepository.save(secondPortEventsByYear);

        assertEquals(Set.of(firstPortEventsByYear, secondPortEventsByYear), mySqlPortRepository.searchAll());
    }

    @Test
    void return_empty_list_when_no_port_event_by_year_exist() {
        assertEquals(Collections.emptySet(), mySqlPortRepository.searchAll());
    }
}
