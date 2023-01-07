package com.shiproutes.ships.ship.application.find;

import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.ships.ship.domain.ShipNotExists;
import com.shiproutes.ships.ship.domain.ShipRepository;

@Service
public final class ShipFinder {

    private final ShipRepository repository;

    public ShipFinder(ShipRepository repository) {
        this.repository = repository;
    }

    public ShipResponse find(IMO imo) {
        return repository.search(imo).map(ShipResponse::from).orElseThrow(() -> new ShipNotExists(imo));
    }
}
