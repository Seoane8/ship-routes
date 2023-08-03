package com.shiproutes.ports.port.application.create;

import com.shiproutes.ports.port.domain.Locode;
import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ingest.PortIngestedEvent;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
public class CreatePortOnPortIngested implements DomainEventSubscriber<PortIngestedEvent> {

    private final PortCreator creator;

    public CreatePortOnPortIngested(PortCreator creator) {
        this.creator = creator;
    }

    @Override
    @EventListener
    public void on(PortIngestedEvent event) {
        creator.create(
            new PortId(event.aggregateId()),
            new PortName(event.name()),
            new Locode(event.locode()),
            new Coordinates(
                new Latitude(event.latitude()),
                new Longitude(event.longitude())
            )
        );
    }
}
