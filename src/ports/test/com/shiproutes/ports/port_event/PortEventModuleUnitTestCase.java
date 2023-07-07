package com.shiproutes.ports.port_event;

import com.shiproutes.ports.PortsContextUnitTestCase;
import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public abstract class PortEventModuleUnitTestCase extends PortsContextUnitTestCase {

    protected PortEventRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(PortEventRepository.class);
    }

    protected void shouldHaveSaved(PortEvent portEvent) {
        verify(repository, atLeastOnce()).save(portEvent);
    }

}
