package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.ports.PortId;

public class RouteAlreadyExist extends DomainError {
    public RouteAlreadyExist(PortId originPort, PortId destinationPort) {
        super("route_already_exist",
            String.format("The route <%s..%s> already exists", originPort, destinationPort)
        );
    }
}
