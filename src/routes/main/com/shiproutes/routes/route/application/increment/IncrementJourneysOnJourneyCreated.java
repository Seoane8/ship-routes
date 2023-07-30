package com.shiproutes.routes.route.application.increment;

import com.shiproutes.routes.shared.domain.JourneyCreatedEvent;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
public class IncrementJourneysOnJourneyCreated implements DomainEventSubscriber<JourneyCreatedEvent> {

    private final JourneysIncrementer incrementer;

    public IncrementJourneysOnJourneyCreated(JourneysIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @EventListener
    public void on(JourneyCreatedEvent event) {
        incrementer.increment(
            new PortId(event.originPort()),
            new PortId(event.destinationPort())
        );
    }
}
