package com.shiproutes.ports.year_port_event.application.search_all;

import com.shiproutes.ports.year_port_event.application.AllPortEventsByYearResponse;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchAllPortEventsByYearQueryHandler
    implements QueryHandler<SearchAllPortEventsByYearQuery, AllPortEventsByYearResponse> {

    private final AllYearPortEventsSearcher searcher;

    public SearchAllPortEventsByYearQueryHandler(AllYearPortEventsSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public AllPortEventsByYearResponse handle(SearchAllPortEventsByYearQuery query) {
        return searcher.search();
    }
}
