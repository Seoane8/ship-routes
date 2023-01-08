package com.shiproutes.ports.arrival.application.create;

import com.shiproutes.ports.arrival.ArrivalModuleUnitTestCase;
import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrivalCreatorShould extends ArrivalModuleUnitTestCase {

    private ArrivalCreator creator;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();

        this.creator = new ArrivalCreator(repository);
    }

    @Test
    void create_new_arrival() {
        Arrival arrival = ArrivalMother.random();

        creator.create(arrival.id(), arrival.portId(), arrival.shipId(), arrival.date());

        shouldHaveSaved(arrival);
    }
}
