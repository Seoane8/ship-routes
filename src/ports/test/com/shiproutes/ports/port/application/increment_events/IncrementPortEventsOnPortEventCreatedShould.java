package com.shiproutes.ports.port.application.increment_events;

import com.shiproutes.ports.port.PortModuleUnitTestCase;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortMother;
import com.shiproutes.ports.port.domain.PortNotExist;
import com.shiproutes.ports.port_event.domain.PortEventCreated;
import com.shiproutes.ports.port_event.domain.PortEventCreatedMother;
import com.shiproutes.shared.domain.PortId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IncrementPortEventsOnPortEventCreatedShould extends PortModuleUnitTestCase {

    IncrementPortEventsOnPortEventCreated subscriber;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        subscriber = new IncrementPortEventsOnPortEventCreated(
            new PortEventsIncrementer(repository)
        );
    }

    @Test
    void increment_events_of_existent_port() {
        PortEventCreated event = PortEventCreatedMother.random();

        PortId portId = new PortId(event.portId());
        Port port = PortMother.fromId(portId);
        Port expectedPortToSave = PortMother.incrementingEvents(port);
        shouldExists(port);

        subscriber.on(event);

        shouldHaveSaved(expectedPortToSave);
    }

    @Test
    void throw_exception_when_port_not_exist() {
        assertThrows(PortNotExist.class, () -> {
            PortEventCreated event = PortEventCreatedMother.random();

            PortId portId = new PortId(event.portId());
            shouldNotExist(portId);

            subscriber.on(event);
        });
    }

}
