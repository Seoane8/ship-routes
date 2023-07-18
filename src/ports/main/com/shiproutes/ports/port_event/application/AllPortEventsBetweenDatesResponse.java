package com.shiproutes.ports.port_event.application;

import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AllPortEventsBetweenDatesResponse implements Response {

    private final Set<PortEventsBetweenDatesResponse> ports;

    public AllPortEventsBetweenDatesResponse(Collection<PortEventsBetweenDatesResponse> ports) {
        this.ports = new HashSet<>(ports);
    }

    public Set<PortEventsBetweenDatesResponse> ports() {
        return ports;
    }
}
