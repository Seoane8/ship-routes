package com.shiproutes.routes.route.application.create;

import com.shiproutes.routes.port.application.find_coordinates.CoordinatesResponse;
import com.shiproutes.routes.port.application.find_coordinates.FindCoordinatesQuery;
import com.shiproutes.routes.route.domain.*;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Optional;

@Service
public class RouteCreator {

    private final RouteRepository repository;
    private final QueryBus queryBus;
    private final EventBus eventBus;

    public RouteCreator(RouteRepository repository, QueryBus queryBus, EventBus eventBus) {
        this.repository = repository;
        this.queryBus = queryBus;
        this.eventBus = eventBus;
    }

    public void create(RouteId id, PortId originPort, PortId destinationPort, RoutePath path)
        throws PortNotExist, RoutePathMismatch {

        Route route;
        ensurePathIsCorrect(originPort, destinationPort, path);

        Optional<Route> existentRoute = searchExistentRoute(id, originPort, destinationPort);
        if (existentRoute.isPresent()) {
            route = existentRoute.get();
            route.updatePath(path);
        } else {
            route = Route.create(id, originPort, destinationPort, path);
        }

        repository.save(route);
        eventBus.publish(route.pullDomainEvents());
    }

    private void ensurePathIsCorrect(PortId originPort, PortId destinationPort, RoutePath path)
        throws PortNotExist, RoutePathMismatch {
        Coordinates origin = findCoordinates(originPort);
        Coordinates destination = findCoordinates(destinationPort);
        if (!path.origin().equals(origin) || !path.destination().equals(destination)) {
            throw new RoutePathMismatch(origin, destination, path);
        }
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

    private Optional<Route> searchExistentRoute(RouteId id, PortId originPort, PortId destinationPort) {
        Optional<Route> existentRoute = repository.search(id);
        if (existentRoute.isPresent() && !existentRoute.get().matches(originPort, destinationPort)) {
            throw new ExistentRouteMismatch(id, originPort, destinationPort);
        }
        if (existentRoute.isEmpty() && repository.search(originPort, destinationPort).isPresent()) {
            throw new RouteAlreadyExist(originPort, destinationPort);
        }
        return existentRoute;
    }
}
