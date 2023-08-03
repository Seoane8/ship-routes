package com.shiproutes.backoffice.port_event.application.ingest;

import com.shiproutes.backoffice.port.application.find_id.FindIngestPortIdQuery;
import com.shiproutes.backoffice.port.application.find_id.IngestPortIdResponse;
import com.shiproutes.backoffice.port_event.domain.PortCreator;
import com.shiproutes.backoffice.port_event.domain.PortEventType;
import com.shiproutes.backoffice.ship.application.ingest.IngestShipCommand;
import com.shiproutes.shared.domain.PortEventIngestedEvent;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;

import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

@Service
public class PortEventIngestor {

    private final PortCreator portCreator;
    private final UuidGenerator uuidGenerator;
    private final QueryBus queryBus;
    private final CommandBus commandBus;
    private final EventBus eventBus;

    public PortEventIngestor(PortCreator portCreator, UuidGenerator uuidGenerator,
                             QueryBus queryBus, CommandBus commandBus, EventBus eventBus) {
        this.portCreator = portCreator;
        this.uuidGenerator = uuidGenerator;
        this.queryBus = queryBus;
        this.commandBus = commandBus;
        this.eventBus = eventBus;
    }

    public void ingest(String locode, String portName, Coordinates coordinates,
                       String imo, String shipName, Integer teus, Instant timestamp, PortEventType eventType) {

        commandBus.dispatch(new IngestShipCommand(imo, shipName, teus));

        PortId portId = searchPortId(locode).orElseGet(() -> {
            PortId newPortId = new PortId(uuidGenerator.generate());
            portCreator.create(newPortId, locode, portName, coordinates);
            return newPortId;
        });

        PortEventIngestedEvent event = new PortEventIngestedEvent(
            uuidGenerator.generate(),
            locode,
            imo,
            timestamp,
            eventType.name()
        );
        eventBus.publish(Collections.singletonList(event));
    }

    private Optional<PortId> searchPortId(String locode) {
        try {
            IngestPortIdResponse response = queryBus.ask(new FindIngestPortIdQuery(locode));
            return Optional.of(response.portId()).map(PortId::new);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
