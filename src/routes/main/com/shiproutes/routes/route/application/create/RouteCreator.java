package com.shiproutes.routes.route.application.create;

import com.shiproutes.routes.port.application.find_coordinates.CoordinatesResponse;
import com.shiproutes.routes.port.application.find_coordinates.FindCoordinatesQuery;
import com.shiproutes.routes.route.domain.*;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class RouteCreator {

    private final RouteRepository repository;
    private final PathGenerator pathGenerator;
    private final QueryBus queryBus;
    private final EventBus eventBus;

    public RouteCreator(RouteRepository repository, PathGenerator pathGenerator, QueryBus queryBus, EventBus eventBus) {
        this.repository = repository;
        this.pathGenerator = pathGenerator;
        this.queryBus = queryBus;
        this.eventBus = eventBus;
    }

    public void create(RouteId id, PortId originPort, PortId destinationPort) {
        if (repository.search(originPort, destinationPort).isPresent()) return;

        RoutePath path = repository.search(destinationPort, originPort).map(Route::path).map(RoutePath::reverse)
            .orElseGet(() -> searchPath(originPort, destinationPort));

        Route route = Route.create(id, originPort, destinationPort, path);

        repository.save(route);
        eventBus.publish(route.pullDomainEvents());
    }

    private RoutePath searchPath(PortId originPort, PortId destinationPort) {
        return pathGenerator.generate(findCoordinates(originPort), findCoordinates(destinationPort));
    }

    private Coordinates findCoordinates(PortId portId) {
        CoordinatesResponse response = queryBus.ask(new FindCoordinatesQuery(portId.value()));
        return new Coordinates(
            new Latitude(response.latitude()),
            new Longitude(response.longitude())
        );
    }
}
