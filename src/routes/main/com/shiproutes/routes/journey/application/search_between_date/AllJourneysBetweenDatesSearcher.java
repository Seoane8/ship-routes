package com.shiproutes.routes.journey.application.search_between_date;

import com.shiproutes.routes.journey.domain.DepartureDate;
import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyRepository;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.StartDateAfterEndDate;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllJourneysBetweenDatesSearcher {

    private final JourneyRepository repository;

    public AllJourneysBetweenDatesSearcher(JourneyRepository repository) {
        this.repository = repository;
    }

    public AllJourneysBetweenDatesResponse search(DepartureDate startDate, DepartureDate endDate) {
        ensureStartDateIsBeforeEndDate(startDate, endDate);
        Map<List<PortId>, JourneysBetweenDatesResponse> result = new HashMap<>();
        for (Journey event : repository.searchBetweenDates(startDate.startOfDay(), endDate.endOfDay())) {
            List<PortId> key = List.of(event.originPort(), event.destinationPort());
            JourneysBetweenDatesResponse journey = result.computeIfAbsent(key,
                k -> JourneysBetweenDatesResponse.from(event));
            journey.incrementJourney();
            journey.incrementTeus(event.teus());
        }
        return new AllJourneysBetweenDatesResponse(result.values());
    }

    private void ensureStartDateIsBeforeEndDate(DepartureDate startDate, DepartureDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new StartDateAfterEndDate(startDate, endDate);
        }
    }
}
