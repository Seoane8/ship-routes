package com.shiproutes.backoffice.ship.application.ingest;

import com.shiproutes.backoffice.ship.domain.IngestShip;
import com.shiproutes.backoffice.ship.domain.IngestShipRepository;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.ingest.ShipIngestedEvent;

import java.util.List;

@Service
public class ShipIngestor {

    private final IngestShipRepository repository;
    private final EventBus eventBus;

    public ShipIngestor(IngestShipRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void ingest(IMO imo, String name, Teus teus) {
        if (repository.search(imo).isPresent()) return;

        IngestShip ship = new IngestShip(imo);

        ShipIngestedEvent event = new ShipIngestedEvent(
            ship.imo().value(),
            name,
            teus.value()
        );
        eventBus.publish(List.of(event));
        repository.save(ship);
    }
}
