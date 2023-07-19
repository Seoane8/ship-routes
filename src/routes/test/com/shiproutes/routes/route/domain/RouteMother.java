package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.PortIdMother;

public class RouteMother {

    public static Route random() {
        return new Route(
            RouteIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random()
        );
    }

}
