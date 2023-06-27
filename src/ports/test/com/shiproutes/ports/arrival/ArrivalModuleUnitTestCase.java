package com.shiproutes.ports.arrival;

import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalRepository;
import com.shiproutes.ports.shared.application.FindPortQuery;
import com.shiproutes.ports.shared.application.FindTeusQuery;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.ports.shared.application.TeusResponse;
import com.shiproutes.ports.shared.domain.Coordinates;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.WordMother;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public abstract class ArrivalModuleUnitTestCase extends UnitTestCase {

    protected ArrivalRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(ArrivalRepository.class);
    }

    protected void shouldExistPortWithCoordinates(PortId portId, Coordinates coordinates) {
        shouldAsk(
            new FindPortQuery(portId.value()),
            new PortResponse(
                portId.value(),
                WordMother.random(),
                WordMother.random(),
                coordinates.longitude().value(),
                coordinates.latitude().value()
            )
        );
    }

    protected void shouldExistShipWithTeus(IMO shipId, Teus teus) {
        shouldAsk(
            new FindTeusQuery(shipId.value()),
            new TeusResponse(teus.value())
        );
    }

    protected void shouldHaveSaved(Arrival arrival) {
        verify(repository, atLeastOnce()).save(arrival);
    }

}
