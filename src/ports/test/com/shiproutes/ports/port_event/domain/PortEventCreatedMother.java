package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.ports.PortEventCreated;

public final class PortEventCreatedMother {

    public static PortEventCreated fromPortEvent(PortEvent portEvent) {
        return new PortEventCreated(
            portEvent.id().value(),
            portEvent.portId().value(),
            portEvent.type().value(),
            portEvent.shipId().value(),
            portEvent.date().value(),
            portEvent.teus().value()
        );
    }

    public static PortEventCreated random() {
        return fromPortEvent(PortEventMother.random());
    }

}
