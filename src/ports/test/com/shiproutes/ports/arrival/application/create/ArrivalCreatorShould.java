package com.shiproutes.ports.arrival.application.create;

import com.shiproutes.ports.arrival.ArrivalModuleUnitTestCase;
import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalCreated;
import com.shiproutes.ports.arrival.domain.ArrivalCreatedMother;
import com.shiproutes.ports.arrival.domain.ArrivalMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrivalCreatorShould extends ArrivalModuleUnitTestCase {

    private ArrivalCreator creator;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();

        this.creator = new ArrivalCreator(repository, queryBus, eventBus);
    }

    @Test
    void create_new_arrival() {
        Arrival arrival = ArrivalMother.random();
        shouldExistPortWithCoordinates(arrival.portId(), arrival.coordinates());
        shouldExistShipWithTeus(arrival.shipId(), arrival.teus());

        creator.create(arrival.id(), arrival.portId(), arrival.shipId(), arrival.date());

        shouldHaveSaved(arrival);
    }

    @Test
    void publish_arrival_created_event() {
        Arrival arrival = ArrivalMother.random();
        ArrivalCreated domainEvent = ArrivalCreatedMother.fromArrival(arrival);
        shouldExistPortWithCoordinates(arrival.portId(), arrival.coordinates());
        shouldExistShipWithTeus(arrival.shipId(), arrival.teus());

        creator.create(arrival.id(), arrival.portId(), arrival.shipId(), arrival.date());

        shouldHavePublished(domainEvent);
    }
}
