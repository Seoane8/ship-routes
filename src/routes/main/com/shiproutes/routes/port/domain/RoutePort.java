package com.shiproutes.routes.port.domain;

import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.ports.Coordinates;

import java.util.Objects;

public class RoutePort {

    private final PortId id;

    private final Coordinates coordinates;

    public RoutePort(PortId id, Coordinates coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }

    public PortId id() {
        return id;
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoutePort)) return false;
        RoutePort routePort = (RoutePort) o;
        return Objects.equals(id, routePort.id) && Objects.equals(coordinates, routePort.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coordinates);
    }
}
