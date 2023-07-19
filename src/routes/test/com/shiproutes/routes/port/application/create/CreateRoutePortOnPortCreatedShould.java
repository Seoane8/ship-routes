package com.shiproutes.routes.port.application.create;

import com.shiproutes.routes.port.PortModuleUnitTestCase;
import com.shiproutes.routes.port.domain.RoutePort;
import com.shiproutes.routes.port.domain.RoutePortMother;
import com.shiproutes.shared.domain.ports.PortCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateRoutePortOnPortCreatedShould extends PortModuleUnitTestCase {

    CreateRoutePortOnPortCreated listener;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        listener = new CreateRoutePortOnPortCreated(new RoutePortCreator(repository));
    }

    @Test
    void save_port_if_not_exists() {
        RoutePort port = RoutePortMother.random();
        PortCreatedEvent event = eventFromPort(port);

        shouldNotExists(port);

        listener.on(event);

        shouldHaveSaved(port);
    }

    @Test
    void do_nothing_if_port_already_exists() {
        RoutePort port = RoutePortMother.random();
        PortCreatedEvent event = eventFromPort(port);

        shouldExists(port);

        listener.on(event);

        shouldNotHaveSaved(port);
    }

    private PortCreatedEvent eventFromPort(RoutePort port) {
        return new PortCreatedEvent(
            port.id().value(),
            "",
            "",
            port.coordinates().latitude().value(),
            port.coordinates().longitude().value()
        );
    }
}
