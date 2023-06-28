package com.shiproutes.ports.departure;

import com.shiproutes.ports.PortsContextUnitTestCase;
import com.shiproutes.ports.departure.domain.Departure;
import com.shiproutes.ports.departure.domain.DepartureRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public abstract class DepartureModuleUnitTestCase extends PortsContextUnitTestCase {

    protected DepartureRepository repository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        repository = mock(DepartureRepository.class);
    }

    protected final void shouldHaveSaved(Departure departure) {
        verify(repository, atLeastOnce()).save(departure);
    }
}
