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
            PortTotalEvents.initialize()
        );
    }

    public static Port random() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            PortTotalEventsMother.random()
        );
    }

    public static Port fromId(PortId portId) {
        return new Port(
            portId,
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            PortTotalEventsMother.random()
        );
    }

    public static Port incrementingEvents(Port port) {
        return new Port(
            port.id(),
            port.name(),
            port.locode(),
            port.coordinates(),
            new PortTotalEvents(port.totalEvents().value() + 1)
        );
    }
}
