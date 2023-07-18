package com.shiproutes.ports.port_event_month.application.increment_events;

import com.shiproutes.ports.port_event.domain.PortEventCreated;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.port_event_month.domain.Month;
import com.shiproutes.ports.port_event_year.domain.Year;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({PortEventCreated.class})
public class IncrementPortEventsByMonthOnPortEventCreated {

    private final PortEventsByMonthIncrementer incrementer;

    public IncrementPortEventsByMonthOnPortEventCreated(PortEventsByMonthIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @EventListener
    public void on(PortEventCreated event) {
        PortId portId = new PortId(event.portId());
        PortEventType type = PortEventType.valueOf(event.type());
        Year year = Year.fromInstant(event.date());
        Month month = Month.fromInstant(event.date());
        incrementer.increment(portId, year, month, type);
    }
}