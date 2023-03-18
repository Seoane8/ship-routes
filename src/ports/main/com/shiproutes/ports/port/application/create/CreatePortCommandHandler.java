package com.shiproutes.ports.port.application.create;

import com.shiproutes.ports.port.domain.*;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.command.CommandHandler;

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
