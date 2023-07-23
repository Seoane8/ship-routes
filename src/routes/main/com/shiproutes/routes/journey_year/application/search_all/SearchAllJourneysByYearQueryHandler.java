package com.shiproutes.routes.journey_year.application.search_all;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class SearchAllJourneysByYearQueryHandler
    implements QueryHandler<SearchAllJourneysByYearQuery, AllJourneysByYearResponse> {

    private final AllJourneysByYearSearcher searcher;

    public SearchAllJourneysByYearQueryHandler(AllJourneysByYearSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public AllJourneysByYearResponse handle(SearchAllJourneysByYearQuery query) {
        return searcher.search();
    }
}
