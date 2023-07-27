package com.shiproutes.routes.route.application.search_all;

import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.List;
import java.util.Objects;

public class RouteResponse implements Response {
    private final String id;
    private final String originPort;
    private final String destinationPort;
    private final List<List<Double>> path;
    private final Long journeys;

    public RouteResponse(String id, String originPort, String destinationPort, List<List<Double>> path, Long journeys) {
        this.id = id;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.path = path;
        this.journeys = journeys;
    }

    public static RouteResponse from(Route entity) {
        return new RouteResponse(
            entity.id().value(),
            entity.originPort().value(),
            entity.destinationPort().value(),
            entity.path().toPrimitives(),
            entity.journeys().value());
    }

    public String id() {
        return id;
    }

    public String originPort() {
        return originPort;
    }

    public String destinationPort() {
        return destinationPort;
    }

    public List<List<Double>> path() {
        return path;
    }

    public Long journeys() {
        return journeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RouteResponse)) return false;
        RouteResponse that = (RouteResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(originPort, that.originPort)
            && Objects.equals(destinationPort, that.destinationPort) && Objects.equals(path, that.path)
            && Objects.equals(journeys, that.journeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originPort, destinationPort, path, journeys);
    }
}
