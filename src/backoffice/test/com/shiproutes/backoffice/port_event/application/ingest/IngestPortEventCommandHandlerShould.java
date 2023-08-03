package com.shiproutes.backoffice.port_event.application.ingest;

import com.shiproutes.backoffice.port_event.PortEventModuleUnitTestCase;
import com.shiproutes.backoffice.port_event.domain.IngestPortEventCommandMother;
import com.shiproutes.backoffice.port_event.domain.PortEventIngestedEventMother;
import com.shiproutes.backoffice.ship.domain.CreateShipCommandMother;
import com.shiproutes.shared.domain.PortEventIngestedEvent;
import com.shiproutes.shared.domain.ports.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngestPortEventCommandHandlerShould extends PortEventModuleUnitTestCase {

    IngestPortEventCommandHandler handler;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        handler = new IngestPortEventCommandHandler(
            new PortEventIngestor(portCreator, uuidGenerator, queryBus, commandBus, eventBus)
        );
    }

    @Test
    void publish_ingested_event_when_ship_and_port_already_exists() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);
        PortId portId = PortIdMother.random();
        shouldExistPort(portId, command.locode());
        shouldGenerateUuid(event.aggregateId());

        handler.handle(command);

        shouldHavePublished(event);
    }

    @Test
    void publish_ingested_event_when_port_not_exist() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);
        PortId portId = PortIdMother.random();
        shouldNotExistPort(command.locode());
        shouldGenerateUuids(portId.value(), event.aggregateId());

        handler.handle(command);

        shouldHavePublished(event);
    }

    @Test
    void create_port_when_not_exist() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);
        PortId portId = PortIdMother.random();
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
        PortId portId = PortIdMother.random();
        shouldExistPort(portId, command.locode());
        shouldGenerateUuids(event.aggregateId());

        handler.handle(command);

        shouldHaveDispatched(CreateShipCommandMother.from(command));
    }
}
