package com.shiproutes.routes.port.domain;

import com.shiproutes.shared.domain.ports.PortId;

import java.util.Optional;

public interface RoutePortRepository {

    void save(RoutePort routePort);

    Optional<RoutePort> search(PortId id);

}
