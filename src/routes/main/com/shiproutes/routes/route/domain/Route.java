package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.PortId;

import java.util.Objects;

public final class Route {
    private final RouteId id;
    private final PortId departurePort;
    private final PortId arrivalPort;
    private final RouteDistance distance;

    public Route(RouteId id, PortId departurePort, PortId arrivalPort, RouteDistance distance) {
        this.id = id;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.distance = distance;
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

    public RouteDistance distance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(departurePort, route.departurePort)
            && Objects.equals(arrivalPort, route.arrivalPort) && Objects.equals(distance, route.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departurePort, arrivalPort, distance);
    }
}
