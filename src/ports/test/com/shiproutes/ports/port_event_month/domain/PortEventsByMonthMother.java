package com.shiproutes.ports.port_event_month.domain;

import com.shiproutes.ports.port.domain.PortNameMother;
import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.shared.domain.TeusCounter;
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
            PortNameMother.random(),
            CoordinatesMother.random(),
            YearMother.random(),
            MonthMother.random(),
            new TotalDepartures(LongMother.random()),
            new TotalArrivals(LongMother.random()),
            new TeusCounter(IntegerMother.random()));
    }

    public static PortEventsByMonth firstFromPortEvent(PortEvent portEvent) {
        return new PortEventsByMonth(
            new PortEventsByMonthId(UuidMother.random()),
            portEvent.portId(),
            portEvent.portName(),
            portEvent.coordinates(),
            Year.fromInstant(portEvent.date().value()),
            Month.fromInstant(portEvent.date().value()),
            new TotalDepartures(portEvent.type() == PortEventType.DEPARTURE ? 1L : 0L),
            new TotalArrivals(portEvent.type() == PortEventType.ARRIVAL ? 1L : 0L),
            new TeusCounter(portEvent.teus().value())
        );
    }

    public static PortEventsByMonth fromPortEvent(PortEvent portEvent) {
        return new PortEventsByMonth(
            new PortEventsByMonthId(UuidMother.random()),
            portEvent.portId(),
            portEvent.portName(),
            portEvent.coordinates(),
            Year.fromInstant(portEvent.date().value()),
            Month.fromInstant(portEvent.date().value()),
            new TotalDepartures(LongMother.random()),
            new TotalArrivals(LongMother.random()),
            new TeusCounter(IntegerMother.random()));
    }

    public static PortEventsByMonth incrementing(PortEventsByMonth portEventsByMonth, PortEvent portEvent) {
        return new PortEventsByMonth(
            portEventsByMonth.id(),
            portEventsByMonth.portId(),
            portEventsByMonth.portName(),
            portEventsByMonth.coordinates(),
            portEventsByMonth.year(),
            portEventsByMonth.month(),
            portEvent.type() == PortEventType.DEPARTURE ? portEventsByMonth.departures().increment() : portEventsByMonth.departures(),
            portEvent.type() == PortEventType.ARRIVAL ? portEventsByMonth.arrivals().increment() : portEventsByMonth.arrivals(),
            portEventsByMonth.teusCounter().increment(portEvent.teus())
        );
    }

}
