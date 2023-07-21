package com.shiproutes.routes.route.application.find_path;

import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.List;
import java.util.Objects;

public class RoutePathResponse implements Response {

    private final List<List<Double>> path;

    public RoutePathResponse(List<List<Double>> path) {
        this.path = path;
    }

    public static RoutePathResponse from(RoutePath routePath) {
        return new RoutePathResponse(routePath.toPrimitives());
    }


    public List<List<Double>> path() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoutePathResponse)) return false;
        RoutePathResponse that = (RoutePathResponse) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
