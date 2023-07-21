package com.shiproutes.routes.route.application.find_path;

import com.shiproutes.routes.port.application.find_coordinates.CoordinatesResponse;
import com.shiproutes.routes.port.application.find_coordinates.FindCoordinatesQuery;
import com.shiproutes.routes.route.domain.*;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Optional;

@Service
public class RoutePathFinder {

    private final RouteRepository repository;
    private final PathGenerator pathGenerator;
    private final UuidGenerator uuidGenerator;
    private final QueryBus queryBus;
    private final EventBus eventBus;

    public RoutePathFinder(RouteRepository repository, PathGenerator pathGenerator, UuidGenerator uuidGenerator,
                           QueryBus queryBus, EventBus eventBus) {
        this.repository = repository;
        this.pathGenerator = pathGenerator;
        this.uuidGenerator = uuidGenerator;
        this.queryBus = queryBus;
        this.eventBus = eventBus;
    }

    public RoutePathResponse find(PortId originPort, PortId destinationPort) {

        Optional<Route> existentRoute = repository.search(originPort, destinationPort);
        if (existentRoute.isPresent()) {
            return RoutePathResponse.from(existentRoute.get().path());
        }

        RoutePath routePath;
        Optional<Route> reversedRoute = repository.search(destinationPort, originPort);
        if (reversedRoute.isPresent()) {
            routePath = reversedRoute.get().path().reverse();
        } else {
            Coordinates origin = findCoordinates(originPort);
            Coordinates destination = findCoordinates(destinationPort);
            routePath = pathGenerator.generate(origin, destination);
        }

        RouteId routeId = new RouteId(uuidGenerator.generate());
        Route route = Route.create(routeId, originPort, destinationPort, routePath);

        repository.save(route);
        eventBus.publish(route.pullDomainEvents());
        return RoutePathResponse.from(route.path());
    }

    private Coordinates findCoordinates(PortId portId) {
        try {
            CoordinatesResponse response = queryBus.ask(new FindCoordinatesQuery(portId.value()));
            return new Coordinates(
                new Latitude(response.latitude()),
                new Longitude(response.longitude())
            );
        } catch (Exception e) {
            throw new PortNotExist(portId);
        }
    }

}
