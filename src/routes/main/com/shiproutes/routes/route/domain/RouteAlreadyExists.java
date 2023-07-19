package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.ports.PortId;

public class RouteAlreadyExists extends DomainError {
    public RouteAlreadyExists(PortId origin, PortId destination) {
        super(
            "route_already_exists",
            String.format("The route from port <%s> to port <%s> already exists", origin, destination)
        );
    }
}
