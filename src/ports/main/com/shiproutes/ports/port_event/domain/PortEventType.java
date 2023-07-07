package com.shiproutes.ports.port_event.domain;

public enum PortEventType {
    ARRIVAL,
    DEPARTURE;

    public String value() {
        return this.name();
    }
}
