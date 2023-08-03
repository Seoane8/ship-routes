package com.shiproutes.backoffice.port_event.application.ingest;

import com.shiproutes.backoffice.port.domain.IngestPortCommandMother;
import com.shiproutes.backoffice.port_event.PortEventModuleUnitTestCase;
import com.shiproutes.backoffice.port_event.domain.IngestPortEventCommandMother;
import com.shiproutes.backoffice.port_event.domain.PortEventIngestedEventMother;
import com.shiproutes.backoffice.ship.domain.IngestShipCommandMother;
import com.shiproutes.shared.domain.PortEventIngestedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngestPortEventCommandHandlerShould extends PortEventModuleUnitTestCase {

    IngestPortEventCommandHandler handler;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        handler = new IngestPortEventCommandHandler(
            new PortEventIngestor(uuidGenerator, commandBus, eventBus)
        );
    }

    @Test
    void publish_ingested_event_when_ship_and_port_already_exists() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();
        PortEventIngestedEvent event = PortEventIngestedEventMother.from(command);

        handler.handle(command);

        shouldHavePublished(event);
    }

    @Test
    void create_ship_when_not_exist() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();

        handler.handle(command);

        shouldHaveDispatched(IngestShipCommandMother.from(command));
    }

    @Test
    void create_port_when_not_exist() {
        IngestPortEventCommand command = IngestPortEventCommandMother.random();

        handler.handle(command);

        shouldHaveDispatched(IngestPortCommandMother.from(command));
    }


}
