package com.shiproutes.ingest.ship.application.create;

import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ShipCreatedEvent;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({ShipCreatedEvent.class})
public class CreateIngestShipOnShipCreated {

    private final IngestShipCreator creator;

    public CreateIngestShipOnShipCreated(IngestShipCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(ShipCreatedEvent event) {
        creator.create(new IMO(event.aggregateId()));
    }
}
