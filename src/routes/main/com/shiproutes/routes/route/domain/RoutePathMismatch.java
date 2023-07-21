package com.shiproutes.routes.route.domain;

import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.ports.Coordinates;

public class RoutePathMismatch extends DomainError {

    public RoutePathMismatch(Coordinates origin, Coordinates destination, RoutePath path) {
        super("route_path_mismatch",
            String.format("The route path <%s..%s> does not match with the coordinates of ports <%s..%s>",
                path.origin(), path.destination(), origin, destination));
    }

}
