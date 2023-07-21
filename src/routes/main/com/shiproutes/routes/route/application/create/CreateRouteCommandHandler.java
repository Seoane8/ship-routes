package com.shiproutes.routes.route.application.create;

import com.shiproutes.routes.route.domain.RouteId;
import com.shiproutes.routes.route.domain.RoutePath;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.command.CommandHandler;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class CreateRouteCommandHandler implements CommandHandler<CreateRouteCommand> {

    private final RouteCreator creator;

    public CreateRouteCommandHandler(RouteCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateRouteCommand command) {
        creator.create(
            new RouteId(command.id()),
            new PortId(command.origin()),
            new PortId(command.destination()),
            command.path().stream().map(coordinates -> {
                if (coordinates.size() != 2) {
                    throw new IllegalArgumentException("Invalid coordinates " + coordinates);
                }
                return new Coordinates(
                    new Latitude(coordinates.get(0)),
                    new Longitude(coordinates.get(1))
                );
            }).collect(RoutePath.collector())
        );
    }
}
