package com.shiproutes.ships.ship.application.find;

import com.shiproutes.shared.domain.IMOMother;
import com.shiproutes.ships.ship.ShipModuleUnitTestCase;
import com.shiproutes.ships.ship.domain.Ship;
import com.shiproutes.ships.ship.domain.ShipMother;
import com.shiproutes.ships.ship.domain.ShipNotExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShipFinderShould extends ShipModuleUnitTestCase {

    private ShipFinder finder;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        this.finder = new ShipFinder(this.repository);
    }

    @Test
    void return_an_existent_ship() {
        Ship ship = ShipMother.random();

        shouldExists(ship);

        assertEquals(ShipResponse.from(ship), finder.find(ship.imo()));
    }

    @Test
    void throw_an_exception_when_ship_not_exists() {
        assertThrows(ShipNotExists.class, () -> finder.find(IMOMother.random()));
    }
}
