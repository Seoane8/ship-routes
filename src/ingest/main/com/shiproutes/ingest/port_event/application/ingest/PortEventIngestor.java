package com.shiproutes.ingest.port_event.application.ingest;

import com.shiproutes.ingest.port.application.find_id.FindIngestPortIdQuery;
import com.shiproutes.ingest.port.application.find_id.IngestPortIdResponse;
import com.shiproutes.ingest.port_event.domain.PortCreator;
import com.shiproutes.ingest.port_event.domain.PortEventIngestedEvent;
import com.shiproutes.ingest.port_event.domain.PortEventType;
import com.shiproutes.ingest.port_event.domain.ShipCreator;
import com.shiproutes.ingest.ship.application.find_ship.FindIngestShipQuery;
import com.shiproutes.ingest.ship.application.find_ship.IngestShipResponse;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.UuidGenerator;
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
    private final ShipCreator shipCreator;
    private final UuidGenerator uuidGenerator;
    private final QueryBus queryBus;
    private final EventBus eventBus;

    public PortEventIngestor(PortCreator portCreator, ShipCreator shipCreator, UuidGenerator uuidGenerator,
                             QueryBus queryBus, EventBus eventBus) {
        this.portCreator = portCreator;
        this.shipCreator = shipCreator;
        this.uuidGenerator = uuidGenerator;
        this.queryBus = queryBus;
        this.eventBus = eventBus;
    }

    public void ingest(String locode, String portName, Coordinates coordinates,
                       String imo, String shipName, Integer teus, Instant timestamp, PortEventType eventType) {

        IMO shipId = searchIMO(imo).orElseGet(() -> {
            IMO newImo = new IMO(uuidGenerator.generate());
            shipCreator.create(newImo, shipName, teus);
            return newImo;
        });

        PortId portId = searchPortId(locode).orElseGet(() -> {
            PortId newPortId = new PortId(uuidGenerator.generate());
            portCreator.create(newPortId, locode, portName, coordinates);
            return newPortId;
        });

        PortEventIngestedEvent event = new PortEventIngestedEvent(
            uuidGenerator.generate(),
            portId.value(),
            shipId.value(),
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

    private Optional<IMO> searchIMO(String imo) {
        try {
            IngestShipResponse response = queryBus.ask(new FindIngestShipQuery(imo));
            return Optional.of(response.imo()).map(IMO::new);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
