package com.shiproutes.ports.departure.application.create;

import com.shiproutes.ports.departure.domain.Departure;
import com.shiproutes.ports.departure.domain.DepartureDate;
import com.shiproutes.ports.departure.domain.DepartureId;
import com.shiproutes.ports.departure.domain.DepartureRepository;
import com.shiproutes.ports.shared.application.FindPortQuery;
import com.shiproutes.ports.shared.application.FindTeusQuery;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.ports.shared.application.TeusResponse;
import com.shiproutes.ports.shared.domain.Coordinates;
import com.shiproutes.ports.shared.domain.Latitude;
import com.shiproutes.ports.shared.domain.Longitude;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;

@Service
public final class DepartureCreator {

    private final DepartureRepository repository;
    private final QueryBus queryBus;
    private final EventBus eventBus;

    public DepartureCreator(DepartureRepository repository, QueryBus queryBus, EventBus eventBus) {
        this.repository = repository;
        this.queryBus = queryBus;
        this.eventBus = eventBus;
    }

    public void create(DepartureId id, PortId portId, IMO shipId, DepartureDate date) {
        Coordinates coordinates = findPortCoordinates(portId);
        Teus teus = findShipTeus(shipId);
        Departure departure = Departure.create(id, portId, coordinates, shipId, teus, date);

        repository.save(departure);
        eventBus.publish(departure.pullDomainEvents());
    }

    private Coordinates findPortCoordinates(PortId portId) {
        PortResponse port = queryBus.ask(new FindPortQuery(portId.value()));
        return new Coordinates(
            new Latitude(port.latitude()),
            new Longitude(port.longitude())
        );
    }

    private Teus findShipTeus(IMO shipId) {
        TeusResponse teus = queryBus.ask(new FindTeusQuery(shipId.value()));
        return new Teus(teus.value());
    }
}
