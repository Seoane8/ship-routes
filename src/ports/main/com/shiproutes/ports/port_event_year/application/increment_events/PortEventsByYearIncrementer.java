package com.shiproutes.ports.port_event_year.application.increment_events;

import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearId;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearRepository;
import com.shiproutes.ports.shared.application.FindPortQuery;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

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

    public void increment(PortId portId, Year year, PortEventType type, Teus teus) {
        PortEventsByYear portEventsByYear = repository.search(portId, year)
            .orElseGet(() -> createPortEventsByYear(portId, year));

        if (type == PortEventType.ARRIVAL) {
            portEventsByYear.incrementArrivals();
        } else {
            portEventsByYear.incrementDepartures();
        }
        portEventsByYear.incrementTeus(teus);

        repository.save(portEventsByYear);
    }

    private PortEventsByYear createPortEventsByYear(PortId portId, Year year) {
        PortEventsByYearId id = new PortEventsByYearId(uuidGenerator.generate());
        PortResponse port = queryBus.ask(new FindPortQuery(portId.value()));
        Coordinates coordinates = new Coordinates(
            new Latitude(port.latitude()),
            new Longitude(port.longitude())
        );
        PortName portName = new PortName(port.name());
        return PortEventsByYear.create(id, portId, portName, coordinates, year);
    }

}
