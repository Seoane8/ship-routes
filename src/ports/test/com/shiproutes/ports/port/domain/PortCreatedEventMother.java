package com.shiproutes.ports.port.domain;

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
