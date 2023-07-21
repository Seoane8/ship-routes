package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.ports.PortIdMother;

public class RouteMother {

    public static Route random() {
        return new Route(
            RouteIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random()
        );
    }

    public static Route reverse(Route route) {
        return new Route(
            route.id(),
            route.destinationPort(),
            route.originPort(),
            route.path().reverse()
        );
    }

    public static Route updatePath(Route existentRoute) {
        return new Route(
            existentRoute.id(),
            existentRoute.originPort(),
            existentRoute.destinationPort(),
            RoutePathMother.update(existentRoute.path())
        );
    }
}
