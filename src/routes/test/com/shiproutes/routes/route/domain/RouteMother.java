package com.shiproutes.routes.route.domain;

import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.JourneysCounterMother;
import com.shiproutes.routes.shared.domain.RoutePathMother;
import com.shiproutes.shared.domain.ports.PortIdMother;

public class RouteMother {

    public static Route random() {
        return new Route(
            RouteIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random(),
            JourneysCounterMother.random());
    }

    public static Route randomNew() {
        return new Route(
            RouteIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random(),
            JourneysCounter.initialize());
    }

    public static Route reverse(Route route) {
        return new Route(
            RouteIdMother.random(),
            route.destinationPort(),
            route.originPort(),
            route.path().reverse(),
            JourneysCounterMother.random());
    }

    public static Route reverseNew(Route route) {
        return new Route(
            RouteIdMother.random(),
            route.destinationPort(),
            route.originPort(),
            route.path().reverse(),
            JourneysCounter.initialize());
    }

    public static Route updatePath(Route existentRoute) {
        return new Route(
            existentRoute.id(),
            existentRoute.originPort(),
            existentRoute.destinationPort(),
            RoutePathMother.update(existentRoute.path()),
            existentRoute.journeys());
    }
}
