package com.shiproutes.routes.route.domain;

import java.util.List;
import java.util.stream.Collectors;

public class RouteCreatedEventMother {

    public static RouteCreatedEvent fromRoute(Route route) {
        return new RouteCreatedEvent(
            route.id().value(),
            route.originPort().value(),
            route.destinationPort().value(),
            route.path().stream()
                .map(coordinates -> List.of(coordinates.latitude().value(), coordinates.longitude().value()))
                .collect(Collectors.toList())
        );
    }
}
