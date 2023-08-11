package com.shiproutes.backoffice.user.application.search;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class SearchUserQueryHandler implements QueryHandler<SearchUserQuery, UsersResponse> {

    private final UserSearcher searcher;

    public SearchUserQueryHandler(UserSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public UsersResponse handle(SearchUserQuery query) {
        return searcher.search(query.partialUsername());
    }
}
