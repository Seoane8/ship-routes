package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.PortId;

import java.util.Objects;

public final class Route {
    private final RouteId id;
    private final PortId departurePort;
    private final PortId arrivalPort;
    private final RoutePath path;

    public Route(RouteId id, PortId departurePort, PortId arrivalPort, RoutePath path) {
        this.id = id;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.path = path;
    }

    public RouteId id() {
        return id;
    }

    public PortId departurePort() {
        return departurePort;
    }

    public PortId arrivalPort() {
        return arrivalPort;
    }

    public RoutePath path() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id)
            && Objects.equals(departurePort, route.departurePort)
            && Objects.equals(arrivalPort, route.arrivalPort)
            && Objects.equals(path, route.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departurePort, arrivalPort, path);
    }
}
