package com.shiproutes.ships.ship.application.create;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.ships.ship.domain.*;

import java.util.List;

@Service
public final class ShipCreator {
    private final ShipRepository repository;
    private final EventBus eventBus;

    public ShipCreator(ShipRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(IMO imo, ShipName name, Teus teus) throws ShipAlreadyExists {
        ensureShipNotExists(imo);
        Ship ship = new Ship(imo, name, teus);
        repository.save(ship);
        ShipCreatedEvent event = new ShipCreatedEvent(ship.imo().value(), ship.name().value(), ship.teus().value());
        eventBus.publish(List.of(event));
    }

    private void ensureShipNotExists(IMO imo) throws ShipAlreadyExists {
        if (repository.search(imo).isPresent()) {
            throw new ShipAlreadyExists(imo);
        }
    }
}
