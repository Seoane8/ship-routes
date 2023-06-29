package com.shiproutes.ports.port.application.search_all;

import com.shiproutes.ports.port.application.PortsResponse;
import com.shiproutes.ports.port.domain.PortRepository;
import com.shiproutes.shared.domain.Service;

@Service
public final class AllPortsSearcher {

    private final PortRepository repository;

    public AllPortsSearcher(PortRepository repository) {
        this.repository = repository;
    }

    public PortsResponse search() {
        return PortsResponse.from(repository.searchAll());
    }
}
