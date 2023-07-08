package com.shiproutes.ports.port_event_year.application.search_all;

import com.shiproutes.ports.port_event_year.application.AllPortEventsByYearResponse;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchAllPortEventsByYearQueryHandler
    implements QueryHandler<SearchAllPortEventsByYearQuery, AllPortEventsByYearResponse> {

    private final AllPortEventsByYearSearcher searcher;

    public SearchAllPortEventsByYearQueryHandler(AllPortEventsByYearSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public AllPortEventsByYearResponse handle(SearchAllPortEventsByYearQuery query) {
        return searcher.search();
    }
}
