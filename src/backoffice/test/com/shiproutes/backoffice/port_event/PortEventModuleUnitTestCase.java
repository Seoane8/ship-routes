package com.shiproutes.backoffice.port_event;

import com.shiproutes.backoffice.port.application.find_id.FindIngestPortIdQuery;
import com.shiproutes.backoffice.port.application.find_id.IngestPortIdResponse;
import com.shiproutes.backoffice.port.domain.PortNotExist;
import com.shiproutes.backoffice.port_event.domain.PortCreator;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public abstract class PortEventModuleUnitTestCase extends UnitTestCase {

    protected PortCreator portCreator;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();

        portCreator = mock(PortCreator.class);
    }

    protected void shouldHaveCreatedPort(PortId portId, String locode, String portName, Coordinates coordinates) {
        verify(portCreator, atLeastOnce()).create(portId, locode, portName, coordinates);
    }

    protected void shouldExistPort(PortId portId, String locode) {
        shouldAsk(
            new FindIngestPortIdQuery(locode),
            new IngestPortIdResponse(portId.value())
        );
    }

    protected void shouldNotExistPort(String locode) {
        shouldFailOnAsk(
            new FindIngestPortIdQuery(locode),
            new PortNotExist(locode)
        );
    }

}
