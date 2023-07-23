package com.shiproutes.routes.journey.application.search_between_date;

import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AllJourneysBetweenDatesResponse implements Response {

    private final Set<JourneysBetweenDatesResponse> journeys;

    public AllJourneysBetweenDatesResponse(Collection<JourneysBetweenDatesResponse> journeys) {
        this.journeys = new HashSet<>(journeys);
    }

    public Set<JourneysBetweenDatesResponse> journeys() {
        return journeys;
    }

}
