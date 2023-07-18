package com.shiproutes.ports.port_event.infrastructure.persistence;

import com.shiproutes.ports.port_event.PortEventModuleInfrastructureTestCase;
import com.shiproutes.ports.port_event.domain.*;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class MySqlPortEventRepositoryShould extends PortEventModuleInfrastructureTestCase {

    @Test
    void save_a_port_event() {
        mySqlPortEventRepository.save(PortEventMother.random());
    }

    @Test
    void return_an_existent_port_event() {
        PortEvent portEvent = PortEventMother.random();
        mySqlPortEventRepository.save(portEvent);

        Optional<PortEvent> portEventFound = mySqlPortEventRepository.search(portEvent.id());
        assertEquals(Optional.of(portEvent), portEventFound);
    }

    @Test
    void not_return_a_non_existent_port_event() {
        assertEquals(Optional.empty(), mySqlPortEventRepository.search(PortEventIdMother.random()));
    }

    @Test
    void return_existent_ports_between_two_dates() {
        PortEventDate endDate = PortEventDateMother.now();
        PortEventDate startDate = PortEventDateMother.before(endDate);
        PortEvent expectedPortEvent = PortEventMother.randomWithDateBetween(startDate, endDate);
        PortEvent discardedPortEvent = PortEventMother.randomBefore(startDate);

        mySqlPortEventRepository.save(expectedPortEvent);
        mySqlPortEventRepository.save(discardedPortEvent);

        assertEquals(
            Set.of(expectedPortEvent),
            mySqlPortEventRepository.searchBetweenDates(startDate, endDate)
        );
    }
}
