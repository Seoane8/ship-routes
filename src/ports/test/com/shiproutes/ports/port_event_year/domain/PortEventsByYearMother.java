package com.shiproutes.ports.port_event_year.domain;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.LongMother;
import com.shiproutes.shared.domain.UuidMother;
import com.shiproutes.shared.domain.ports.CoordinatesMother;
import com.shiproutes.shared.domain.ports.PortIdMother;

public class PortEventsByYearMother {

    public static PortEventsByYear random() {
        return new PortEventsByYear(
            new PortEventsByYearId(UuidMother.random()),
            PortIdMother.random(),
            CoordinatesMother.random(),
            YearMother.random(),
            new TotalDepartures(LongMother.random()),
            new TotalArrivals(LongMother.random())
        );
    }

    public static PortEventsByYear firstFromPortEvent(PortEvent portEvent) {
        return new PortEventsByYear(
            new PortEventsByYearId(UuidMother.random()),
            portEvent.portId(),
            portEvent.coordinates(),
            Year.fromInstant(portEvent.date().value()),
            new TotalDepartures(portEvent.type() == PortEventType.DEPARTURE ? 1L : 0L),
            new TotalArrivals(portEvent.type() == PortEventType.ARRIVAL ? 1L : 0L)
        );
    }

    public static PortEventsByYear fromPortEvent(PortEvent portEvent) {
        return new PortEventsByYear(
            new PortEventsByYearId(UuidMother.random()),
            portEvent.portId(),
            portEvent.coordinates(),
            Year.fromInstant(portEvent.date().value()),
            new TotalDepartures(LongMother.random()),
            new TotalArrivals(LongMother.random())
        );
    }

    public static PortEventsByYear incrementingDepartures(PortEventsByYear portEventsByYear) {
        return new PortEventsByYear(
            portEventsByYear.id(),
            portEventsByYear.portId(),
            portEventsByYear.coordinates(),
            portEventsByYear.year(),
            portEventsByYear.departures().increment(),
            portEventsByYear.arrivals()
        );
    }

    public static PortEventsByYear incrementingArrivals(PortEventsByYear portEventsByYear) {
        return new PortEventsByYear(
            portEventsByYear.id(),
            portEventsByYear.portId(),
            portEventsByYear.coordinates(),
            portEventsByYear.year(),
            portEventsByYear.departures(),
            portEventsByYear.arrivals().increment()
        );
    }
}
