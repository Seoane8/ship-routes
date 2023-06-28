package com.shiproutes.ports.ship.domain;

import com.shiproutes.shared.domain.IMO;

import java.util.Optional;

public interface PortShipRepository {

    void save(PortShip ship);

    Optional<PortShip> search(IMO imo);

}
