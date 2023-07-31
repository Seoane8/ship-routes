package com.shiproutes.ports.port_event_year.domain;

import com.shiproutes.ports.port.domain.PortNameMother;
import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.*;
import com.shiproutes.shared.domain.ports.CoordinatesMother;
import com.shiproutes.shared.domain.ports.PortIdMother;
import com.shiproutes.shared.domain.ports.TeusCounter;

public class PortEventsByYearMother {

    public static PortEventsByYear random() {
        return new PortEventsByYear(
            new PortEventsByYearId(UuidMother.random()),
            PortIdMother.random(),
            PortNameMother.random(),
            CoordinatesMother.random(),
            YearMother.random(),
            new TotalDepartures(LongMother.random()),
            new TotalArrivals(LongMother.random()),
            new TeusCounter(IntegerMother.random())
        );
    }

    public static PortEventsByYear firstFromPortEvent(PortEvent portEvent) {
        return new PortEventsByYear(
            new PortEventsByYearId(UuidMother.random()),
            portEvent.portId(),
            portEvent.portName(),
            portEvent.coordinates(),
            Year.fromInstant(portEvent.date().value()),
            new TotalDepartures(portEvent.type() == PortEventType.DEPARTURE ? 1L : 0L),
            new TotalArrivals(portEvent.type() == PortEventType.ARRIVAL ? 1L : 0L),
            new TeusCounter(portEvent.teus().value())
        );
    }

    public static PortEventsByYear fromPortEvent(PortEvent portEvent) {
        return new PortEventsByYear(
            new PortEventsByYearId(UuidMother.random()),
            portEvent.portId(),
            portEvent.portName(),
            portEvent.coordinates(),
            Year.fromInstant(portEvent.date().value()),
            new TotalDepartures(LongMother.random()),
            new TotalArrivals(LongMother.random()),
            new TeusCounter(IntegerMother.random())
        );
    }

    public static PortEventsByYear incrementing(PortEventsByYear portEventsByYear, PortEvent portEvent) {
        return new PortEventsByYear(
            portEventsByYear.id(),
            portEventsByYear.portId(),
            portEventsByYear.portName(),
            portEventsByYear.coordinates(),
            portEventsByYear.year(),
            portEvent.type() == PortEventType.DEPARTURE ? portEventsByYear.departures().increment() : portEventsByYear.departures(),
            portEvent.type() == PortEventType.ARRIVAL ? portEventsByYear.arrivals().increment() : portEventsByYear.arrivals(),
            portEventsByYear.teusCounter().increment(portEvent.teus())
        );
    }

}
