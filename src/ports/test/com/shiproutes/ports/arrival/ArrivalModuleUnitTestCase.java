package com.shiproutes.ports.arrival;

import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalRepository;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public abstract class ArrivalModuleUnitTestCase extends UnitTestCase {

    protected ArrivalRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(ArrivalRepository.class);
    }

    protected void shouldHaveSaved(Arrival arrival) {
        verify(repository, atLeastOnce()).save(arrival);
    }

}
