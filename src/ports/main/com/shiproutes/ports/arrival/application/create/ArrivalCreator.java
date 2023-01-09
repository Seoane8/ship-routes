package com.shiproutes.ports.arrival.application.create;

import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalDate;
import com.shiproutes.ports.arrival.domain.ArrivalId;
import com.shiproutes.ports.arrival.domain.ArrivalRepository;
import com.shiproutes.ports.shared.domain.PortId;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.EventBus;

@Service
public final class ArrivalCreator {

    private final ArrivalRepository repository;
    private final EventBus eventBus;

    public ArrivalCreator(ArrivalRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(ArrivalId id, PortId portId, IMO shipId, ArrivalDate date) {
        Arrival arrival = Arrival.create(id, portId, shipId, date);

        repository.save(arrival);
        eventBus.publish(arrival.pullDomainEvents());
    }
}
