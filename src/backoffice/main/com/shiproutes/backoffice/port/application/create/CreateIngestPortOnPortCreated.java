package com.shiproutes.backoffice.port.application.create;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortCreatedEvent;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
public class CreateIngestPortOnPortCreated implements DomainEventSubscriber<PortCreatedEvent> {

    private final IngestPortCreator creator;

    public CreateIngestPortOnPortCreated(IngestPortCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(PortCreatedEvent event) {
        creator.create(
            new PortId(event.aggregateId()),
            event.locode()
        );
    }
}
