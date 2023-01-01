package com.shiproutes.ships.ship.infrastructure.persistence;

import com.shiproutes.ships.ship.ShipModuleInfrastructureTestCase;
import com.shiproutes.ships.ship.domain.IMOMother;
import com.shiproutes.ships.ship.domain.Ship;
import com.shiproutes.ships.ship.domain.ShipMother;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class MySqlShipRepositoryShould extends ShipModuleInfrastructureTestCase {

    @Test
    void save_a_ship() {
        Ship ship = ShipMother.random();

        mySqlShipRepository.save(ship);
    }

    @Test
    void return_an_existing_ship() {
        Ship ship = ShipMother.random();
        mySqlShipRepository.save(ship);

        Optional<Ship> foundShip = mySqlShipRepository.search(ship.imo());
        assertEquals(Optional.of(ship), foundShip);
    }

    @Test
    void not_return_a_non_existent_ship() {
        assertEquals(Optional.empty(), mySqlShipRepository.search(IMOMother.random()));
    }
}
