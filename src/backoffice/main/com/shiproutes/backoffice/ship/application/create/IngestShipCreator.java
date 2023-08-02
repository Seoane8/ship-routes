package com.shiproutes.backoffice.ship.application.create;

import com.shiproutes.backoffice.ship.domain.IngestShip;
import com.shiproutes.backoffice.ship.domain.IngestShipRepository;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;

@Service
public final class IngestShipCreator {

    private final IngestShipRepository repository;

    public IngestShipCreator(IngestShipRepository repository) {
        this.repository = repository;
    }

    public void create(IMO imo) {
        if (repository.search(imo).isPresent()) return;

        IngestShip ship = new IngestShip(imo);

        repository.save(ship);
    }
}
