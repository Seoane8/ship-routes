package com.shiproutes.ports.port_event.application.create;

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
        PortEvent portEvent = PortEventMother.random();
        shouldExistPortWithCoordinates(portEvent.portId(), portEvent.coordinates());
        shouldExistShipWithTeus(portEvent.shipId(), portEvent.teus());

        creator.create(portEvent.id(), portEvent.type(), portEvent.portId(), portEvent.shipId(), portEvent.date());

        shouldHaveSaved(portEvent);
    }

    @Test
    void publish_port_event_created_event() {
        PortEvent portEvent = PortEventMother.random();
        PortEventCreated domainEvent = PortEventCreatedMother.fromPortEvent(portEvent);
        shouldExistPortWithCoordinates(portEvent.portId(), portEvent.coordinates());
        shouldExistShipWithTeus(portEvent.shipId(), portEvent.teus());

        creator.create(portEvent.id(), portEvent.type(), portEvent.portId(), portEvent.shipId(), portEvent.date());

        shouldHavePublished(domainEvent);
    }
}
