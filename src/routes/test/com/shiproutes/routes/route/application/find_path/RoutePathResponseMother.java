package com.shiproutes.routes.route.application.find_path;

import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.shared.application.RoutePathResponse;

public class RoutePathResponseMother {

    public static RoutePathResponse of(Route route) {
        return new RoutePathResponse(route.path().toPrimitives());
    }

    public static RoutePathResponse reversed(Route route) {
        return new RoutePathResponse(route.path().reverse().toPrimitives());
    }
}
