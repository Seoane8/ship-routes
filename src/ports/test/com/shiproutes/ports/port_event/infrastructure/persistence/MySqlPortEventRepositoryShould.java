package com.shiproutes.ports.port_event.infrastructure.persistence;

import com.shiproutes.ports.port_event.PortEventModuleInfrastructureTestCase;
import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventIdMother;
import com.shiproutes.ports.port_event.domain.PortEventMother;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
