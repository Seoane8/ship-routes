package com.shiproutes.backoffice.port.application.ingest;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.command.CommandHandler;

@Service
public class IngestPortCommandHandler implements CommandHandler<IngestPortCommand> {

    private final PortIngestor ingestor;

    public IngestPortCommandHandler(PortIngestor ingestor) {
        this.ingestor = ingestor;
    }

    @Override
    public void handle(IngestPortCommand command) {
        ingestor.ingest(
            command.locode(),
            command.name(),
            command.latitude(),
            command.longitude()
        );
    }
}
