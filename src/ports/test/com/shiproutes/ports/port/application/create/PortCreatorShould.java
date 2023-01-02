package com.shiproutes.ports.port.application.create;

import com.shiproutes.ports.port.PortModuleUnitTestCase;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PortCreatorShould extends PortModuleUnitTestCase {

    private PortCreator creator;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        creator = new PortCreator(repository);
    }

    @Test
    void create_new_port() {
        Port port = PortMother.random();

        creator.create(port.id(), port.name(), port.locode(), port.coordinates());

        shouldHaveSaved(port);
    }

    @Test
    void throw_exception_when_port_already_exists() {
        assertThrows(Exception.class, () -> {
            Port port = PortMother.random();

            shouldExists(port);

            creator.create(port.id(), port.name(), port.locode(), port.coordinates());
        });
    }
}
