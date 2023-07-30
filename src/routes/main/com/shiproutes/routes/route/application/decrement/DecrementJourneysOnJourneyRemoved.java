package com.shiproutes.routes.route.application.decrement;

import com.shiproutes.routes.shared.domain.JourneyRemovedEvent;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
public class DecrementJourneysOnJourneyRemoved implements DomainEventSubscriber<JourneyRemovedEvent> {

    private final JourneysDecrementer decrementer;

    public DecrementJourneysOnJourneyRemoved(JourneysDecrementer decrementer) {
        this.decrementer = decrementer;
    }

    @EventListener
    public void on(JourneyRemovedEvent event) {
        decrementer.decrement(
            new PortId(event.originPort()),
            new PortId(event.destinationPort())
        );
    }
}
