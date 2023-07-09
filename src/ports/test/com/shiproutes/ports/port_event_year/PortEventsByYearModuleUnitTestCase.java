package com.shiproutes.ports.port_event_year;

import com.shiproutes.ports.PortsContextUnitTestCase;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class PortEventsByYearModuleUnitTestCase extends PortsContextUnitTestCase {

    protected PortEventsByYearRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(PortEventsByYearRepository.class);
    }

    protected void shouldHaveSaved(PortEventsByYear portEventsByYear) {
        verify(repository, atLeastOnce()).save(portEventsByYear);
    }

    protected void shouldExists(PortEventsByYear portEventsByYear) {
        when(repository.search(portEventsByYear.portId(), portEventsByYear.year()))
            .thenReturn(Optional.of(portEventsByYear));
    }

    protected void shouldNotExist(PortEventsByYear portEventsByYear) {
        when(repository.search(portEventsByYear.portId(), portEventsByYear.year()))
            .thenReturn(Optional.empty());
    }

}
