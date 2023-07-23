package com.shiproutes.routes.journey.application.search_between_date;

import com.shiproutes.shared.domain.bus.query.Query;

import java.time.Instant;

public class SearchAllJourneysBetweenDatesQuery implements Query {

    private final Instant startDate;
    private final Instant endDate;

    public SearchAllJourneysBetweenDatesQuery(Instant startDate, Instant endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Instant startDate() {
        return startDate;
    }

    public Instant endDate() {
        return endDate;
    }
}
