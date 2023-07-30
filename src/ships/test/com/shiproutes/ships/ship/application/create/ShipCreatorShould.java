package com.shiproutes.ships.ship.application.create;

import com.shiproutes.shared.domain.ShipCreatedEvent;
import com.shiproutes.ships.ship.ShipModuleUnitTestCase;
import com.shiproutes.ships.ship.domain.Ship;
import com.shiproutes.ships.ship.domain.ShipCreatedEventMother;
import com.shiproutes.ships.ship.domain.ShipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipCreatorShould extends ShipModuleUnitTestCase {

    private ShipCreator creator;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        creator = new ShipCreator(repository, eventBus);
    }

    @Test
    void create_new_ship() {
        Ship ship = ShipMother.random();

        creator.create(ship.imo(), ship.name(), ship.teus());

        shouldHaveSaved(ship);
    }

    @Test
    void publish_ship_created_domain_event() {
        Ship ship = ShipMother.random();
        ShipCreatedEvent domainEvent = ShipCreatedEventMother.fromShip(ship);

        creator.create(ship.imo(), ship.name(), ship.teus());

        shouldHavePublished(domainEvent);
    }

    @Test
    void do_nothing_when_the_ship_already_exists() {
        Ship ship = ShipMother.random();

        shouldExists(ship);

        creator.create(ship.imo(), ship.name(), ship.teus());
    }

}
