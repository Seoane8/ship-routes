package com.shiproutes.ports.port_event_month;

import com.shiproutes.ports.PortsContextUnitTestCase;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonth;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class PortEventsByMonthModuleUnitTestCase extends PortsContextUnitTestCase {

    protected PortEventsByMonthRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(PortEventsByMonthRepository.class);
    }

    protected void shouldHaveSaved(PortEventsByMonth portEventsByMonth) {
        verify(repository, atLeastOnce()).save(portEventsByMonth);
    }

    protected void shouldExists(PortEventsByMonth portEventsByMonth) {
        when(repository.search(portEventsByMonth.portId(), portEventsByMonth.year(), portEventsByMonth.month()))
            .thenReturn(Optional.of(portEventsByMonth));
    }

    protected void shouldNotExist(PortEventsByMonth portEventsByMonth) {
        when(repository.search(portEventsByMonth.portId(), portEventsByMonth.year(), portEventsByMonth.month()))
            .thenReturn(Optional.empty());
    }

}
