package com.shiproutes.ships.ship.application.record;

import com.shiproutes.ships.ship.domain.*;

public final class ShipRecorder {
    private final ShipRepository repository;

    public ShipRecorder(ShipRepository repository) {
        this.repository = repository;
    }

    public void record(IMO imo, ShipName name, Teus teus) throws ShipAlreadyRecorded {
        ensureShipNotExists(imo);
        Ship ship = new Ship(imo, name, teus);
        repository.save(ship);
    }

    private void ensureShipNotExists(IMO imo) throws ShipAlreadyRecorded {
        if (repository.search(imo).isPresent()) {
            throw new ShipAlreadyRecorded(imo);
        }
    }
}
