package com.shiproutes.backoffice.port_event;

import com.shiproutes.backoffice.port.application.find_id.FindIngestPortIdQuery;
import com.shiproutes.backoffice.port.application.find_id.IngestPortIdResponse;
import com.shiproutes.backoffice.port.domain.PortNotExist;
import com.shiproutes.backoffice.port_event.domain.PortCreator;
import com.shiproutes.backoffice.port_event.domain.ShipCreator;
import com.shiproutes.backoffice.ship.application.find_ship.FindIngestShipQuery;
import com.shiproutes.backoffice.ship.application.find_ship.IngestShipResponse;
import com.shiproutes.backoffice.ship.domain.ShipNotExist;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public abstract class PortEventModuleUnitTestCase extends UnitTestCase {

    protected PortCreator portCreator;
    protected ShipCreator shipCreator;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();

        portCreator = mock(PortCreator.class);
        shipCreator = mock(ShipCreator.class);
    }

    protected void shouldHaveCreatedPort(PortId portId, String locode, String portName, Coordinates coordinates) {
        verify(portCreator, atLeastOnce()).create(portId, locode, portName, coordinates);
    }

    protected void shouldHaveCreatedShip(IMO imo, String shipName, Integer teus) {
        verify(shipCreator, atLeastOnce()).create(imo, shipName, teus);
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

    protected void shouldExistShip(IMO imo) {
        shouldAsk(
            new FindIngestShipQuery(imo.value()),
            new IngestShipResponse(imo.value())
        );
    }

    protected void shouldNotExistShip(IMO imo) {
        shouldFailOnAsk(
            new FindIngestShipQuery(imo.value()),
            new ShipNotExist(imo)
        );
    }
}
