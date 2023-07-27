package com.shiproutes.routes.route.application.decrement;

import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RouteNotExist;
import com.shiproutes.routes.route.domain.RouteRepository;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class JourneysDecrementer {

    private final RouteRepository repository;

    public JourneysDecrementer(RouteRepository repository) {
        this.repository = repository;
    }

    public void decrement(PortId originPort, PortId destinationPort) {

        Route route = repository.search(originPort, destinationPort)
            .orElseThrow(() -> new RouteNotExist(originPort, destinationPort));

        route.decrementJourneys();

        repository.save(route);
    }

}
