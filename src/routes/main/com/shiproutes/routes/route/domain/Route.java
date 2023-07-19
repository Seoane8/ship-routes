package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Objects;

public final class Route extends AggregateRoot {
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

    public static Route create(RouteId id, PortId originPort, PortId destinationPort, RoutePath path) {

        return new Route(id, originPort, destinationPort, path);
    }

    public RouteId id() {
        return id;
    }

    public PortId departurePort() {
        return departurePort;
    }

    public Coordinates departureCoordinates() {
        return path.origin();
    }

    public PortId arrivalPort() {
        return arrivalPort;
    }

    public Coordinates arrivalCoordinates() {
        return path.destination();
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
