package com.shiproutes.ports.port.application.find;

import com.shiproutes.ports.shared.application.FindPortQuery;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public final class FindPortQueryHandler implements QueryHandler<FindPortQuery, PortResponse> {

    private final PortFinder finder;

    public FindPortQueryHandler(PortFinder finder) {
        this.finder = finder;
    }

    @Override
    public PortResponse handle(FindPortQuery query) {
        return finder.find(new PortId(query.id()));
    }
}
