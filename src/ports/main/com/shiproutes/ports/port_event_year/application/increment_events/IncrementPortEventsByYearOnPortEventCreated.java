package com.shiproutes.ports.port_event_year.application.increment_events;

import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortEventCreated;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
public class IncrementPortEventsByYearOnPortEventCreated implements DomainEventSubscriber<PortEventCreated> {

    private final PortEventsByYearIncrementer incrementer;

    public IncrementPortEventsByYearOnPortEventCreated(PortEventsByYearIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @EventListener
    public void on(PortEventCreated event) {
        PortId portId = new PortId(event.portId());
        PortEventType type = PortEventType.valueOf(event.type());
        Year year = Year.fromInstant(event.date());
        incrementer.increment(portId, year, type);
    }
}
