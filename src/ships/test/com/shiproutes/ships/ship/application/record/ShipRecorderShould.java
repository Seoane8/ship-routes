package com.shiproutes.ships.ship.application.record;

import com.shiproutes.ships.ship.ShipModuleUnitTestCase;
import com.shiproutes.ships.ship.domain.Ship;
import com.shiproutes.ships.ship.domain.ShipAlreadyRecorded;
import com.shiproutes.ships.ship.domain.ShipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ShipRecorderShould extends ShipModuleUnitTestCase {

    private ShipRecorder recorder;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        recorder = new ShipRecorder(repository);
    }

    @Test
    void record_an_unrecorded_ship() {
        Ship ship = ShipMother.random();

        recorder.record(ship.imo(), ship.name(), ship.teus());

        shouldHaveSaved(ship);
    }

    @Test
    void throw_an_exception_when_the_ship_is_already_recorded() {
        assertThrows(ShipAlreadyRecorded.class, () -> {
            Ship ship = ShipMother.random();

            shouldExists(ship);

            recorder.record(ship.imo(), ship.name(), ship.teus());
        });
    }

}
