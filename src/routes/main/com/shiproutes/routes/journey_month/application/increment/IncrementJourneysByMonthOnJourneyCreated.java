package com.shiproutes.routes.journey_month.application.increment;

import com.shiproutes.routes.shared.domain.JourneyCreatedEvent;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({JourneyCreatedEvent.class})
public class IncrementJourneysByMonthOnJourneyCreated {

    private final JourneysByMonthIncrementer incrementer;

    public IncrementJourneysByMonthOnJourneyCreated(JourneysByMonthIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @EventListener
    public void on(JourneyCreatedEvent event) {
        incrementer.increment(
            new PortId(event.originPort()),
            new PortId(event.destinationPort()),
            Month.fromInstant(event.departureDate()),
            Year.fromInstant(event.departureDate())
        );
    }
}
