package com.shiproutes.routes.route.application.search_all;

import com.shiproutes.routes.route.domain.RouteRepository;
import com.shiproutes.shared.domain.Service;

@Service
public class AllRoutesSearcher {

    private final RouteRepository repository;

    public AllRoutesSearcher(RouteRepository repository) {
        this.repository = repository;
    }

    public AllRoutesResponse search() {
        return AllRoutesResponse.from(repository.searchAll());
    }

}
