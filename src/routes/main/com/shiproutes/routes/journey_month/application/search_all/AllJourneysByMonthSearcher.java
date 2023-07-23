package com.shiproutes.routes.journey_month.application.search_all;

import com.shiproutes.routes.journey_month.domain.JourneysByMonth;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthRepository;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllJourneysByMonthSearcher {

    private final JourneysByMonthRepository repository;

    public AllJourneysByMonthSearcher(JourneysByMonthRepository repository) {
        this.repository = repository;
    }

    public AllJourneysByMonthResponse search() {
        Map<List<PortId>, JourneysByMonthResponse> result = new HashMap<>();
        for (JourneysByMonth journeysByMonth : repository.searchAll()) {
            List<PortId> key = List.of(journeysByMonth.originPort(), journeysByMonth.destinationPort());
            JourneysByMonthResponse port = result.computeIfAbsent(key,
                k -> JourneysByMonthResponse.from(journeysByMonth));
            port.addJourneys(journeysByMonth);
        }
        return new AllJourneysByMonthResponse(result.values());
    }
}
