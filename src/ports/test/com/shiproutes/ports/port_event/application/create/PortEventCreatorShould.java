package com.shiproutes.ports.port_event.application.create;

import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortMother;
import com.shiproutes.ports.port_event.PortEventModuleUnitTestCase;
import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventCreatedMother;
import com.shiproutes.ports.port_event.domain.PortEventMother;
import com.shiproutes.shared.domain.ports.PortEventCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PortEventCreatorShould extends PortEventModuleUnitTestCase {

    private PortEventCreator creator;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();

        this.creator = new PortEventCreator(repository, queryBus, eventBus);
    }

    @Test
    void create_new_port_event() {
        Port port = PortMother.random();
        shouldExistPort(port);
        PortEvent portEvent = PortEventMother.random(port);
        shouldExistShipWithTeus(portEvent.shipId(), portEvent.teus());

        creator.create(portEvent.id(), portEvent.type(), port.locode(), portEvent.shipId(), portEvent.date());

        shouldHaveSaved(portEvent);
    }

    @Test
    void publish_port_event_created_event() {
        Port port = PortMother.random();
        shouldExistPort(port);
        PortEvent portEvent = PortEventMother.random(port);
        PortEventCreated domainEvent = PortEventCreatedMother.fromPortEvent(portEvent);
        shouldExistShipWithTeus(portEvent.shipId(), portEvent.teus());

        creator.create(portEvent.id(), portEvent.type(), port.locode(), portEvent.shipId(), portEvent.date());

        shouldHavePublished(domainEvent);
    }
}
