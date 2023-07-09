package com.shiproutes.ports.port.application.increment_events;

import com.shiproutes.ports.port_event.domain.PortEventCreated;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({PortEventCreated.class})
public final class IncrementPortEventsOnPortEventCreated {

    private final PortEventsIncrementer incrementer;

    public IncrementPortEventsOnPortEventCreated(PortEventsIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @EventListener
    public void on(PortEventCreated event) {
        PortId portId = new PortId(event.portId());
        PortEventType type = PortEventType.valueOf(event.type());

        incrementer.increment(portId, type);
    }

}
