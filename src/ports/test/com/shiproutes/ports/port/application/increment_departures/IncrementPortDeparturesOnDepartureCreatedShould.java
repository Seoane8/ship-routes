package com.shiproutes.ports.port.application.increment_departures;

import com.shiproutes.ports.departure.domain.DepartureCreated;
import com.shiproutes.ports.departure.domain.DepartureCreatedMother;
import com.shiproutes.ports.port.PortModuleUnitTestCase;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortMother;
import com.shiproutes.ports.port.domain.PortNotExist;
import com.shiproutes.shared.domain.PortId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IncrementPortDeparturesOnDepartureCreatedShould extends PortModuleUnitTestCase {

    IncrementPortDeparturesOnDepartureCreated subscriber;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        subscriber = new IncrementPortDeparturesOnDepartureCreated(
            new PortDeparturesIncrementer(repository)
        );
    }

    @Test
    void increment_departures_of_existent_port() {
        DepartureCreated event = DepartureCreatedMother.random();

        PortId portId = new PortId(event.portId());
        Port port = PortMother.fromId(portId);
        Port expectedPortToSave = PortMother.incrementingDepartures(port);
        shouldExists(port);

        subscriber.on(event);

        shouldHaveSaved(expectedPortToSave);
    }

    @Test
    void throw_exception_when_port_not_exist() {
        assertThrows(PortNotExist.class, () -> {
            DepartureCreated event = DepartureCreatedMother.random();

            PortId portId = new PortId(event.portId());
            shouldNotExist(portId);

            subscriber.on(event);
        });
    }

}
