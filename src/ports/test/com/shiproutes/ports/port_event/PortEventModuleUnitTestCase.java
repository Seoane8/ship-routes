package com.shiproutes.ports.port_event;

import com.shiproutes.ports.PortsContextUnitTestCase;
import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventRepository;
import com.shiproutes.ports.port_event.domain.ShipNotExist;
import com.shiproutes.ports.shared.application.FindTeusQuery;
import com.shiproutes.shared.domain.IMO;
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

    protected void shouldNotExistShip(IMO shipId) {
        shouldFailOnAsk(new FindTeusQuery(shipId.value()), new ShipNotExist(shipId));
    }

}
