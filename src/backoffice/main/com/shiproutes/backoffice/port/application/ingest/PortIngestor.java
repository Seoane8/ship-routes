package com.shiproutes.backoffice.port.application.ingest;

import com.shiproutes.backoffice.port.domain.IngestPort;
import com.shiproutes.backoffice.port.domain.IngestPortRepository;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.ingest.PortIngestedEvent;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.List;

@Service
public class PortIngestor {

    private final IngestPortRepository repository;
    private final UuidGenerator uuidGenerator;
    private final EventBus eventBus;

    public PortIngestor(IngestPortRepository repository, UuidGenerator uuidGenerator, EventBus eventBus) {
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
        this.eventBus = eventBus;
    }

    public void ingest(String locode, String name, Double latitude, Double longitude) {
        if (repository.searchByLocode(locode).isPresent()) return;

        IngestPort port = new IngestPort(
            new PortId(uuidGenerator.generate()),
            locode
        );

        PortIngestedEvent event = new PortIngestedEvent(
            port.id().value(),
            port.locode(),
            name,
            latitude,
            longitude
        );
        eventBus.publish(List.of(event));
        repository.save(port);
    }
}
