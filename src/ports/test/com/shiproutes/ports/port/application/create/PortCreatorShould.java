package com.shiproutes.ports.port.application.create;

import com.shiproutes.ports.port.PortModuleUnitTestCase;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortCreatedEventMother;
import com.shiproutes.ports.port.domain.PortMother;
import com.shiproutes.shared.domain.ports.PortCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PortCreatorShould extends PortModuleUnitTestCase {

    private PortCreator creator;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        creator = new PortCreator(repository, eventBus);
    }

    @Test
    void create_new_port() {
        Port port = PortMother.randomNew();

        creator.create(port.id(), port.name(), port.locode(), port.coordinates());

        shouldHaveSaved(port);
    }

    @Test
    void publish_port_created_event() {
        Port port = PortMother.randomNew();
        PortCreatedEvent domainEvent = PortCreatedEventMother.fromPort(port);

        creator.create(port.id(), port.name(), port.locode(), port.coordinates());

        shouldHavePublished(domainEvent);
    }

    @Test
    void throw_exception_when_port_already_exists() {
        assertThrows(Exception.class, () -> {
            Port port = PortMother.randomNew();

            shouldExists(port);

            creator.create(port.id(), port.name(), port.locode(), port.coordinates());
        });
    }
}
