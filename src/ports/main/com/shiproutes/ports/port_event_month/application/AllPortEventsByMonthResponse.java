package com.shiproutes.ports.port_event_month.application;

import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AllPortEventsByMonthResponse implements Response {

    private final Set<PortEventsByMonthResponse> ports;

    public AllPortEventsByMonthResponse(Collection<PortEventsByMonthResponse> ports) {
        this.ports = new HashSet<>(ports);
    }

    public Set<PortEventsByMonthResponse> ports() {
        return ports;
    }
}
