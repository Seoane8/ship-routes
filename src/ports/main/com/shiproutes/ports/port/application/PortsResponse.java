package com.shiproutes.ports.port.application;

import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Set;
import java.util.stream.Collectors;

public final class PortsResponse implements Response {

    private final Set<PortResponse> ports;

    public PortsResponse(Set<PortResponse> ports) {
        this.ports = ports;
    }

    public static PortsResponse from(Set<Port> ports) {
        return ports.stream().map(PortResponse::from)
            .collect(Collectors.collectingAndThen(Collectors.toSet(), PortsResponse::new));
    }

    public Set<PortResponse> ports() {
        return ports;
    }
}
