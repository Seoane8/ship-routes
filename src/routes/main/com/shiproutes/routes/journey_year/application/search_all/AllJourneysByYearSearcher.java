package com.shiproutes.routes.journey_year.application.search_all;

import com.shiproutes.routes.journey_year.domain.JourneysByYear;
import com.shiproutes.routes.journey_year.domain.JourneysByYearRepository;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllJourneysByYearSearcher {

    private final JourneysByYearRepository repository;

    public AllJourneysByYearSearcher(JourneysByYearRepository repository) {
        this.repository = repository;
    }

    public AllJourneysByYearResponse search() {
        Map<List<PortId>, JourneysByYearResponse> result = new HashMap<>();
        for (JourneysByYear journeysByYear : repository.searchAll()) {
            List<PortId> key = List.of(journeysByYear.originPort(), journeysByYear.destinationPort());
            JourneysByYearResponse port = result.computeIfAbsent(key,
                k -> JourneysByYearResponse.from(journeysByYear));
            port.addJourneys(journeysByYear);
        }
        return new AllJourneysByYearResponse(result.values());
    }
}
