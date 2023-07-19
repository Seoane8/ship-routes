package com.shiproutes.ports.port_event_year.application.increment_events;

import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearId;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearRepository;
import com.shiproutes.ports.port_event_year.domain.Year;
import com.shiproutes.ports.shared.application.FindPortQuery;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.coordinates.Coordinates;
import com.shiproutes.shared.domain.coordinates.Latitude;
import com.shiproutes.shared.domain.coordinates.Longitude;

@Service
public class PortEventsByYearIncrementer {

    private final PortEventsByYearRepository repository;
    private final UuidGenerator uuidGenerator;
    private final QueryBus queryBus;

    public PortEventsByYearIncrementer(PortEventsByYearRepository repository, UuidGenerator uuidGenerator,
                                       QueryBus queryBus) {
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
        this.queryBus = queryBus;
    }

    public void increment(PortId portId, Year year, PortEventType type) {
        PortEventsByYear portEventsByYear = repository.search(portId, year)
            .orElseGet(() -> createPortEventsByYear(portId, year));

        if (type == PortEventType.ARRIVAL) {
            portEventsByYear.incrementArrivals();
        } else {
            portEventsByYear.incrementDepartures();
        }

        repository.save(portEventsByYear);
    }

    private PortEventsByYear createPortEventsByYear(PortId portId, Year year) {
        PortEventsByYearId id = new PortEventsByYearId(uuidGenerator.generate());
        Coordinates coordinates = findPortCoordinates(portId);
        return PortEventsByYear.create(id, portId, coordinates, year);
    }

    private Coordinates findPortCoordinates(PortId portId) {
        PortResponse port = queryBus.ask(new FindPortQuery(portId.value()));
        return new Coordinates(
            new Latitude(port.latitude()),
            new Longitude(port.longitude())
        );
    }
}
