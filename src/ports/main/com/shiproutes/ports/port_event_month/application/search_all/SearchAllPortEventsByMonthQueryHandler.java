package com.shiproutes.ports.port_event_month.application.search_all;

import com.shiproutes.ports.port_event_month.application.AllPortEventsByMonthResponse;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchAllPortEventsByMonthQueryHandler
    implements QueryHandler<SearchAllPortEventsByMonthQuery, AllPortEventsByMonthResponse> {

    private final AllPortEventsByMonthSearcher searcher;

    public SearchAllPortEventsByMonthQueryHandler(AllPortEventsByMonthSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public AllPortEventsByMonthResponse handle(SearchAllPortEventsByMonthQuery query) {
        return searcher.search();
    }
}
