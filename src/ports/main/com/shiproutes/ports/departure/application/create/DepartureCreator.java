package com.shiproutes.ports.departure.application.create;

import com.shiproutes.ports.departure.domain.Departure;
import com.shiproutes.ports.departure.domain.DepartureDate;
import com.shiproutes.ports.departure.domain.DepartureId;
import com.shiproutes.ports.departure.domain.DepartureRepository;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.EventBus;

@Service
public final class DepartureCreator {

    private final DepartureRepository repository;
    private final EventBus eventBus;

    public DepartureCreator(DepartureRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(DepartureId id, PortId portId, IMO shipId, DepartureDate date) {
        Departure departure = Departure.create(id, portId, shipId, date);

        repository.save(departure);
        eventBus.publish(departure.pullDomainEvents());
    }
}
