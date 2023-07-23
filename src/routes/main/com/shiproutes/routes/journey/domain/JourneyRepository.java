package com.shiproutes.routes.journey.domain;

import com.shiproutes.shared.domain.IMO;

import java.util.Optional;
import java.util.Set;

public interface JourneyRepository {

    void save(Journey journey);

    Optional<Journey> search(JourneyId id);

    Optional<Journey> searchJourneyArrival(IMO shipId, DepartureDate departureDate);

    Optional<Journey> searchJourneyDeparture(IMO shipId, ArrivalDate arrivalDate);

    void remove(Journey journey);

    Set<Journey> searchBetweenDates(DepartureDate startDate, DepartureDate endDate);
}
