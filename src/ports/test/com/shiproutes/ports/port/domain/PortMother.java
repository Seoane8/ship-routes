package com.shiproutes.ports.port.domain;

import com.shiproutes.ports.shared.domain.CoordinatesMother;
import com.shiproutes.ports.shared.domain.PortIdMother;
import com.shiproutes.shared.domain.PortId;

public final class PortMother {

    public static Port randomNew() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            PortTotalDepartures.initialize()
        );
    }

    public static Port random() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            PortTotalDeparturesMother.random()
        );
    }

    public static Port fromId(PortId portId) {
        return new Port(
            portId,
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            PortTotalDeparturesMother.random()
        );
    }

    public static Port incrementingDepartures(Port port) {
        return new Port(
            port.id(),
            port.name(),
            port.locode(),
            port.coordinates(),
            new PortTotalDepartures(port.totalDepartures().value() + 1)
        );
    }
}
