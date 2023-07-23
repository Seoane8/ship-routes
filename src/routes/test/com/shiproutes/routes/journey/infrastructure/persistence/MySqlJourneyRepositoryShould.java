package com.shiproutes.routes.journey.infrastructure.persistence;

import com.shiproutes.routes.journey.JourneyModuleInfrastructureTestCase;
import com.shiproutes.routes.journey.domain.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySqlJourneyRepositoryShould extends JourneyModuleInfrastructureTestCase {

    @Test
    void save_new_journey() {
        Journey journey = JourneyMother.random();

        mySqlJourneyRepository.save(journey);
    }

    @Test
    void return_an_existing_journey() {
        Journey journey = JourneyMother.random();
        mySqlJourneyRepository.save(journey);

        assertEquals(Optional.of(journey), mySqlJourneyRepository.search(journey.id()));
    }

    @Test
    void not_return_a_non_existent_journey() {
        assertEquals(Optional.empty(), mySqlJourneyRepository.search(JourneyIdMother.random()));
    }

    @Test
    void should_remove_a_journey() {
        Journey journey = JourneyMother.random();
        mySqlJourneyRepository.save(journey);

        mySqlJourneyRepository.remove(journey);
        assertEquals(Optional.empty(), mySqlJourneyRepository.search(journey.id()));
    }

    @Test
    void should_return_an_incomplete_journey_arrival() {
        Journey journey = JourneyMother.randomArrival();
        mySqlJourneyRepository.save(journey);
        DepartureDate departureDate = DepartureDateMother.randomBefore(journey.arrivalDate());

        assertEquals(
            Optional.of(journey),
            mySqlJourneyRepository.searchJourneyArrival(journey.shipId(), departureDate)
        );
    }

    @Test
    void should_return_a_complete_journey_arrival() {
        Journey journey = JourneyMother.random();
        mySqlJourneyRepository.save(journey);
        DepartureDate departureDate = DepartureDateMother.randomBetween(journey.departureDate(), journey.arrivalDate());

        assertEquals(
            Optional.of(journey),
            mySqlJourneyRepository.searchJourneyArrival(journey.shipId(), departureDate)
        );
    }

    @Test
    void not_return_an_incomplete_journey_arrival_when_departure_date_not_match() {
        Journey journey = JourneyMother.randomArrival();
        mySqlJourneyRepository.save(journey);
        DepartureDate departureDate = DepartureDateMother.randomAfter(journey.arrivalDate());

        assertEquals(
            Optional.empty(),
            mySqlJourneyRepository.searchJourneyArrival(journey.shipId(), departureDate)
        );
    }

    @Test
    void not_return_a_complete_journey_arrival_when_departure_date_not_match() {
        Journey journey = JourneyMother.random();
        mySqlJourneyRepository.save(journey);
        DepartureDate departureDate = DepartureDateMother.randomBefore(journey.departureDate());

        assertEquals(
            Optional.empty(),
            mySqlJourneyRepository.searchJourneyArrival(journey.shipId(), departureDate)
        );
    }

    @Test
    void should_return_an_incomplete_journey_departure() {
        Journey journey = JourneyMother.randomDeparture();
        mySqlJourneyRepository.save(journey);
        ArrivalDate arrivalDate = ArrivalDateMother.randomAfter(journey.departureDate());

        assertEquals(
            Optional.of(journey),
            mySqlJourneyRepository.searchJourneyDeparture(journey.shipId(), arrivalDate)
        );
    }

    @Test
    void should_return_a_complete_journey_departure() {
        Journey journey = JourneyMother.random();
        mySqlJourneyRepository.save(journey);
        ArrivalDate arrivalDate = ArrivalDateMother.randomBetween(journey.departureDate(), journey.arrivalDate());

        assertEquals(
            Optional.of(journey),
            mySqlJourneyRepository.searchJourneyDeparture(journey.shipId(), arrivalDate)
        );
    }

    @Test
    void not_return_an_incomplete_journey_departure_when_arrival_date_not_match() {
        Journey journey = JourneyMother.randomDeparture();
        mySqlJourneyRepository.save(journey);
        ArrivalDate arrivalDate = ArrivalDateMother.randomBefore(journey.departureDate());

        assertEquals(
            Optional.empty(),
            mySqlJourneyRepository.searchJourneyDeparture(journey.shipId(), arrivalDate)
        );
    }

    @Test
    void not_return_a_complete_journey_departure_when_arrival_date_not_match() {
        Journey journey = JourneyMother.random();
        mySqlJourneyRepository.save(journey);
        ArrivalDate arrivalDate = ArrivalDateMother.randomAfter(journey.arrivalDate());

        assertEquals(
            Optional.empty(),
            mySqlJourneyRepository.searchJourneyDeparture(journey.shipId(), arrivalDate)
        );
    }

    @Test
    void return_existent_ports_between_two_dates() {
        DepartureDate endDate = DepartureDateMother.random();
        DepartureDate startDate = DepartureDateMother.randomBefore(endDate);
        Journey expectedPortEvent = JourneyMother.randomWithDepartureDateBetween(startDate, endDate);
        Journey discardedPortEvent = JourneyMother.randomDepartureBefore(startDate);

        mySqlJourneyRepository.save(expectedPortEvent);
        mySqlJourneyRepository.save(discardedPortEvent);

        assertEquals(
            Set.of(expectedPortEvent),
            mySqlJourneyRepository.searchBetweenDates(startDate, endDate)
        );
    }
}
