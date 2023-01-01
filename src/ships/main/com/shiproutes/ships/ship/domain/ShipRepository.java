package com.shiproutes.ships.ship.domain;

import java.util.Optional;

public interface ShipRepository {

    void save(Ship ship);

    Optional<Ship> search(IMO imo);

}
