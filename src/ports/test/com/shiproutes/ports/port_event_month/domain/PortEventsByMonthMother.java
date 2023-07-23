package com.shiproutes.ports.port_event_month.domain;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.*;
import com.shiproutes.shared.domain.ports.CoordinatesMother;
import com.shiproutes.shared.domain.ports.PortIdMother;

public class PortEventsByMonthMother {
    public static PortEventsByMonth random() {
        return new PortEventsByMonth(
            new PortEventsByMonthId(UuidMother.random()),
            PortIdMother.random(),
            CoordinatesMother.random(),
            YearMother.random(),
            MonthMother.random(),
            new TotalDepartures(LongMother.random()),
            new TotalArrivals(LongMother.random())
        );
    }

    public static PortEventsByMonth firstFromPortEvent(PortEvent portEvent) {
        return new PortEventsByMonth(
            new PortEventsByMonthId(UuidMother.random()),
            portEvent.portId(),
            portEvent.coordinates(),
            Year.fromInstant(portEvent.date().value()),
            Month.fromInstant(portEvent.date().value()),
            new TotalDepartures(portEvent.type() == PortEventType.DEPARTURE ? 1L : 0L),
            new TotalArrivals(portEvent.type() == PortEventType.ARRIVAL ? 1L : 0L)
        );
    }

    public static PortEventsByMonth fromPortEvent(PortEvent portEvent) {
        return new PortEventsByMonth(
            new PortEventsByMonthId(UuidMother.random()),
            portEvent.portId(),
            portEvent.coordinates(),
            Year.fromInstant(portEvent.date().value()),
            Month.fromInstant(portEvent.date().value()),
            new TotalDepartures(LongMother.random()),
            new TotalArrivals(LongMother.random())
        );
    }

    public static PortEventsByMonth incrementingDepartures(PortEventsByMonth portEventsByMonth) {
        return new PortEventsByMonth(
            portEventsByMonth.id(),
            portEventsByMonth.portId(),
            portEventsByMonth.coordinates(),
            portEventsByMonth.year(),
            portEventsByMonth.month(),
            portEventsByMonth.departures().increment(),
            portEventsByMonth.arrivals()
        );
    }

    public static PortEventsByMonth incrementingArrivals(PortEventsByMonth portEventsByMonth) {
        return new PortEventsByMonth(
            portEventsByMonth.id(),
            portEventsByMonth.portId(),
            portEventsByMonth.coordinates(),
            portEventsByMonth.year(),
            portEventsByMonth.month(),
            portEventsByMonth.departures(),
            portEventsByMonth.arrivals().increment()
        );
    }
}
