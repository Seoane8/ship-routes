package com.shiproutes.routes.journey_month.application.search_all;

import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AllJourneysByMonthResponse implements Response {

    private final Set<JourneysByMonthResponse> journeys;

    public AllJourneysByMonthResponse(Collection<JourneysByMonthResponse> journeys) {
        this.journeys = new HashSet<>(journeys);
    }

    public Set<JourneysByMonthResponse> journeys() {
        return journeys;
    }
}
