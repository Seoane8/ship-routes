package com.shiproutes.ports.port_event_year.application.increment_events;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventCreatedMother;
import com.shiproutes.ports.port_event.domain.PortEventMother;
import com.shiproutes.ports.port_event_year.PortEventsByYearModuleUnitTestCase;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearMother;
import com.shiproutes.shared.domain.ports.PortEventCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncrementPortEventsByYearOnPortEventCreatedShould extends PortEventsByYearModuleUnitTestCase {

    IncrementPortEventsByYearOnPortEventCreated subscriber;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        subscriber = new IncrementPortEventsByYearOnPortEventCreated(
            new PortEventsByYearIncrementer(repository, uuidGenerator, queryBus)
        );
    }

    @Test
    void increment_departures_of_existent_entity() {
        PortEvent portEvent = PortEventMother.randomDeparture();
        PortEventCreated event = PortEventCreatedMother.fromPortEvent(portEvent);
        PortEventsByYear portEventsByYear = PortEventsByYearMother.fromPortEvent(portEvent);
        PortEventsByYear expectedPortEventsByYearToSave =
            PortEventsByYearMother.incrementingDepartures(portEventsByYear);

        shouldExists(portEventsByYear);

        subscriber.on(event);

        shouldHaveSaved(expectedPortEventsByYearToSave);
    }

    @Test
    void increment_arrivals_of_existent_entity() {
        PortEvent portEvent = PortEventMother.randomArrival();
        PortEventCreated event = PortEventCreatedMother.fromPortEvent(portEvent);
        PortEventsByYear portEventsByYear = PortEventsByYearMother.fromPortEvent(portEvent);
        PortEventsByYear expectedPortEventsByYearToSave =
            PortEventsByYearMother.incrementingArrivals(portEventsByYear);

        shouldExists(portEventsByYear);

        subscriber.on(event);

        shouldHaveSaved(expectedPortEventsByYearToSave);
    }

    @Test
    void create_entity_and_increment_departures_when_entity_not_exist() {
        PortEvent portEvent = PortEventMother.randomDeparture();
        PortEventCreated event = PortEventCreatedMother.fromPortEvent(portEvent);
        PortEventsByYear expectedPortEventsByYearToSave =
            PortEventsByYearMother.firstFromPortEvent(portEvent);

        shouldNotExist(expectedPortEventsByYearToSave);
        shouldGenerateUuid(expectedPortEventsByYearToSave.id().value());
        shouldExistPortWithCoordinates(portEvent.portId(), portEvent.coordinates());

        subscriber.on(event);

        shouldHaveSaved(expectedPortEventsByYearToSave);
    }

    @Test
    void create_entity_and_increment_arrivals_when_entity_not_exist() {
        PortEvent portEvent = PortEventMother.randomArrival();
        PortEventCreated event = PortEventCreatedMother.fromPortEvent(portEvent);
        PortEventsByYear expectedPortEventsByYearToSave =
            PortEventsByYearMother.firstFromPortEvent(portEvent);

        shouldNotExist(expectedPortEventsByYearToSave);
        shouldGenerateUuid(expectedPortEventsByYearToSave.id().value());
        shouldExistPortWithCoordinates(portEvent.portId(), portEvent.coordinates());

        subscriber.on(event);

        shouldHaveSaved(expectedPortEventsByYearToSave);
    }
}
