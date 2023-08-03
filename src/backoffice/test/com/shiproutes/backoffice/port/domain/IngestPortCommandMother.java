package com.shiproutes.backoffice.port.domain;

import com.shiproutes.backoffice.port.application.ingest.IngestPortCommand;
import com.shiproutes.backoffice.port_event.application.ingest.IngestPortEventCommand;
import com.shiproutes.shared.domain.bus.command.Command;

public class IngestPortCommandMother {
    public static Command from(IngestPortEventCommand command) {
        return new IngestPortCommand(
            command.locode(),
            command.portName(),
            command.latitude(),
            command.longitude()
        );
    }
}
