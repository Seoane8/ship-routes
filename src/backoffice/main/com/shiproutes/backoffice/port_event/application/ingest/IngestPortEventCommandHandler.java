package com.shiproutes.backoffice.port_event.application.ingest;

import com.shiproutes.backoffice.port_event.domain.PortEventType;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.command.CommandHandler;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;

@Service
public class IngestPortEventCommandHandler implements CommandHandler<IngestPortEventCommand> {

    private final PortEventIngestor ingestor;

    public IngestPortEventCommandHandler(PortEventIngestor ingestor) {
        this.ingestor = ingestor;
    }

    @Override
    public void handle(IngestPortEventCommand command) {
        ingestor.ingest(
            command.locode(),
            command.portName(),
            new Coordinates(
                new Latitude(command.latitude()),
                new Longitude(command.longitude())
            ),
            command.imo(),
            command.shipName(),
            command.teus(),
            command.timestamp(),
            PortEventType.valueOf(command.eventType())
        );
    }
}
