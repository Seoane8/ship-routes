package com.shiproutes.routes.journey_month.application.search_all;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class SearchAllJourneysByMonthQueryHandler
    implements QueryHandler<SearchAllJourneysByMonthQuery, AllJourneysByMonthResponse> {

    private final AllJourneysByMonthSearcher searcher;

    public SearchAllJourneysByMonthQueryHandler(AllJourneysByMonthSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public AllJourneysByMonthResponse handle(SearchAllJourneysByMonthQuery query) {
        return searcher.search();
    }
}
