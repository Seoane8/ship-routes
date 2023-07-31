package com.shiproutes.ports.port_event_month.application.increment_events;

import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonth;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthId;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthRepository;
import com.shiproutes.ports.shared.application.FindPortQuery;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.shared.domain.*;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class PortEventsByMonthIncrementer {

    private final PortEventsByMonthRepository repository;
    private final UuidGenerator uuidGenerator;
    private final QueryBus queryBus;

    public PortEventsByMonthIncrementer(PortEventsByMonthRepository repository, UuidGenerator uuidGenerator,
                                        QueryBus queryBus) {
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
        this.queryBus = queryBus;
    }

    public void increment(PortId portId, Year year, Month month, PortEventType type, Teus teus) {
        PortEventsByMonth portEventsByMonth = repository.search(portId, year, month)
            .orElseGet(() -> createPortEventsByMonth(portId, year, month));

        if (type == PortEventType.ARRIVAL) {
            portEventsByMonth.incrementArrivals();
        } else {
            portEventsByMonth.incrementDepartures();
        }
        portEventsByMonth.incrementTeus(teus);

        repository.save(portEventsByMonth);
    }

    private PortEventsByMonth createPortEventsByMonth(PortId portId, Year year, Month month) {
        PortEventsByMonthId id = new PortEventsByMonthId(uuidGenerator.generate());
        Coordinates coordinates = findPortCoordinates(portId);
        return PortEventsByMonth.create(id, portId, coordinates, year, month);
    }

    private Coordinates findPortCoordinates(PortId portId) {
        PortResponse port = queryBus.ask(new FindPortQuery(portId.value()));
        return new Coordinates(
            new Latitude(port.latitude()),
            new Longitude(port.longitude())
        );
    }
}
