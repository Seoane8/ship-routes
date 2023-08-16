package com.shiproutes.ports;

import com.shiproutes.ports.port.application.find_by_locode.FindPortByLocodeQuery;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortNotExist;
import com.shiproutes.ports.shared.application.FindPortQuery;
import com.shiproutes.ports.shared.application.FindTeusQuery;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.ports.shared.application.TeusResponse;
import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.ports.shared.domain.TotalArrivalsMother;
import com.shiproutes.ports.shared.domain.TotalDeparturesMother;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.IntegerMother;
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
                TotalArrivalsMother.random().value(),
                IntegerMother.random()
            )
        );
    }

    protected void shouldExistPort(Port port) {
        PortResponse portResponse = PortResponse.from(port);
        shouldAsk(new FindPortQuery(port.id().value()), portResponse);
        shouldAsk(new FindPortByLocodeQuery(port.locode().value()), portResponse);
    }

    protected void shouldNotExistPort(Port port) {
        shouldFailOnAsk(new FindPortQuery(port.id().value()), new PortNotExist(port.id()));
        shouldFailOnAsk(new FindPortByLocodeQuery(port.locode().value()), new PortNotExist(port.locode()));
    }

    protected void shouldExistShipWithTeus(IMO shipId, Teus teus) {
        shouldAsk(
            new FindTeusQuery(shipId.value()),
            new TeusResponse(teus.value())
        );
    }

}
