package com.shiproutes.ships.ship.application.record;

import com.shiproutes.ships.ship.ShipModuleUnitTestCase;
import com.shiproutes.ships.ship.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ShipRecorderShould extends ShipModuleUnitTestCase {

    private ShipRecorder recorder;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        recorder = new ShipRecorder(repository, eventBus);
    }

    @Test
    void record_an_unrecorded_ship() {
        Ship ship = ShipMother.random();

        recorder.record(ship.imo(), ship.name(), ship.teus());

        shouldHaveSaved(ship);
    }

    @Test
    void publish_ship_recorded_domain_event() {
        Ship ship = ShipMother.random();
        ShipRecorded domainEvent = ShipRecordedMother.fromShip(ship);

        recorder.record(ship.imo(), ship.name(), ship.teus());

        shouldHavePublished(domainEvent);
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
