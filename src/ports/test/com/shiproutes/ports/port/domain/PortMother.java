package com.shiproutes.ports.port.domain;

import com.shiproutes.ports.shared.domain.*;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.coordinates.CoordinatesMother;

public final class PortMother {

    public static Port randomNew() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            TotalDepartures.initialize(),
            TotalArrivals.initialize());
    }

    public static Port random() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            TotalDeparturesMother.random(),
            TotalArrivalsMother.random());
    }

    public static Port fromId(PortId portId) {
        return new Port(
            portId,
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            TotalDeparturesMother.random(),
            TotalArrivalsMother.random());
    }

    public static Port incrementingDepartures(Port port) {
        return new Port(
            port.id(),
            port.name(),
            port.locode(),
            port.coordinates(),
            new TotalDepartures(port.totalDepartures().value() + 1),
            port.totalArrivals());
    }

    public static Port incrementingArrivals(Port port) {
        return new Port(
            port.id(),
            port.name(),
            port.locode(),
            port.coordinates(),
            port.totalDepartures(),
            new TotalArrivals(port.totalArrivals().value() + 1));
    }
}
