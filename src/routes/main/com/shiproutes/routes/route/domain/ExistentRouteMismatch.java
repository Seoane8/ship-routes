package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.ports.PortId;

public class ExistentRouteMismatch extends DomainError {
    public ExistentRouteMismatch(RouteId id, PortId originPort, PortId destinationPort) {
        super("existent_route_mismatch",
            String.format("The route <%s> already exists and ports must be <%s..%s>", id, originPort, destinationPort)
        );
    }
}
