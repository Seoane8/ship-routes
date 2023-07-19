package com.shiproutes.routes.port;

import com.shiproutes.routes.port.domain.RoutePort;
import com.shiproutes.routes.port.domain.RoutePortRepository;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class PortModuleUnitTestCase extends UnitTestCase {

    protected RoutePortRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(RoutePortRepository.class);
    }

    protected void shouldHaveSaved(RoutePort port) {
        verify(repository, atLeastOnce()).save(port);
    }

    protected void shouldNotHaveSaved(RoutePort port) {
        verify(repository, never()).save(port);
    }

    protected void shouldExists(RoutePort port) {
        when(repository.search(port.id())).thenReturn(Optional.of(port));
    }

    protected void shouldNotExists(RoutePort port) {
        when(repository.search(port.id())).thenReturn(Optional.empty());
    }
}
