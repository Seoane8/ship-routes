package com.shiproutes.ports.port.application.find_by_locode;

import com.shiproutes.ports.port.domain.Locode;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class FindPortByLocodeQueryHandler implements QueryHandler<FindPortByLocodeQuery, PortResponse> {

    private final PortByLocodeFinder finder;

    public FindPortByLocodeQueryHandler(PortByLocodeFinder finder) {
        this.finder = finder;
    }

    @Override
    public PortResponse handle(FindPortByLocodeQuery query) {
        return finder.find(new Locode(query.locode()));
    }
}
