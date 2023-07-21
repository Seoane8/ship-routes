package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Objects;

public final class Route extends AggregateRoot {
    private final RouteId id;
    private final PortId originPort;
    private final PortId destinationPort;
    private RoutePath path;

    public Route(RouteId id, PortId originPort, PortId destinationPort, RoutePath path) {
        this.id = id;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.path = path;
    }

    public static Route create(RouteId id, PortId originPort, PortId destinationPort, RoutePath path) {
        Route route = new Route(id, originPort, destinationPort, path);

        route.record(new RouteCreatedEvent(
            id.value(),
            originPort.value(),
            destinationPort.value(),
            path.toPrimitives()
        ));

        return route;
    }

    public RouteId id() {
        return id;
    }

    public PortId originPort() {
        return originPort;
    }

    public Coordinates originCoordinates() {
        return path.origin();
    }

    public PortId destinationPort() {
        return destinationPort;
    }

    public Coordinates destinationCoordinates() {
        return path.destination();
    }

    public RoutePath path() {
        return path;
    }

    public void updatePath(RoutePath path) {
        this.path = path;
        this.record(new RouteUpdatedEvent(
            id.value(),
            originPort.value(),
            destinationPort.value(),
            path.toPrimitives()
        ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id)
            && Objects.equals(originPort, route.originPort)
            && Objects.equals(destinationPort, route.destinationPort)
            && Objects.equals(path, route.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originPort, destinationPort, path);
    }
}
