package com.shiproutes.ships.ship.application.create;

import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ingest.ShipIngestedEvent;
import com.shiproutes.ships.ship.domain.ShipName;
import org.springframework.context.event.EventListener;

@Service
public class CreateShipOnShipIngested implements DomainEventSubscriber<ShipIngestedEvent> {

    private final ShipCreator creator;

    public CreateShipOnShipIngested(ShipCreator creator) {
        this.creator = creator;
    }

    @Override
    @EventListener
    public void on(ShipIngestedEvent event) {
        creator.create(
            new IMO(event.aggregateId()),
            new ShipName(event.name()),
            new Teus(event.teus())
        );

    }
}
