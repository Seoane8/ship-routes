package com.shiproutes.ships.ship;

import com.shiproutes.shared.infrastructure.UnitTestCase;
import com.shiproutes.ships.ship.domain.Ship;
import com.shiproutes.ships.ship.domain.ShipRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class ShipModuleUnitTestCase extends UnitTestCase {

    protected ShipRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(ShipRepository.class);
    }

    protected void shouldHaveSaved(Ship ship) {
        verify(repository, atLeastOnce()).save(ship);
    }

    protected void shouldExists(Ship ship) {
        when(repository.search(ship.imo())).thenReturn(Optional.of(ship));
    }
}
