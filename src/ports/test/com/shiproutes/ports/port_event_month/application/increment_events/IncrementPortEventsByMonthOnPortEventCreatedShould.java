package com.shiproutes.ports.port_event_month.application.increment_events;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventCreated;
import com.shiproutes.ports.port_event.domain.PortEventCreatedMother;
import com.shiproutes.ports.port_event.domain.PortEventMother;
import com.shiproutes.ports.port_event_month.PortEventsByMonthModuleUnitTestCase;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonth;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncrementPortEventsByMonthOnPortEventCreatedShould extends PortEventsByMonthModuleUnitTestCase {

    IncrementPortEventsByMonthOnPortEventCreated subscriber;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        subscriber = new IncrementPortEventsByMonthOnPortEventCreated(
            new PortEventsByMonthIncrementer(repository, uuidGenerator, queryBus)
        );
    }

    @Test
    void increment_departures_of_existent_entity() {
        PortEvent portEvent = PortEventMother.randomDeparture();
        PortEventCreated event = PortEventCreatedMother.fromPortEvent(portEvent);
        PortEventsByMonth portEventsByMonth = PortEventsByMonthMother.fromPortEvent(portEvent);
        PortEventsByMonth expectedPortEventsByMonthToSave =
            PortEventsByMonthMother.incrementingDepartures(portEventsByMonth);

        shouldExists(portEventsByMonth);

        subscriber.on(event);

        shouldHaveSaved(expectedPortEventsByMonthToSave);
    }

    @Test
    void increment_arrivals_of_existent_entity() {
        PortEvent portEvent = PortEventMother.randomArrival();
        PortEventCreated event = PortEventCreatedMother.fromPortEvent(portEvent);
        PortEventsByMonth portEventsByMonth = PortEventsByMonthMother.fromPortEvent(portEvent);
        PortEventsByMonth expectedPortEventsByMonthToSave =
            PortEventsByMonthMother.incrementingArrivals(portEventsByMonth);

        shouldExists(portEventsByMonth);

        subscriber.on(event);

        shouldHaveSaved(expectedPortEventsByMonthToSave);
    }

    @Test
    void create_entity_and_increment_departures_when_entity_not_exist() {
        PortEvent portEvent = PortEventMother.randomDeparture();
        PortEventCreated event = PortEventCreatedMother.fromPortEvent(portEvent);
        PortEventsByMonth expectedPortEventsByMonthToSave =
            PortEventsByMonthMother.firstFromPortEvent(portEvent);

        shouldNotExist(expectedPortEventsByMonthToSave);
        shouldGenerateUuid(expectedPortEventsByMonthToSave.id().value());
        shouldExistPortWithCoordinates(portEvent.portId(), portEvent.coordinates());

        subscriber.on(event);

        shouldHaveSaved(expectedPortEventsByMonthToSave);
    }

    @Test
    void create_entity_and_increment_arrivals_when_entity_not_exist() {
        PortEvent portEvent = PortEventMother.randomArrival();
        PortEventCreated event = PortEventCreatedMother.fromPortEvent(portEvent);
        PortEventsByMonth expectedPortEventsByMonthToSave =
            PortEventsByMonthMother.firstFromPortEvent(portEvent);

        shouldNotExist(expectedPortEventsByMonthToSave);
        shouldGenerateUuid(expectedPortEventsByMonthToSave.id().value());
        shouldExistPortWithCoordinates(portEvent.portId(), portEvent.coordinates());

        subscriber.on(event);

        shouldHaveSaved(expectedPortEventsByMonthToSave);
    }
}