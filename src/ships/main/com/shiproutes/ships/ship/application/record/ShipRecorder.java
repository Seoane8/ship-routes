package com.shiproutes.ships.ship.application.record;

import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.ships.ship.domain.*;

import java.util.List;

public final class ShipRecorder {
    private final ShipRepository repository;
    private final EventBus eventBus;

    public ShipRecorder(ShipRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void record(IMO imo, ShipName name, Teus teus) throws ShipAlreadyRecorded {
        ensureShipNotExists(imo);
        Ship ship = new Ship(imo, name, teus);
        repository.save(ship);
        ShipRecorded event = new ShipRecorded(ship.imo().value(), ship.name().value(), ship.teus().value());
        eventBus.publish(List.of(event));
    }

    private void ensureShipNotExists(IMO imo) throws ShipAlreadyRecorded {
        if (repository.search(imo).isPresent()) {
            throw new ShipAlreadyRecorded(imo);
        }
    }
}
