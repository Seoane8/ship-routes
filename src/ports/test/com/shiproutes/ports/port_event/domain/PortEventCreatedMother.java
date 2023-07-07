package com.shiproutes.ports.port_event.domain;

public final class PortEventCreatedMother {

    public static PortEventCreated fromPortEvent(PortEvent portEvent) {
        return new PortEventCreated(
            portEvent.id().value(),
            portEvent.type().value(),
            portEvent.portId().value(),
            portEvent.shipId().value(),
            portEvent.date().value()
        );
    }

    public static PortEventCreated random() {
        return fromPortEvent(PortEventMother.random());
    }

}
