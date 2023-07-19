package com.shiproutes.routes.port.application.create;

import com.shiproutes.routes.port.domain.RoutePort;
import com.shiproutes.routes.port.domain.RoutePortRepository;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ports.Coordinates;

@Service
public class RoutePortCreator {

    private final RoutePortRepository repository;

    public RoutePortCreator(RoutePortRepository repository) {
        this.repository = repository;
    }

    public void create(PortId portId, Coordinates coordinates) {
        if (repository.search(portId).isPresent()) return;

        RoutePort port = new RoutePort(portId, coordinates);

        repository.save(port);
    }
}
