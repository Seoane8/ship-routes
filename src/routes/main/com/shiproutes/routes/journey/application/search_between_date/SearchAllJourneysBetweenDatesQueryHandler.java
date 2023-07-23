package com.shiproutes.routes.journey.application.search_between_date;

import com.shiproutes.routes.journey.domain.DepartureDate;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class SearchAllJourneysBetweenDatesQueryHandler
    implements QueryHandler<SearchAllJourneysBetweenDatesQuery, AllJourneysBetweenDatesResponse> {

    private final AllJourneysBetweenDatesSearcher searcher;

    public SearchAllJourneysBetweenDatesQueryHandler(AllJourneysBetweenDatesSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public AllJourneysBetweenDatesResponse handle(SearchAllJourneysBetweenDatesQuery query) {
        return searcher.search(
            new DepartureDate(query.startDate()),
            new DepartureDate(query.endDate())
        );
    }
}
