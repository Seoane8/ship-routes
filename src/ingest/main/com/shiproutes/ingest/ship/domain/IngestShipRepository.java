package com.shiproutes.ingest.ship.domain;

import com.shiproutes.shared.domain.IMO;

import java.util.Optional;

public interface IngestShipRepository {

    void save(IngestShip ship);

    Optional<IngestShip> search(IMO imo);

}
