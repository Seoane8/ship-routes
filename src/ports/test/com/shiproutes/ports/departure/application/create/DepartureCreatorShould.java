package com.shiproutes.ports.departure.application.create;

import com.shiproutes.ports.departure.DepartureModuleUnitTestCase;
import com.shiproutes.ports.departure.domain.Departure;
import com.shiproutes.ports.departure.domain.DepartureCreated;
import com.shiproutes.ports.departure.domain.DepartureCreatedMother;
import com.shiproutes.ports.departure.domain.DepartureMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartureCreatorShould extends DepartureModuleUnitTestCase {

    private DepartureCreator creator;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        creator = new DepartureCreator(repository, queryBus, eventBus);
    }

    @Test
    void save_a_valid_departure() {
        Departure departure = DepartureMother.random();
        shouldExistPortWithCoordinates(departure.portId(), departure.coordinates());
        shouldExistShipWithTeus(departure.shipId(), departure.teus());

        creator.create(departure.id(), departure.portId(), departure.shipId(), departure.date());

        shouldHaveSaved(departure);
    }

    @Test
    void publish_departure_created_event() {
        Departure departure = DepartureMother.random();
        DepartureCreated domainEvent = DepartureCreatedMother.fromDeparture(departure);
        shouldExistPortWithCoordinates(departure.portId(), departure.coordinates());
        shouldExistShipWithTeus(departure.shipId(), departure.teus());

        creator.create(departure.id(), departure.portId(), departure.shipId(), departure.date());

        shouldHavePublished(domainEvent);
    }
}
