package com.shiproutes.ingest.port_event.application.ingest;

import com.shiproutes.ingest.port_event.PortEventModuleUnitTestCase;
import com.shiproutes.ingest.port_event.domain.IngestPortEventCommandMother;
import com.shiproutes.ingest.port_event.domain.PortEventIngestedEvent;
import com.shiproutes.ingest.port_event.domain.PortEventIngestedEventMother;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngestPortEventCommandHandlerShould extends PortEventModuleUnitTestCase {

    IngestPortEventCommandHandler handler;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        handler = new IngestPortEventCommandHandler(
            new PortEventIngestor(portCreator, shipCreator, uuidGenerator, queryBus, eventBus)
        );
    }

    @Test
    void publish_ingested_event_when_ship_and_port_already_exists() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);
        IMO imo = new IMO(command.imo());
        PortId portId = new PortId(event.portId());
        shouldExistShip(imo);
        shouldExistPort(portId, command.locode());
        shouldGenerateUuid(event.aggregateId());

        handler.handle(command);

        shouldHavePublished(event);
    }

    @Test
    void publish_ingested_event_when_port_not_exist() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);
        IMO imo = new IMO(command.imo());
        PortId portId = new PortId(event.portId());
        shouldExistShip(imo);
        shouldNotExistPort(command.locode());
        shouldGenerateUuids(portId.value(), event.aggregateId());

        handler.handle(command);

        shouldHavePublished(event);
    }

    @Test
    void publish_ingested_event_when_ship_not_exist() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);
        IMO imo = new IMO(command.imo());
        PortId portId = new PortId(event.portId());
        shouldNotExistShip(imo);
        shouldExistPort(portId, command.locode());
        shouldGenerateUuids(imo.value(), event.aggregateId());

        handler.handle(command);

        shouldHavePublished(event);
    }

    @Test
    void publish_ingested_event_when_ship_and_port_not_exist() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);
        IMO imo = new IMO(command.imo());
        PortId portId = new PortId(event.portId());
        shouldNotExistShip(imo);
        shouldNotExistPort(command.locode());
        shouldGenerateUuids(imo.value(), portId.value(), event.aggregateId());

        handler.handle(command);

        shouldHavePublished(event);
    }

    @Test
    void create_port_when_not_exist() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);
        IMO imo = new IMO(command.imo());
        PortId portId = new PortId(event.portId());
        shouldExistShip(imo);
        shouldNotExistPort(command.locode());
        shouldGenerateUuids(portId.value(), event.aggregateId());

        handler.handle(command);

        shouldHaveCreatedPort(portId, command.locode(), command.portName(),
            new Coordinates(new Latitude(command.latitude()), new Longitude(command.longitude())));
    }

    @Test
    void create_ship_when_not_exist() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);
        IMO imo = new IMO(command.imo());
        PortId portId = new PortId(event.portId());
        shouldNotExistShip(imo);
        shouldExistPort(portId, command.locode());
        shouldGenerateUuids(imo.value(), event.aggregateId());

        handler.handle(command);

        shouldHaveCreatedShip(imo, command.shipName(), command.teus());
    }
}
