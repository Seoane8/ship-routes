package com.shiproutes.ships.ship.application.record;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.command.CommandHandler;
import com.shiproutes.ships.ship.domain.IMO;
import com.shiproutes.ships.ship.domain.ShipName;
import com.shiproutes.ships.ship.domain.Teus;

@Service
public final class RecordShipCommandHandler implements CommandHandler<RecordShipCommand> {

    private final ShipRecorder recorder;

    public RecordShipCommandHandler(ShipRecorder recorder) {
        this.recorder = recorder;
    }

    @Override
    public void handle(RecordShipCommand command) {
        recorder.record(
            new IMO(command.imo()),
            new ShipName(command.name()),
            new Teus(command.teus())
        );
    }
}
