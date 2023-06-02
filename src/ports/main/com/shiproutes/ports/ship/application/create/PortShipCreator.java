package com.shiproutes.ports.ship.application.create;

import com.shiproutes.ports.ship.domain.PortShip;
import com.shiproutes.ports.ship.domain.PortShipRepository;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;

@Service
public final class PortShipCreator {

    private final PortShipRepository repository;

    public PortShipCreator(PortShipRepository repository) {
        this.repository = repository;
    }

    public void crate(IMO imo, Integer teus) {
        if (repository.search(imo).isPresent()) return;

        PortShip ship = new PortShip(imo, teus);

        repository.save(ship);

    }
}
