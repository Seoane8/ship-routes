package com.shiproutes.routes.route.application.increment;

import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RouteNotExist;
import com.shiproutes.routes.route.domain.RouteRepository;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class JourneysIncrementer {

    private final RouteRepository repository;

    public JourneysIncrementer(RouteRepository repository) {
        this.repository = repository;
    }

    public void increment(PortId originPort, PortId destinationPort, Teus teus) {

        Route route = repository.search(originPort, destinationPort)
            .orElseThrow(() -> new RouteNotExist(originPort, destinationPort));

        route.incrementJourneys();
        route.incrementTeus(teus);

        repository.save(route);
    }

}
