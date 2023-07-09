package com.shiproutes.ports.port_event_year.application;

import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AllPortEventsByYearResponse implements Response {

    private final Set<PortEventsByYearResponse> ports;

    public AllPortEventsByYearResponse(Collection<PortEventsByYearResponse> ports) {
        this.ports = new HashSet<>(ports);
    }

    public Set<PortEventsByYearResponse> ports() {
        return ports;
    }
}
