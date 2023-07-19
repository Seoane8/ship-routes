package com.shiproutes.ports.port;

import com.shiproutes.ports.PortsContextUnitTestCase;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortRepository;
import com.shiproutes.shared.domain.ports.PortId;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class PortModuleUnitTestCase extends PortsContextUnitTestCase {

    protected PortRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(PortRepository.class);
    }

    protected void shouldHaveSaved(Port port) {
        verify(repository, atLeastOnce()).save(port);
    }

    protected void shouldExists(Port port) {
        when(repository.search(port.id())).thenReturn(Optional.of(port));
    }

    protected void shouldNotExist(PortId portId) {
        when(repository.search(portId)).thenReturn(Optional.empty());
    }
}
