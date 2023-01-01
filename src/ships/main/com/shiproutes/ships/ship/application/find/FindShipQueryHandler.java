package com.shiproutes.ships.ship.application.find;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;
import com.shiproutes.ships.ship.domain.IMO;

@Service
public final class FindShipQueryHandler implements QueryHandler<FindShipQuery, ShipResponse> {

    private final ShipFinder finder;

    public FindShipQueryHandler(ShipFinder finder) {
        this.finder = finder;
    }

    @Override
    public ShipResponse handle(FindShipQuery query) {
        return finder.find(new IMO(query.imo()));
    }
}
