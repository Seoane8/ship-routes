package com.shiproutes.backoffice.ship.application.ingest;

import com.shiproutes.backoffice.ship.application.create.IngestShipCreator;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.bus.command.CommandHandler;

@Service
public class IngestShipCommandHandler implements CommandHandler<IngestShipCommand> {

    private final ShipIngestor ingestor;

    public IngestShipCommandHandler(ShipIngestor ingestor) {
        this.ingestor = ingestor;
    }

    @Override
    public void handle(IngestShipCommand command) {
        ingestor.ingest(
            new IMO(command.imo()),
            command.name(),
            new Teus(command.teus())
        );
    }
}
