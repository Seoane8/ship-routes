package com.shiproutes.ports.port_event.application.search_between_date;

import com.shiproutes.ports.port_event.application.AllPortEventsBetweenDatesResponse;
import com.shiproutes.ports.port_event.domain.PortEventDate;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class SearchAllPortEventsBetweenDatesQueryHandler
    implements QueryHandler<SearchAllPortEventsBetweenDatesQuery, AllPortEventsBetweenDatesResponse> {

    private final AllPortEventsBetweenDatesSearcher searcher;

    public SearchAllPortEventsBetweenDatesQueryHandler(AllPortEventsBetweenDatesSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public AllPortEventsBetweenDatesResponse handle(SearchAllPortEventsBetweenDatesQuery query) {
        return searcher.search(
            new PortEventDate(query.startDate()),
            new PortEventDate(query.endDate())
        );
    }
}
