package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RouteCreatedEvent extends DomainEvent {

    private final String origin;
    private final String destination;
    private final List<List<Double>> path;

    public RouteCreatedEvent() {
        super(null);
        this.origin = null;
        this.destination = null;
        this.path = null;
    }

    public RouteCreatedEvent(String aggregateId, String origin, String destination, List<List<Double>> path) {
        super(aggregateId);
        this.origin = origin;
        this.destination = destination;
        this.path = path;
    }

    public RouteCreatedEvent(String aggregateId, String eventId, String occurredOn,
                             String origin, String destination, List<List<Double>> path) {
        super(aggregateId, eventId, occurredOn);
        this.origin = origin;
        this.destination = destination;
        this.path = path;
    }

    @Override
    public String eventName() {
        return "route.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("origin", origin);
            put("destination", destination);
            put("path", path.stream().map(ArrayList::new).collect(Collectors.toCollection(ArrayList::new)));
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId,
                                      String occurredOn) {
        return new RouteCreatedEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("origin"),
            (String) body.get("destination"),
            (List<List<Double>>) body.get("path")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RouteCreatedEvent)) return false;
        RouteCreatedEvent that = (RouteCreatedEvent) o;
        return Objects.equals(origin, that.origin) && Objects.equals(destination, that.destination)
            && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, path);
    }
}
