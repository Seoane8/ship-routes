package com.shiproutes.backoffice.port_event.application.ingest;

import com.shiproutes.backoffice.port.application.ingest.IngestPortCommand;
import com.shiproutes.backoffice.port_event.domain.PortEventType;
import com.shiproutes.backoffice.ship.application.ingest.IngestShipCommand;
import com.shiproutes.shared.domain.PortEventIngestedEvent;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.ports.Coordinates;

import java.time.Instant;
import java.util.Collections;

@Service
public class PortEventIngestor {

    private final UuidGenerator uuidGenerator;
    private final CommandBus commandBus;
    private final EventBus eventBus;

    public PortEventIngestor(UuidGenerator uuidGenerator, CommandBus commandBus, EventBus eventBus) {
        this.uuidGenerator = uuidGenerator;
        this.commandBus = commandBus;
        this.eventBus = eventBus;
    }

    public void ingest(String locode, String portName, Coordinates coordinates,
                       String imo, String shipName, Integer teus, Instant timestamp, PortEventType eventType) {

        commandBus.dispatch(new IngestShipCommand(imo, shipName, teus));

        commandBus.dispatch(new IngestPortCommand(locode, portName, coordinates.latitude().value(), coordinates.longitude().value()));

        PortEventIngestedEvent event = new PortEventIngestedEvent(
            uuidGenerator.generate(),
            locode,
            imo,
            timestamp,
            eventType.name()
        );
        eventBus.publish(Collections.singletonList(event));
    }

}
