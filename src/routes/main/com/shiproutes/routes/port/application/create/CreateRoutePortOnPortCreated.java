package com.shiproutes.routes.port.application.create;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.*;
import org.springframework.context.event.EventListener;

@Service
public class CreateRoutePortOnPortCreated implements DomainEventSubscriber<PortCreatedEvent> {

    private final RoutePortCreator creator;

    public CreateRoutePortOnPortCreated(RoutePortCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(PortCreatedEvent event) {
        creator.create(
            new PortId(event.aggregateId()),
            new Coordinates(
                new Latitude(event.latitude()),
                new Longitude(event.longitude())
            )
        );
    }
}
