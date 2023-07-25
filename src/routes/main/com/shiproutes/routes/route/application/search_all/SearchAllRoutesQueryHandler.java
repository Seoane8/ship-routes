package com.shiproutes.routes.route.application.search_all;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class SearchAllRoutesQueryHandler implements QueryHandler<SearchAllRoutesQuery, AllRoutesResponse> {

    private final AllRoutesSearcher searcher;

    public SearchAllRoutesQueryHandler(AllRoutesSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public AllRoutesResponse handle(SearchAllRoutesQuery query) {
        return searcher.search();
    }
}
