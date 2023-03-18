package com.shiproutes.routes.route.domain;

import java.util.Optional;

public interface RouteRepository {

    void save(Route route);

    Optional<Route> search(RouteId id);

}
