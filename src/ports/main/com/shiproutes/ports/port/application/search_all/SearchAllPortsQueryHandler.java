package com.shiproutes.ports.port.application.search_all;

import com.shiproutes.ports.port.application.PortsResponse;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchAllPortsQueryHandler implements QueryHandler<SearchAllPortsQuery, PortsResponse> {

    private final AllPortsSearcher searcher;

    public SearchAllPortsQueryHandler(AllPortsSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public PortsResponse handle(SearchAllPortsQuery query) {
        return searcher.search();
    }
}
