package com.shiproutes.ships.ship.application.create;

import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.ships.ship.domain.*;

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

        Ship ship = Ship.create(imo, name, teus);

        repository.save(ship);
        eventBus.publish(ship.pullDomainEvents());
    }

    private void ensureShipNotExists(IMO imo) throws ShipAlreadyExists {
        if (repository.search(imo).isPresent()) {
            throw new ShipAlreadyExists(imo);
        }
    }
}
