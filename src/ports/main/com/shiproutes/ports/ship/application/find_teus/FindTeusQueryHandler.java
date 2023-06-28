package com.shiproutes.ports.ship.application.find_teus;

import com.shiproutes.ports.shared.application.FindTeusQuery;
import com.shiproutes.ports.shared.application.TeusResponse;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public final class FindTeusQueryHandler implements QueryHandler<FindTeusQuery, TeusResponse> {

    private final PortShipTeusFinder finder;

    public FindTeusQueryHandler(PortShipTeusFinder finder) {
        this.finder = finder;
    }

    @Override
    public TeusResponse handle(FindTeusQuery query) {
        return finder.find(new IMO(query.imo()));
    }
}
