package com.shiproutes.routes.journey_month.application.decrement;

import com.shiproutes.routes.shared.domain.JourneyRemovedEvent;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
public class DecrementJourneysByMonthOnJourneyRemoved implements DomainEventSubscriber<JourneyRemovedEvent> {

    private final JourneysByMonthDecrementer decrementer;

    public DecrementJourneysByMonthOnJourneyRemoved(JourneysByMonthDecrementer decrementer) {
        this.decrementer = decrementer;
    }

    @EventListener
    public void on(JourneyRemovedEvent event) {
        decrementer.decrement(
            new PortId(event.originPort()),
            new PortId(event.destinationPort()),
            Month.fromInstant(event.departureDate()),
            Year.fromInstant(event.departureDate())
        );
    }
}
