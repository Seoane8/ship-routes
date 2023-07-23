package com.shiproutes.routes.journey_year.application.search_all;

import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AllJourneysByYearResponse implements Response {

    private final Set<JourneysByYearResponse> journeys;

    public AllJourneysByYearResponse(Collection<JourneysByYearResponse> journeys) {
        this.journeys = new HashSet<>(journeys);
    }

    public Set<JourneysByYearResponse> journeys() {
        return journeys;
    }
}
