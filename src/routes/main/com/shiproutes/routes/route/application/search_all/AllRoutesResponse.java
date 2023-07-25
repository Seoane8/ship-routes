package com.shiproutes.routes.route.application.search_all;

import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AllRoutesResponse implements Response {

    private final Set<RouteResponse> routes;

    public AllRoutesResponse(Set<RouteResponse> routes) {
        this.routes = routes;
    }

    public static AllRoutesResponse from(Set<Route> entities) {
        return new AllRoutesResponse(entities.stream().map(RouteResponse::from).collect(Collectors.toSet()));
    }

    public Set<RouteResponse> routes() {
        return routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AllRoutesResponse)) return false;
        AllRoutesResponse that = (AllRoutesResponse) o;
        return Objects.equals(routes, that.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
