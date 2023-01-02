package com.shiproutes.ports.port;

import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortRepository;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public abstract class PortModuleUnitTestCase extends UnitTestCase {

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
}
