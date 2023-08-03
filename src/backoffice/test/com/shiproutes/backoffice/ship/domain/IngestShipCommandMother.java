package com.shiproutes.backoffice.ship.domain;

import com.shiproutes.backoffice.port_event.application.ingest.IngestPortEventCommand;
import com.shiproutes.backoffice.ship.application.ingest.IngestShipCommand;

public class IngestShipCommandMother {

    public static IngestShipCommand from(IngestPortEventCommand portEventCommand) {
        return new IngestShipCommand(
            portEventCommand.imo(),
            portEventCommand.shipName(),
            portEventCommand.teus()
        );
    }

}
