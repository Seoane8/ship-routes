package com.shiproutes.ships.ship.application.create;

import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.bus.command.CommandHandler;
import com.shiproutes.ships.ship.domain.ShipName;

@Service
public final class CreateShipCommandHandler implements CommandHandler<CreateShipCommand> {

    private final ShipCreator creator;

    public CreateShipCommandHandler(ShipCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateShipCommand command) {
        creator.create(
            new IMO(command.imo()),
            new ShipName(command.name()),
            new Teus(command.teus())
        );
    }
}
