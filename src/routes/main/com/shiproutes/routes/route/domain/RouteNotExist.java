package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.ports.PortId;

public class RouteNotExist extends DomainError {
    public RouteNotExist(PortId origin, PortId destination) {
        super("route_not_exist",
            String.format("The route from %s to %s doesn't exist", origin.value(), destination.value()));
    }
}
