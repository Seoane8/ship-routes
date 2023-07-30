package com.shiproutes.ports.port_event.application.create;

import com.shiproutes.ports.port_event.domain.PortEventDate;
import com.shiproutes.ports.port_event.domain.PortEventId;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.PortEventIngestedEvent;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({PortEventIngestedEvent.class})
public class CreatePortEventOnPortEventIngested {

    private final PortEventCreator creator;

    public CreatePortEventOnPortEventIngested(PortEventCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(PortEventIngestedEvent event) {
        creator.create(
            new PortEventId(event.aggregateId()),
            PortEventType.valueOf(event.type()),
            new PortId(event.portId()),
            new IMO(event.shipId()),
            new PortEventDate(event.date())
        );
    }
}
