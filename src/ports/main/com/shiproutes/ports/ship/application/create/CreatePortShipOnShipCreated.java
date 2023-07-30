package com.shiproutes.ports.ship.application.create;

import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ShipCreatedEvent;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

@Service
public class CreatePortShipOnShipCreated implements DomainEventSubscriber<ShipCreatedEvent> {

    private final PortShipCreator creator;

    public CreatePortShipOnShipCreated(PortShipCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(ShipCreatedEvent event) {
        creator.create(new IMO(event.aggregateId()), new Teus(event.teus()));
    }
}
