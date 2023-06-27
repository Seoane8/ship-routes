package com.shiproutes.ports.arrival.application.create;

import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalDate;
import com.shiproutes.ports.arrival.domain.ArrivalId;
import com.shiproutes.ports.arrival.domain.ArrivalRepository;
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
public final class ArrivalCreator {

    private final ArrivalRepository repository;
    private final QueryBus queryBus;
    private final EventBus eventBus;

    public ArrivalCreator(ArrivalRepository repository, QueryBus queryBus, EventBus eventBus) {
        this.repository = repository;
        this.queryBus = queryBus;
        this.eventBus = eventBus;
    }

    public void create(ArrivalId id, PortId portId, IMO shipId, ArrivalDate date) {
        Coordinates coordinates = findPortCoordinates(portId);
        Teus teus = findShipTeus(shipId);
        Arrival arrival = Arrival.create(id, portId, coordinates, shipId, teus, date);

        repository.save(arrival);
        eventBus.publish(arrival.pullDomainEvents());
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
