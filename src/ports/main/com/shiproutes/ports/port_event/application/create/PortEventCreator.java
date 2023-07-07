package com.shiproutes.ports.port_event.application.create;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventDate;
import com.shiproutes.ports.port_event.domain.PortEventId;
import com.shiproutes.ports.port_event.domain.PortEventRepository;
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
public final class PortEventCreator {

    private final PortEventRepository repository;
    private final QueryBus queryBus;
    private final EventBus eventBus;

    public PortEventCreator(PortEventRepository repository, QueryBus queryBus, EventBus eventBus) {
        this.repository = repository;
        this.queryBus = queryBus;
        this.eventBus = eventBus;
    }

    public void create(PortEventId id, PortId portId, IMO shipId, PortEventDate date) {
        Coordinates coordinates = findPortCoordinates(portId);
        Teus teus = findShipTeus(shipId);
        PortEvent portEvent = PortEvent.create(id, portId, coordinates, shipId, teus, date);

        repository.save(portEvent);
        eventBus.publish(portEvent.pullDomainEvents());
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
