package com.shiproutes.ports;

import com.shiproutes.ports.shared.application.FindPortQuery;
import com.shiproutes.ports.shared.application.FindTeusQuery;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.ports.shared.application.TeusResponse;
import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.ports.shared.domain.TotalArrivalsMother;
import com.shiproutes.ports.shared.domain.TotalDeparturesMother;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.WordMother;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.UnitTestCase;

public class PortsContextUnitTestCase extends UnitTestCase {

    protected void shouldExistPort(PortId portId, PortName portName, Coordinates coordinates) {
        shouldAsk(
            new FindPortQuery(portId.value()),
            new PortResponse(
                portId.value(),
                portName.value(),
                WordMother.random(),
                coordinates.longitude().value(),
                coordinates.latitude().value(),
                TotalDeparturesMother.random().value(),
                TotalArrivalsMother.random().value()
            )
        );
    }

    protected void shouldExistShipWithTeus(IMO shipId, Teus teus) {
        shouldAsk(
            new FindTeusQuery(shipId.value()),
            new TeusResponse(teus.value())
        );
    }

}
