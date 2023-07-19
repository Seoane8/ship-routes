package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.ports.PortCreatedEvent;

public final class PortCreatedEventMother {
    public static PortCreatedEvent fromPort(Port port) {
        return new PortCreatedEvent(
            port.id().value(),
            port.name().value(),
            port.locode().value(),
            port.coordinates().latitude().value(),
            port.coordinates().longitude().value()
        );
    }
}
