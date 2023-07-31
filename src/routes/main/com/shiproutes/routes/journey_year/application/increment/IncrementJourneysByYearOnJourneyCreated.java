package com.shiproutes.routes.journey_year.application.increment;

import com.shiproutes.routes.shared.domain.JourneyCreatedEvent;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
public class IncrementJourneysByYearOnJourneyCreated implements DomainEventSubscriber<JourneyCreatedEvent> {

    private final JourneysByYearIncrementer incrementer;

    public IncrementJourneysByYearOnJourneyCreated(JourneysByYearIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @EventListener
    public void on(JourneyCreatedEvent event) {
        incrementer.increment(
            new PortId(event.originPort()),
            new PortId(event.destinationPort()),
            new Teus(event.teus()),
            Year.fromInstant(event.departureDate())
        );
    }
}
