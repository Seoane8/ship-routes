package com.shiproutes.ports.port.application.increment_events;

import com.shiproutes.ports.port.PortModuleUnitTestCase;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortMother;
import com.shiproutes.ports.port.domain.PortNotExist;
import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventCreatedMother;
import com.shiproutes.ports.port_event.domain.PortEventMother;
import com.shiproutes.shared.domain.ports.PortEventCreated;
import com.shiproutes.shared.domain.ports.PortId;
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
    void incrementing_departures_of_existent_port() {
        PortEvent portEvent = PortEventMother.randomDeparture();
        PortEventCreated domainEvent = PortEventCreatedMother.fromPortEvent(portEvent);

        Port port = PortMother.fromId(portEvent.portId());
        Port expectedPortToSave = PortMother.incrementingDepartures(port);
        shouldExists(port);

        subscriber.on(domainEvent);

        shouldHaveSaved(expectedPortToSave);
    }

    @Test
    void increment_arrivals_of_existent_port() {
        PortEvent portEvent = PortEventMother.randomArrival();
        PortEventCreated domainEvent = PortEventCreatedMother.fromPortEvent(portEvent);

        Port port = PortMother.fromId(portEvent.portId());
        Port expectedPortToSave = PortMother.incrementingArrivals(port);
        shouldExists(port);

        subscriber.on(domainEvent);

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
