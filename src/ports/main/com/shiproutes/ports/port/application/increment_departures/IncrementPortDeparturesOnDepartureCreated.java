package com.shiproutes.ports.port.application.increment_departures;

import com.shiproutes.ports.departure.domain.DepartureCreated;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({DepartureCreated.class})
public final class IncrementPortDeparturesOnDepartureCreated {

    private final PortDeparturesIncrementer incrementer;

    public IncrementPortDeparturesOnDepartureCreated(PortDeparturesIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @EventListener
    public void on(DepartureCreated event) {
        PortId portId = new PortId(event.portId());

        incrementer.increment(portId);
    }

}
