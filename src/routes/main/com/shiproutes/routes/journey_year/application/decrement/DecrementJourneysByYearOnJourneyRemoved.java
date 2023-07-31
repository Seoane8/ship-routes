package com.shiproutes.routes.journey_year.application.decrement;

import com.shiproutes.routes.shared.domain.JourneyRemovedEvent;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
public class DecrementJourneysByYearOnJourneyRemoved implements DomainEventSubscriber<JourneyRemovedEvent> {

    private final JourneysByYearDecrementer decrementer;

    public DecrementJourneysByYearOnJourneyRemoved(JourneysByYearDecrementer decrementer) {
        this.decrementer = decrementer;
    }

    @EventListener
    public void on(JourneyRemovedEvent event) {
        decrementer.decrement(
            new PortId(event.originPort()),
            new PortId(event.destinationPort()),
            new Teus(event.teus()),
            Year.fromInstant(event.departureDate())
        );
    }
}
