package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.ports.PortId;

import java.util.Optional;

public interface RouteRepository {

    void save(Route route);

    Optional<Route> search(RouteId id);

    Optional<Route> search(PortId originPort, PortId destinationPort);

}
