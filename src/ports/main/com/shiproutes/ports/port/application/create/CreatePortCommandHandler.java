package com.shiproutes.ports.port.application.create;

import com.shiproutes.ports.port.domain.Locode;
import com.shiproutes.ports.port.domain.PortName;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.command.CommandHandler;
import com.shiproutes.shared.domain.coordinates.Coordinates;
import com.shiproutes.shared.domain.coordinates.Latitude;
import com.shiproutes.shared.domain.coordinates.Longitude;

@Service
public final class CreatePortCommandHandler implements CommandHandler<CreatePortCommand> {

    private final PortCreator creator;

    public CreatePortCommandHandler(PortCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreatePortCommand command) {
        creator.create(
            new PortId(command.id()),
            new PortName(command.name()),
            new Locode(command.locode()),
            new Coordinates(
                new Latitude(command.latitude()),
                new Longitude(command.longitude())
            )
        );
    }
}
