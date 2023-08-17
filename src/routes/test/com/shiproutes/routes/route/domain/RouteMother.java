package com.shiproutes.routes.route.domain;

import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.JourneysCounterMother;
import com.shiproutes.routes.shared.domain.RoutePathMother;
import com.shiproutes.shared.domain.IntegerMother;
import com.shiproutes.shared.domain.ports.PortIdMother;
import com.shiproutes.shared.domain.ports.TeusCounter;

public class RouteMother {

    public static Route random() {
        return new Route(
            RouteIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random(),
            JourneysCounterMother.random(),
            new TeusCounter(IntegerMother.random())
        );
    }

    public static Route randomNew() {
        return new Route(
            RouteIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random(),
            JourneysCounter.initialize(),
            TeusCounter.initialize()
        );
    }

    public static Route reverse(Route route) {
        return new Route(
            RouteIdMother.random(),
            route.destinationPort(),
            route.originPort(),
            route.path().reverse(),
            JourneysCounterMother.random(),
            new TeusCounter(IntegerMother.random())
        );
    }

    public static Route reverseNew(Route route) {
        return new Route(
            RouteIdMother.random(),
            route.destinationPort(),
            route.originPort(),
            route.path().reverse(),
            JourneysCounter.initialize(),
            TeusCounter.initialize()
        );
    }

    public static Route updatePath(Route existentRoute) {
        return new Route(
            existentRoute.id(),
            existentRoute.originPort(),
            existentRoute.destinationPort(),
            RoutePathMother.update(existentRoute.path()),
            existentRoute.journeys(),
            existentRoute.teus()
        );
    }

    public static Route withSamePorts(Route route) {
        return new Route(
            RouteIdMother.random(),
            route.originPort(),
            route.destinationPort(),
            RoutePathMother.random(),
            JourneysCounter.initialize(),
            TeusCounter.initialize()
        );
    }
}
