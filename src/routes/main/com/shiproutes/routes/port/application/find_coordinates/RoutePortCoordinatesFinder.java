package com.shiproutes.routes.port.application.find_coordinates;

import com.shiproutes.routes.port.domain.RoutePort;
import com.shiproutes.routes.port.domain.RoutePortRepository;
import com.shiproutes.routes.route.domain.PortNotExist;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class RoutePortCoordinatesFinder {

    private final RoutePortRepository repository;

    public RoutePortCoordinatesFinder(RoutePortRepository repository) {
        this.repository = repository;
    }

    public CoordinatesResponse find(PortId portId) {
        return repository.search(portId).map(RoutePort::coordinates).map(CoordinatesResponse::fromEntity)
            .orElseThrow(() -> new PortNotExist(portId));
    }
}
