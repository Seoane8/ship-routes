package com.shiproutes.backoffice.ship.application.find_ship;

import com.shiproutes.backoffice.ship.domain.IngestShipRepository;
import com.shiproutes.backoffice.ship.domain.ShipNotExist;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;

@Service
public final class IngestShipFinder {

    private final IngestShipRepository repository;

    public IngestShipFinder(IngestShipRepository repository) {
        this.repository = repository;
    }

    public IngestShipResponse find(IMO imo) {
        return repository.search(imo).map(IngestShipResponse::from).orElseThrow(() -> new ShipNotExist(imo));
    }
}
