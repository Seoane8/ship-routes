package com.shiproutes.routes.route.domain;

import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.ports.TeusCounter;

import java.util.Objects;

public final class Route extends AggregateRoot {
    private final RouteId id;
    private final PortId originPort;
    private final PortId destinationPort;
    private RoutePath path;
    private JourneysCounter journeys;
    private TeusCounter teus;

    public Route(RouteId id, PortId originPort, PortId destinationPort, RoutePath path,
                 JourneysCounter journeys, TeusCounter teus) {
        this.id = id;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.path = path;
        this.journeys = journeys;
        this.teus = teus;
    }

    public static Route create(RouteId id, PortId originPort, PortId destinationPort, RoutePath path) {
        Route route = new Route(id, originPort, destinationPort, path,
            JourneysCounter.initialize(), TeusCounter.initialize());

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

    public JourneysCounter journeys() {
        return journeys;
    }

    public TeusCounter teus() {
        return teus;
    }

    public void incrementJourneys() {
        journeys = journeys.increment();
    }

    public void decrementJourneys() {
        journeys = journeys.decrement();
    }

    public void incrementTeus(Teus teus) {
        this.teus = this.teus.increment(teus);
    }

    public void decrementTeus(Teus teus) {
        this.teus = this.teus.decrement(teus);
    }

    public boolean matches(PortId originPort, PortId destinationPort) {
        return this.originPort.equals(originPort) && this.destinationPort.equals(destinationPort);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(originPort, route.originPort)
            && Objects.equals(destinationPort, route.destinationPort) && Objects.equals(path, route.path)
            && Objects.equals(journeys, route.journeys) && Objects.equals(teus, route.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originPort, destinationPort, path, journeys, teus);
    }
}
