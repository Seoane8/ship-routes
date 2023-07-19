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

}
