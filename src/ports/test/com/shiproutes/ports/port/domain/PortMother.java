package com.shiproutes.ports.port.domain;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalArrivalsMother;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.ports.shared.domain.TotalDeparturesMother;
import com.shiproutes.shared.domain.IntegerMother;
import com.shiproutes.shared.domain.ports.CoordinatesMother;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.ports.PortIdMother;
import com.shiproutes.shared.domain.ports.TeusCounter;

public final class PortMother {

    public static Port randomNew() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            TotalDepartures.initialize(),
            TotalArrivals.initialize(),
            TeusCounter.initialize()
        );
    }

    public static Port random() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            TotalDeparturesMother.random(),
            TotalArrivalsMother.random(),
            new TeusCounter(IntegerMother.random())
        );
    }

    public static Port fromId(PortId portId) {
        return new Port(
            portId,
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            TotalDeparturesMother.random(),
            TotalArrivalsMother.random(),
            new TeusCounter(IntegerMother.random())
        );
    }

    public static Port incrementing(Port port, PortEvent portEvent) {
        return new Port(
            port.id(),
            port.name(),
            port.locode(),
            port.coordinates(),
            portEvent.type() == PortEventType.DEPARTURE ? new TotalDepartures(port.totalDepartures().value() + 1) : port.totalDepartures(),
            portEvent.type() == PortEventType.ARRIVAL ? new TotalArrivals(port.totalArrivals().value() + 1) : port.totalArrivals(),
            port.teus().increment(portEvent.teus())
        );
    }

}
